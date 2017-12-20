package br.com.gmp.bot.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gmp.bot.entity.Notification;
import br.com.gmp.bot.repository.NotificationRepository;
import br.com.gmp.bot.service.ServiceHttp;
import br.com.gmp.bot.service.ServiceMapper;

@RestController
@RequestMapping("notifications")
public class NotificationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
	
	private ServiceMapper serviceMapper;
	private ServiceHttp serviceHttp;
	private NotificationRepository notificatioRepository;
	
	@Inject
	public NotificationController(ServiceMapper serviceMapper, ServiceHttp serviceHttp, NotificationRepository notificatioRepository) {
		this.serviceMapper = serviceMapper;
		this.serviceHttp = serviceHttp;
		this.notificatioRepository = notificatioRepository;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void notificationResponse(@RequestBody String body) throws Exception {
		
		notificatioRepository.save(new Notification(body));
		LOGGER.info("Notification " + body);
		
	}
	
}
