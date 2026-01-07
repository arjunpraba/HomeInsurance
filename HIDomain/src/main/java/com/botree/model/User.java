package com.botree.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userType;
    public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name="first_name", nullable=false)
    private String firstName;

    @Column(name="last_name", nullable=false)
    private String lastName;

    @Column(name="dob", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name="ssn", nullable=false, unique=true)
    private String ssn;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(name="mobile_number", nullable=false)
    private String mobileNumber;

    @Column(name="address1", nullable=false)
    private String address1;

    @Column(name="address2")
    private String address2;

    @Column(nullable=false)
    private String city;

    @Column(nullable=false)
    private String zip;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	public User(String userId, String password, String userType, String firstName, String lastName, Date dob,
			String ssn, String email, String mobileNumber, String address1, String address2, String city, String zip) {
		super();
		this.userId = userId;
		this.password = password;
		this.userType = userType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.ssn = ssn;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.zip = zip;
	}

	public User() {
		
	}

	public User(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	
    
    
    
}