package br.com.gmp.bot.controller;

import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gmp.bot.entity.Message;
import br.com.gmp.bot.model.Envelope;
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

			Envelope from = serviceMapper.get().readValue(envelope, Envelope.class);
			
			HttpStatus status = serviceHttp.post(from);
			
			
			messageRepository.save(new Message(envelope));
			
			if (status != HttpStatus.ACCEPTED ) {
				LOGGER.error("Request not accepted");
			}

			LOGGER.info("Message from user " + from.toString());
			
			Envelope to = new Envelope();
			to.setTo(from.getFrom());
			to.setFrom(from.getTo());
			to.setId(UUID.randomUUID().toString());
			to.setContent(from.getContent());
			to.setType(from.getType());
			
			status = serviceHttp.post(to);

			messageRepository.save(new Message(to.toString()));
			
			
			if (status != HttpStatus.ACCEPTED ) {
				LOGGER.error("Request not accepted");
			}

			LOGGER.info("Message from bot " + to.toString());
			
		} catch (Exception e) {
			LOGGER.error("Error on get messages", e);
		}

	}

}
