package com.reltessinger.upscaleEnergy.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfiguration {

	@Value("${domain}")
	private String domain;
	
	@Value("${server.servlet.contextPath}")
	private String contextPath;
		
	public URI getURI(String pathRequest, int id) {
		try {
			return new URI(domain+contextPath+pathRequest+id);
		} catch (URISyntaxException e) {
			return null;
		}
		
	}
	
}
