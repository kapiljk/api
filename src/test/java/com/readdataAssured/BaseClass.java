package com.readdataAssured;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	RequestSpecification reqspec;
	Response response;
	public void addHeader(String key,String value) {
		reqspec= RestAssured.given().header(key,value);
	}
	public void addHeaders(Headers headers) {
		reqspec= RestAssured.given().headers(headers);
	}
	
	public void addQueryParam(String key, String value) {
		reqspec = reqspec.queryParam(key, value);
	}
	public void addPathparam(String key, String value) {
		reqspec=reqspec.pathParam(key, value);
	}
	public void addBody(String body) {
		reqspec=reqspec.body(body);
	}
	public void addBody(Object body) {
		reqspec=reqspec.body(body);
	}
	
	public Response requestType(String type, String endpoint) {
		switch (type) {
		case "GET":
			response= reqspec.log().all().get(endpoint);
			break;
		case "POST":
			response= reqspec.log().all().post(endpoint);
			break;
		case "PUT":
			response= reqspec.log().all().put(endpoint);
			break;
		case "DELETE":
			response= reqspec.log().all().delete(endpoint);
			break;
		default:
			break;
		}
		return response;
	}
	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}
	public String getResBodyAsString(Response response) {
		String asString= response.asString();
	    return asString;
	}
	public String getResBodyAsPrettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;
	}
	public void addBaseAuth(String username, String password) {
	reqspec = reqspec.auth().preemptive().basic(username, password);
	}
	}
