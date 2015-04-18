package com.estimating.bean;

public class UserBean {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 
	 */
	public UserBean() {
		super();
	}
	/**
	 * @param username
	 * @param password
	 */
	public UserBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}
