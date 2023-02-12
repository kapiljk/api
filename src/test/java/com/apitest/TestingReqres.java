package com.apitest;



import org.junit.Test;

import io.restassured.response.Response;

public class TestingReqres extends BaseClass {

	@Test
	public void createListUser() {
		addHeader("accept", "application/json");
		Response requestType = requestType("GET", "https://reqres.in/api/users?page=2");
		int statusCode = getStatusCode(requestType);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(requestType);
		System.out.println(resBodyAsPrettyString);
	}

	@Test
	public void creatUser() {
		addHeader("accept", "application/json");
		addBody("addHeader(\"accept\", \"application/json\");\r\n"
				+ "		Response requestType = requestType(\"GET\", \"https://reqres.in/api/users?page=2\");\r\n"
				+ "		int statusCode = getStatusCode(requestType);\r\n" + "		System.out.println(statusCode);\r\n"
				+ "		String resBodyAsPrettyString = getResBodyAsPrettyString(requestType);\r\n"
				+ "		System.out.println(resBodyAsPrettyString);");
		Response requestType = requestType("POST", "https://reqres.in/api/users");

		int statusCode = getStatusCode(requestType);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(requestType);
		System.out.println(resBodyAsPrettyString);

	}

	@Test
	public void updateUser() {
		addHeader("accept", "application/json");
		addBody("{\r\n" + "    \"name\": \"morpheus\",\r\n" + "    \"job\": \"zion resident\"\r\n" + "}");
		Response requestType = requestType("PUT", "https://reqres.in/api/users/2");

		int statusCode = getStatusCode(requestType);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(requestType);
		System.out.println(resBodyAsPrettyString);

	}

	@Test
	public void deleteUser() {
		addHeader("accept", "application/json");

		Response requestType = requestType("DELETE", "https://reqres.in/api/users/2");

		int statusCode = getStatusCode(requestType);
		System.out.println(statusCode);
		String resBodyAsPrettyString = getResBodyAsPrettyString(requestType);
		System.out.println(resBodyAsPrettyString);

	}

}
