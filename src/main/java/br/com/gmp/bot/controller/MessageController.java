package br.com.gmp.bot.controller;

import javax.inject.Inject;

import org.limeprotocol.EnvelopeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gmp.bot.model.Message;
import br.com.gmp.bot.service.ServiceHttp;
import br.com.gmp.bot.service.ServiceMapper;

@RestController
@RequestMapping("messages")
public class MessageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
	
	private ServiceMapper serviceMapper;
	private ServiceHttp serviceHttp;
	
	@Inject
	public MessageController(ServiceMapper serviceMapper, ServiceHttp serviceHttp) {
		this.serviceMapper = serviceMapper;
		this.serviceHttp = serviceHttp;
	}


	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void messageResponse(@RequestBody String envelope) throws Exception {

		try {

			Message from = serviceMapper.get().readValue(envelope, Message.class);
			
			HttpStatus status = serviceHttp.post(from);
			
			System.out.println(status);
			
			if (status != HttpStatus.ACCEPTED ) {
				LOGGER.error("Request not accepted");
			}

			Message to = new Message();
			to.setTo(from.getFrom());
			to.setFrom(from.getTo());
			to.setId(EnvelopeId.newId());
			to.setContent(from.getContent());
			to.setType(from.getType());
			
			status = serviceHttp.post(to);
			
			System.out.println("Sender " + status);
			
			if (status != HttpStatus.ACCEPTED ) {
				LOGGER.error("Request not accepted");
			}

			
			
		} catch (Exception e) {
			LOGGER.error("Error on get messages", e);
		}

	}

}
