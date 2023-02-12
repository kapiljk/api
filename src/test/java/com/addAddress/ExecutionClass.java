package com.addAddress;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.endpoints.Endpoints;
import com.pojo.AddUserAddress_Input_Pojo;
import com.pojo.AddUserAddress_Output_Pojo;
import com.pojo.CityList;
import com.pojo.CityList_Input_Pojo;
import com.pojo.CityList_Outpu_Pojo;
import com.pojo.DeleteAddress_Input_Pojo;
import com.pojo.DeleteUserAddress_Output_Pojo;
import com.pojo.GetUserAddress_Output_Pojo;
import com.pojo.SearchProduct_Input_Pojo;
import com.pojo.SearchProduct_Output_Pojo;
import com.pojo.StateList;
import com.pojo.StateList_Output_Pojo;
import com.pojo.UpdateUserAddress_Input_Pojo;
import com.pojo.UpdateUserAddress_Output_Pojo;
import com.readdataAssured.BaseClass;
import com.readdataAssured.Login_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ExecutionClass extends BaseClass {
	String logtoken;
	int stateIDNum;
	String stateID;
	int cityID;
	String address_id;
	int address_idNum;

	
	
	@Test(priority=8)
	public void searchProduct() {
		List<Header> listHeaders = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		SearchProduct_Input_Pojo product_Input_Pojo = new SearchProduct_Input_Pojo("nuts");
		addBody(product_Input_Pojo);
		Response response = requestType("POST", Endpoints.SEARCHPRODUCT);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verifying Status Code");
		SearchProduct_Output_Pojo searchProduct_Output_Pojo = response.as(SearchProduct_Output_Pojo.class);
		String actMessage = searchProduct_Output_Pojo.getMessage();
		Assert.assertEquals(actMessage, "OK", "Verifying search prodcut message");
		
	}
	@Test(priority = 6)
	public void getUserAddress() {
		List<Header> listHeaders = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		Response resposne = requestType("GET", Endpoints.GETUSERADDRESS);
		int statusCode = getStatusCode(resposne);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verifying Status Code");

		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = resposne.as(GetUserAddress_Output_Pojo.class);
		String actMessage = getUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(actMessage, "OK", "Verifying getuser success message as ok");
	}

	@Test(priority = 7)
	public void deleteUserAddress() {
		List<Header> listHeaders = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		DeleteAddress_Input_Pojo address_Input_Pojo = new DeleteAddress_Input_Pojo(address_id);
		addBody(address_Input_Pojo);
		Response response = requestType("DELETE", Endpoints.DELETEUSERADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verifying Status Code");
		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String actMesaage = deleteUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(actMesaage, "Address deleted successfully", "verifying success message after delete");

	}

	@Test(priority = 5)
	public void updateUserAddress() {// add address to existing user
		List<Header> listHeaders = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		
		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(address_id,
				"kapil", "palepu", "7993964845", "JoshithKeyvan", stateIDNum, cityID, 101, "500015", "Hyderabad",
				"Home");
		addBody(updateUserAddress_Input_Pojo);
		Response response = requestType("PUT", Endpoints.UPDATEUSERADDRESS);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "Verifying Status Code");

		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = response.as(UpdateUserAddress_Output_Pojo.class);
		String actMsg = updateUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(actMsg, "Address updated successfully", "verifying Address updated successfully msg");
	}

	@Test(priority = 2)
	public void getStateList() {
		addHeader("accept", "application/json");
		Response response = requestType("GET", Endpoints.USERSTATELIST);
		int actStatusCode = getStatusCode(response);
		System.out.println(actStatusCode);
		Assert.assertEquals(actStatusCode, 200, "Verifying Status Code");
		StateList_Output_Pojo stateList_Output_Pojo = response.as(StateList_Output_Pojo.class);
		ArrayList<StateList> stateList = stateList_Output_Pojo.getData();
		for (StateList eachStateList : stateList) {
			String actStateName = eachStateList.getName();
			if (actStateName.equals("Telangana")) {
				stateIDNum = eachStateList.getId();
				stateID = String.valueOf(stateIDNum);
				Assert.assertEquals(actStateName, "Telangana", "verifying state name as Telangana");
				System.out.println(stateID);
				break;
			}
		}
	}

	@Test(priority = 3)
	public void getCityList() {
		List<Header> listHeaders = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h3 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		
		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(stateID);
		addBody(cityList_Input_Pojo);
		
		Response response = requestType("POST", Endpoints.USERCITYLIST);
		int actStatusCode = getStatusCode(response);
		System.out.println(actStatusCode);
		Assert.assertEquals(actStatusCode, 200, "verifying status code as 200");
		CityList_Outpu_Pojo cityList_Outpu_Pojo = response.as(CityList_Outpu_Pojo.class);
		ArrayList<CityList> cityList = cityList_Outpu_Pojo.getData();
		for (CityList eachCityList : cityList) {
			String actCityName = eachCityList.getName();
			if (actCityName.equals("Hyderabad")) {
				cityID = eachCityList.getId();
				Assert.assertEquals(actCityName, "Hyderabad", "Verifying city name as Hyderabad");
				break;
			}
		}
	}

	@Test(priority = 1)
	public void login() {// login with existing username and password.
		// 1. Add header
		addHeader("accept", "application/json");
		// 2. Basic authentication
		addBaseAuth("palepu.jk@gmail.com", "Jehovah@jk12");
		// 3. Method Type
		Response response = requestType("POST", Endpoints.POSTMANBASICAUTHLOGIN);
		int actStatusCode = getStatusCode(response);
		System.out.println(actStatusCode);
		Assert.assertEquals(actStatusCode, 200, "Verifying Status Code");
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String actFirst_name = login_Output_Pojo.getData().getFirst_name();
		String actEmail = login_Output_Pojo.getData().getEmail();
		Assert.assertEquals(actFirst_name, "kapil", "Verifying First Name");
		Assert.assertEquals(actEmail, "palepu.jk@gmail.com", "Verifying Email Id");
		logtoken = login_Output_Pojo.getData().getLogtoken();
	}

	@Test(priority = 4)
	public void addUserAddress() {// add address to existing user
		List<Header> listHeaders = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		AddUserAddress_Input_Pojo address_Input_Pojo = new AddUserAddress_Input_Pojo("kapil", "palepu", "7993964845",
				"JoshithKeyvan", stateIDNum, cityID, 101, "500015", "Hyderabad", "Home");
		addBody(address_Input_Pojo);
		Response response = requestType("POST", "https://omrbranch.com/api/addUserAddress");
		int actStatusCode = getStatusCode(response);
		System.out.println(actStatusCode);
		Assert.assertEquals(actStatusCode, 200, "Verifying response code");
		AddUserAddress_Output_Pojo ads = response.as(AddUserAddress_Output_Pojo.class);
		String actMsg = ads.getMessage();
		address_idNum = ads.getAddress_id();
		address_id = String.valueOf(address_idNum);
		System.out.println("Address ID is :" + address_idNum);
		Assert.assertEquals(actMsg, "Address added successfully", "Verifying Address added successfully");

	}

}
