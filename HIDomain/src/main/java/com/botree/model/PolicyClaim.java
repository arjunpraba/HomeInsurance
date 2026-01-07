package com.botree.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class PolicyClaim {
	@Id
    private String policyKey;
    @ManyToOne
    @JoinColumn(name = "quote_id")
	private Quote quote;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private LocalDate policyStartDate;
    private LocalDate policyEndDate;
    private Integer policyTerm; // in years
    private String policyStatus;
	public String getPolicyKey() {
		return policyKey;
	}
	public void setPolicyKey(String policyKey) {
		this.policyKey = policyKey;
	}

	public Quote getQuote() {
		return quote;
	}
	public void setQuote(Quote quote) {
		this.quote = quote;
	}
	public LocalDate getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(LocalDate policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public LocalDate getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(LocalDate policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	public Integer getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(Integer policyTerm) {
		this.policyTerm = policyTerm;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public PolicyClaim(String policyKey,User user, Quote quote, LocalDate policyStartDate, LocalDate policyEndDate,
			Integer policyTerm, String policyStatus) {
		super();
		this.user=user;
		this.policyKey = policyKey;
		this.quote = quote;
		this.policyStartDate = policyStartDate;
		this.policyEndDate = policyEndDate;
		this.policyTerm = policyTerm;
		this.policyStatus = policyStatus;
	}
	public PolicyClaim() {
		super();
	}

}
