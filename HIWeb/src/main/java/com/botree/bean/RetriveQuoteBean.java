package com.botree.bean;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.QuoteDAO;
import com.botree.dao.RetriveDAO;
import com.botree.model.Policy;
import com.botree.model.PolicyClaim;
import com.botree.model.Quote;
import com.botree.model.User;

import jakarta.faces.context.FacesContext;

@Component
@Scope("session")
public class RetriveQuoteBean {
	
	@Autowired
	RetriveDAO rd;
	@Autowired
	PolicyBean pb;
	
	@Autowired
	QuoteDAO qd;
	
	private String claimReason;
	
	public String getClaimReason() {
		return claimReason;
	}

	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
	}

	private User user;
	
	private List<Quote> quoteList;
	private double amount;
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	private Quote quote;
	private List<Policy> policies;
	
	private Policy policy;
	
	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}

	public void init(User user) {
		this.user=user;
	}
	
	private PolicyClaim policyClaim;
	
	public void retrive() throws IOException {
		this.quoteList=rd.getQuotes(user);
		
		System.out.println("got it");
		System.out.println(this.quoteList);
		System.out.println(user);
		System.out.println("this is");
		FacesContext.getCurrentInstance().getExternalContext().redirect("retrive_quote.xhtml");
	}
	
	public void getPolicies1() throws IOException {
		this.policies=rd.getAllPolicies(user);

		FacesContext.getCurrentInstance().getExternalContext().redirect("policies.xhtml");
	
	
	}
	
	public void viewQuote(Quote q) throws IOException {
		this.quote=q;
		FacesContext.getCurrentInstance().getExternalContext().redirect("view_quote.xhtml");
	}
	
	public String claimPolicy(Policy policy) {
	    this.policy = policy;
	    return "policyDetails.xhtml?faces-redirect=true";
//        amount=policy.getYearlyPremium()+policy.getDwellingCoverage()+policy.getDetachedStructures()+policy.getPersonalProperty()+policy.getAdditionalLivingExpense()+policy.getMedicalExpense()+policy.getDeductible();
//        pb.init(user,quote,amount);
//        
//        pb.init1();
        
        // Here you can save policy details in DB if needed
        // policyRepository.save(...);
//
//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_INFO,
//                        "Success", "Policy purchased successfully!"));
//        return "policyDetails.xhtml?faces-redirect=true";
        
		
	}
	
	public String submitClaim() {
		policyClaim=rd.updatepolicyclaim(policy);
		return "claim_confirmation.xhtml?faces-redirect=true";
		
	}

	public PolicyClaim getPolicyClaim() {
		return policyClaim;
	}

	public void setPolicyClaim(PolicyClaim policyClaim) {
		this.policyClaim = policyClaim;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Quote> getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(List<Quote> quoteList) {
		this.quoteList = quoteList;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}
	


}
