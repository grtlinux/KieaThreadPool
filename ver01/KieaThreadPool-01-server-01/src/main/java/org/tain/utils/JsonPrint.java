package org.tain.utils;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonPrint implements JsonPrintImpl {

	private static JsonPrint instance = null;
	
	public static JsonPrint getInstance() {
		if (instance == null) {
			instance = new JsonPrint();
		}
		return instance;
	}
	
	private JsonPrint() {
		if (this.objectMapper == null) {
			this.objectMapper = new ObjectMapper();
			
			this.objectMapper.registerModule(new JavaTimeModule());
			
			// allow single quote
			//this.objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, false);
			
			// if unknown fields in JSON, no exception when transfer json to object
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
			
			this.objectMapper.enable(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID);
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, false);
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
			
			// exclude fiels with null value.
			this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			
			//SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept("stream");
			//FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", theFilter);
		}
	}
	
	///////////////////////////////////////////////////////
	///////////////////////////////////////////////////////
	///////////////////////////////////////////////////////
	
	private ObjectMapper objectMapper = null;
	
	@Override
	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}
	
	@Override
	public String toJson(Object object) {
		try {
			return this.objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}

	@Override
	public String toPrettyJson(Object object) {
		try {
			return this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}

	@Override
	public void printJson(Object object) {
		System.out.println("JSON >>>>> " + this.toJson(object));
	}

	@Override
	public void printJson(String title, Object object) {
		System.out.println(title + " JSON >>>>> " + this.toJson(object));
	}

	@Override
	public void printPrettyJson(Object object) {
		System.out.println("Pretty JSON >>>>> " + this.toPrettyJson(object));
	}

	@Override
	public void printPrettyJson(String title, Object object) {
		System.out.println(title + " Pretty JSON >>>>> " + this.toPrettyJson(object));
	}

	@Override
	public void saveJson(File file, Object object) {
		try {
			this.objectMapper.writeValue(file, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void savePrettyJson(File file, Object object) {
		try {
			this.objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
