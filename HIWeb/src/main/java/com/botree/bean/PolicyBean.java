package com.botree.bean;

import com.botree.dao.PolicyDAO;
import com.botree.model.PolicyClaim;
import com.botree.model.PolicyDetails;
import com.botree.model.Quote;
import com.botree.model.User;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component("policyBean")
@Scope("session")
public class PolicyBean implements Serializable {

	
	@Autowired
	PolicyDAO p;
    private static final long serialVersionUID = 1L;
    private Quote quote; // Quote to buy
    
    private User user;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	// Card and payment details
    private String cardType;     // Debit Card, Credit Card
    private String vendor;       // HDFC, ICICI, American Express
    private String cardBrand;    // VISA, MASTER, AMEX
    private String cardNumber;
    private String nameOnCard;
    private String cvv;
    private String expiryDate;
    private Double amount;
    private LocalDate policyStartDate;
    private String policyKey;
    private LocalDate policyEndDate;
    private int policyTerm;
    private String policyStatus;
    private Boolean acceptedTerms;

    
    


	public String getPolicyKey() {
		return policyKey;
	}

	public void setPolicyKey(String policyKey) {
		this.policyKey = policyKey;
	}

	public LocalDate getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(LocalDate policyEndDate) {
		this.policyEndDate = policyEndDate;
	}

	public int getPolicyTerm() {
		return policyTerm;
	}

	public void setPolicyTerm(int policyTerm) {
		this.policyTerm = policyTerm;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}
	// Dropdown values
    private List<String> cardTypes;// = p.getCardTypes(); //Arrays.asList("Debit Card", "Credit Card");
    private List<String> vendors;//getVendors();  //Arrays.asList("HDFC", "ICICI", "American Express");
    private List<String> cardBrands;// Arrays.asList("VISA", "MASTER", "AMEX");

    /* =========================
       Initialize Amount from Quote
       ========================= */
    public void init(User user,Quote quote, Double amount) {
        this.quote = quote;
        this.amount = amount;
        this.user=user;
//        cardTypes = p.getCardTypes();
//        vendors = p.getVendors();
//        cardBrands = p.getCardBrands();
    }
    
    public void init1() {
        cardTypes = p.getCardTypes();
        vendors = p.getVendors();
        cardBrands = p.getCardBrands();
    }

    /* =========================
       Buy Policy Action
       ========================= */
    public String buyPolicy(Quote quote) {

//        if (acceptedTerms == null || !acceptedTerms) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                            "Error", "You must accept terms and conditions."));
//
//            return "coverage.xhtml?faces-redirect=true";  
//        }
        cardTypes = p.getCardTypes();
        vendors = p.getVendors();
        cardBrands = p.getCardBrands();

        // Here you can save policy details in DB if needed
        // policyRepository.save(...);
//
//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_INFO,
//                        "Success", "Policy purchased successfully!"));
        return "buyPolicy.xhtml?faces-redirect=true";
    }
    public void buyPolicyDetails() throws IOException {
    	
      if (acceptedTerms == null || !acceptedTerms) {
      FacesContext.getCurrentInstance().addMessage(null,
              new FacesMessage(FacesMessage.SEVERITY_ERROR,
                      "Error", "You must accept terms and conditions."));
      
  }else {
	  PolicyDetails pp=new PolicyDetails(quote,cardType,vendor,cardBrand,cardNumber,nameOnCard,cvv,expiryDate,amount,policyStartDate);

	p.savePolicyDetails(pp);
	policyKey="POL" + System.currentTimeMillis();
	policyTerm=5;
	policyStatus="Active";
	policyEndDate=policyStartDate.plusYears(policyTerm);
	PolicyClaim pc=new PolicyClaim(policyKey,user,quote,policyStartDate,policyEndDate,policyTerm,policyStatus);
	p.savePolicyClaim(pc);
	FacesContext.getCurrentInstance().getExternalContext().redirect("policyConfirmation.xhtml");
	  
  }
    }

    /* =========================
       Getters and Setters
       ========================= */
    public Quote getQuote() { return quote; }
    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public String getVendor() { return vendor; }
    public void setVendor(String vendor) { this.vendor = vendor; }
    public String getCardBrand() { return cardBrand; }
    public void setCardBrand(String cardBrand) { this.cardBrand = cardBrand; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getNameOnCard() { return nameOnCard; }
    public void setNameOnCard(String nameOnCard) { this.nameOnCard = nameOnCard; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public LocalDate getPolicyStartDate() { return policyStartDate; }
    public void setPolicyStartDate(LocalDate policyStartDate) { this.policyStartDate = policyStartDate; }
    public Boolean getAcceptedTerms() { return acceptedTerms; }
    public void setAcceptedTerms(Boolean acceptedTerms) { this.acceptedTerms = acceptedTerms; }

    public List<String> getCardTypes() { return cardTypes; }
    public List<String> getVendors() { return vendors; }
    public List<String> getCardBrands() { return cardBrands; }
}
