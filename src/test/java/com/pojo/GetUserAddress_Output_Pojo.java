package com.pojo;

import java.util.ArrayList;

public class GetUserAddress_Output_Pojo {

	
	 private int status;
	    private String message;
	    public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public ArrayList<UserAddress> getData() {
			return data;
		}
		public void setData(ArrayList<UserAddress> data) {
			this.data = data;
		}
		private ArrayList<UserAddress> data;
	
}
