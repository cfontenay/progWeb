package tpProgWeb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	public String firstName;
	public String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public User() {
		
	}
	/*
    @JsonProperty("FirstName")
	public String firstName;
	@JsonProperty("LastName")
	public String lastName;
	*/
}
