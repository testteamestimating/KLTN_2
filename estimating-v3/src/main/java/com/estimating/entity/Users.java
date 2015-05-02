package com.estimating.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="username", length=30)
	private String username;
	
	@Column(name="password", length=128, nullable = false)
	private String password;
	
	@Column(name = "address", length = 50, nullable = true)
	private String address;

	@Column(name = "country", length = 20, nullable = true)
	private String country;

	@Column(name = "city", length = 20, nullable = true)
	private String city;

	@Column(name = "phone", length = 15, nullable = true)
	private String phone;

	@Column(name = "fullname", length = 30, nullable = true)
	private String fullname;
	
	@Embedded
	private DateEmbedded date;

	@ManyToOne
	@JoinColumn(name = "utId", nullable = false)
	private UserType userType;
	
	@OneToMany(mappedBy = "owner_user")
	private List<Shared> owner_shares;
	
	@OneToMany(mappedBy = "share_user")
	private List<Shared> share_user;
	
	@OneToMany(mappedBy = "user")
	private List<Project> project;
	
	/**
	 * @param username
	 * @param password
	 * @param date
	 * @param userType
	 * @param project
	 */
	public Users(String username, String password, DateEmbedded date,
			String address, String city, String phone, String country,
			String fullname, UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.date = date;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.country = country;
		this.fullname = fullname;
		this.userType = userType;
	}

	




	public String getAddress() {
		return address;
	}






	public void setAddress(String address) {
		this.address = address;
	}






	public String getCountry() {
		return country;
	}






	public void setCountry(String country) {
		this.country = country;
	}






	public String getCity() {
		return city;
	}






	public void setCity(String city) {
		this.city = city;
	}






	public String getPhone() {
		return phone;
	}






	public void setPhone(String phone) {
		this.phone = phone;
	}






	public String getFullname() {
		return fullname;
	}






	public void setFullname(String fullname) {
		this.fullname = fullname;
	}






	/**
	 * @return the project
	 */
	public List<Project> getProject() {
		return project;
	}



	/**
	 * @param project the project to set
	 */
	public void setProject(List<Project> project) {
		this.project = project;
	}



	public Users() {
	}
	
	
	
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
	 * @return the date
	 */
	public DateEmbedded getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(DateEmbedded date) {
		this.date = date;
	}

	public Users(String username, String password, DateEmbedded date) {
		super();
		this.username = username;
		this.password = password;
		this.date = date;
	}



	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}



	/**
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}



	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}




