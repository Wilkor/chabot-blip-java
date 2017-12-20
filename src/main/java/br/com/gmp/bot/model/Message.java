package br.com.gmp.bot.model;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	
	private String id;
	
	@JsonProperty("to")
	private String to;
	
	private String type;
	
	private String content;
	
	@JsonProperty("from")
	private String from;
	
	@JsonIgnore
	private String metadata;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	
	@Override
	public String toString() {
		JSONObject j = new JSONObject();
		
		j.put("to", this.getTo());
		j.put("from", this.getFrom());
		j.put("content", this.getContent());
		j.put("type", this.getType());
		j.put("id", this.getId());
		
		return j.toString();
	}


}
