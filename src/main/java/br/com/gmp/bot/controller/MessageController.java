package br.com.gmp.bot.controller;

import javax.inject.Inject;

import org.limeprotocol.messaging.contents.PlainText;
import org.limeprotocol.serialization.JacksonEnvelopeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gmp.bot.entity.Message;
import br.com.gmp.bot.repository.MessageRepository;
import br.com.gmp.bot.service.ServiceHttp;
import br.com.gmp.bot.service.ServiceMapper;


@RestController
@RequestMapping("messages")
public class MessageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
	
	private ServiceMapper serviceMapper;
	private ServiceHttp serviceHttp;
	private MessageRepository messageRepository;
	
	@Inject
	public MessageController(ServiceMapper serviceMapper, ServiceHttp serviceHttp, MessageRepository messageRepository) {
		this.serviceMapper = serviceMapper;
		this.serviceHttp = serviceHttp;
		this.messageRepository = messageRepository;
	}


	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void messageResponse(@RequestBody String envelope) throws Exception {

		try {

			org.limeprotocol.Message msg = (org.limeprotocol.Message) new JacksonEnvelopeSerializer().deserialize(envelope);
			
			messageRepository.save(new Message(envelope));

			LOGGER.info("Message from user " + msg.toString());
			
			org.limeprotocol.Message limeMsg = new org.limeprotocol.Message();
			
			limeMsg.setContent(msg.getContent());
			limeMsg.setTo(msg.getFrom());
			limeMsg.setFrom(msg.getTo());
			limeMsg.setId(org.limeprotocol.EnvelopeId.newId());
		
			HttpStatus status = serviceHttp.post(limeMsg);

			messageRepository.save(new Message(limeMsg.toString()));
			
			
			if (status != HttpStatus.ACCEPTED ) {
				LOGGER.error("Request not accepted");
			}

			LOGGER.info("Message from bot " + limeMsg.toString());
			
		} catch (Exception e) {
			LOGGER.error("Error on get messages", e);
		}

	}

}
