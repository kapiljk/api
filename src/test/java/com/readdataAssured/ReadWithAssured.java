package com.readdataAssured;


/**
 * @See reading data from json file by converting date into object
 */
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
//first_name=kapil
//last_name=palepu
//email= palepu.jk@gmail.com
//phoneNo=7993964845


public class ReadWithAssured extends BaseClass {
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
	Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
	String actFirst_name = login_Output_Pojo.getData().getFirst_name();
	String actEmail = login_Output_Pojo.getData().getEmail();
	Assert.assertEquals(actFirst_name, "kapil", "Verifying First Name");
	Assert.assertEquals(actEmail, "palepu.jk@gmail.com", "Verifying Email Id");
	
}

}
