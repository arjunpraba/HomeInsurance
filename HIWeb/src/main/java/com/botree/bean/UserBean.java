package com.botree.bean;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.UserDAO;
import com.botree.model.User;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import com.botree.dao.*;

@Component
@Scope("session")
public class UserBean {
	
	@Autowired
	UserDAO ud;
	
	@Autowired
	QuoteBean qb;
	
	@Autowired
	RetriveQuoteBean rb;
	
	@Autowired
	AdminBean ab;
	
	private User user;
	private String currectUser;
	public String getCurrectUser() {
		return currectUser;
	}
	public void setCurrectUser(String currectUser) {
		this.currectUser = currectUser;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	private String reEnterPassword;

	public String getReEnterPassword() {
		return reEnterPassword;
	}
	public void setReEnterPassword(String reEnterPassword) {
		this.reEnterPassword = reEnterPassword;
	}

	private String userId;
	private String password;
	private String userType;

	private String firstName;
	private String lastName;

	private Date dob;

	private String ssn;
	private String email;
	private String mobileNumber;

	private String address1;
	private String address2;
	private String city;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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

	public void register() throws IOException {
	    	userType="admin";
		 User use=new User(userId,password,userType,firstName,lastName,dob,ssn,email,mobileNumber,address1,address2,city,zip);
        if(!use.getPassword().equals(reEnterPassword)) {
            FacesContext.getCurrentInstance()
                .addMessage(null,
                  new FacesMessage("Passwords do not match"));
        }else {
		ud.register(use);
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }	
	}
	public void login() throws IOException {
		User use=new User(userId,password);
	    User users=ud.getUser(userId);
	    if(users.getUserType().equals("admin")) {
	    ab.init(users);
	     FacesContext.getCurrentInstance().getExternalContext().redirect("admin_home.xhtml");
	    }
		if(ud.authenticate(use)) {
			this.user=ud.getUser(userId);
			firstName=ud.getUserType(use);
			qb.init1(user);
			rb.init(user);
			FacesContext.getCurrentInstance().getExternalContext().redirect("user_home.xhtml");
		}
	}
	
   public void goToGetQuote() throws IOException {
	   FacesContext.getCurrentInstance().getExternalContext().redirect("get_quote.xhtml");
   }
   
   public void goToRetrieveQuote() throws IOException {
	   rb.retrive();
	   rb.init(this.user);
	   FacesContext.getCurrentInstance().getExternalContext().redirect("retrive_quote.xhtml");
   }
	
}
