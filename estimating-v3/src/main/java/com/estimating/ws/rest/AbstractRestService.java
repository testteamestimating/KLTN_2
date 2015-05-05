package com.estimating.ws.rest;

import org.springframework.web.client.RestTemplate;

public class AbstractRestService {
	protected String urlUseCasePoint = "http://estimating-rest-ws.elasticbeanstalk.com/usecasepoint/";

	protected String urlFunctionPoint = "http://estimating-rest-ws.elasticbeanstalk.com/functionpoint/";


	RestTemplate restTemplate;

	/**
	 * @return the restTemplate
	 */
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	/**
	 * @param restTemplate
	 *            the restTemplate to set
	 */
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
