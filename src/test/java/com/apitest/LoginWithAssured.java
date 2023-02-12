package com.apitest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class LoginWithAssured extends BaseClass {
@Test
public void login() {
	//1. Add header
	addHeader("accept", "application/json");
	//2. Basic authentication
	addBaseAuth("palepu.jk@gmail.com", "Jehovah@jk12");
	//3. Method Type
	Response response = requestType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
	int actStatusCode = getStatusCode(response);
	System.out.println(actStatusCode);
	Assert.assertEquals(actStatusCode, 200,"Verifying Status Code");
	
	
	
}

}
