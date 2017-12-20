package br.com.gmp.bot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private int id;
	
	private String message;
	
	
	public Message(String msg) {
		this.setMessage(msg);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
