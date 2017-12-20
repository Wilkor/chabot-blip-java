package br.com.gmp.bot.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.gmp.bot.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
	
}
