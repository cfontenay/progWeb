package com.binary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("FirstName")
	public String firstName;
	@JsonProperty("LastName")
	public String lastName;
	@JsonProperty("ID")
	public long id;
}
