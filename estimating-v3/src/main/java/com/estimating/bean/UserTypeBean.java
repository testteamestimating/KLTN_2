package com.estimating.bean;

public class UserTypeBean {
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;
	/**
	 * @param id
	 * @param description
	 */
	public UserTypeBean(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	/**
	 * 
	 */
	public UserTypeBean() {
		super();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
