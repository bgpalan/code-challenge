package com.wire.qa.migration.io;

/**
 * A class representing a migrated name response . This is a simple holder for
 * response .
 * 
 * This class is a bit verbose and contains only getters and setters , Which can
 * be tacked possibly by using Lombok plugin(which will auto generate getters
 * and setters)
 * 
 * @author bharat.gopalan
 *
 */
public class NameResponse {
	private String salutation;
	private String firstname;
	private String middlename;
	private String lastname;
	private String suffix;

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
