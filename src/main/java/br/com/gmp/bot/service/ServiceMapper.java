package br.com.gmp.bot.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class ServiceMapper {

	private ObjectMapper objectMapper;
	
	@Inject
	public ServiceMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	public ObjectMapper get() throws Exception {

		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        return objectMapper;
	}
	
	
	
}
