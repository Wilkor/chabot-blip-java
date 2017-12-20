package br.com.gmp.bot.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.gmp.bot.entity.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
