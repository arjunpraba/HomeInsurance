package com.botree.bean;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.QuoteDAO;
import com.botree.model.Policy;
import com.botree.model.Quote;
import com.botree.model.User;

import jakarta.faces.context.FacesContext;

@Component
@Scope("session")
public class CoverageBean {
	@Autowired
	QuoteDAO qd;
	
	@Autowired
	PolicyBean pb;
	  private static final long serialVersionUID = 1L;

	    private Quote quote;
	    private long quoteId;
	    
	    private User user;
	    
	    public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		private double amount;

	    public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public long getQuoteId() {
			return quoteId;
		}

		public void setQuoteId(long quoteId) {
			this.quoteId = quoteId;
		}

		/* =========================
	       Calculated Fields
	       ========================= */
	    private double yearlyPremium;
	    private double dwellingCoverage;
	    private double detachedStructures;
	    private double personalProperty;
	    private double additionalLivingExpense;
	    private double medicalExpense = 5000; // default
	    private double deductible;

	    /* =========================
	       Initialization
	       ========================= */
	    public void init(Quote quote,User user) throws IOException {
	        this.quote = quote;
	        this.user=user;
	        this.quoteId=quote.getQuoteId();
	        calculateCoverage();
	        FacesContext.getCurrentInstance().getExternalContext().redirect("coverage.xhtml");
	    }


	    private void calculateCoverage() {
	        if (quote == null) return;

	        // 1. Yearly Premium
	        double rate = 5.0; // $5 per $1000 coverage
	        double basePremium = (rate * quote.getMarketValue()) / 1000.0;

	        // Home type adjustment
	        String type = quote.getResidenceType();
	        if ("Single-Family Home".equalsIgnoreCase(type)) {
	            basePremium += basePremium * 0.005; // 0.5%
	        } else if ("Condo".equalsIgnoreCase(type) || "Duplex".equalsIgnoreCase(type)
	                || "Apartment".equalsIgnoreCase(type)) {
	            basePremium += basePremium * 0.006; // 0.6%
	        } else if ("Townhouse".equalsIgnoreCase(type) || "Row house".equalsIgnoreCase(type)) {
	            basePremium += basePremium * 0.007; // 0.7%
	        }
	        this.yearlyPremium = basePremium;

	        // 2. Dwelling Coverage
	        double constructionCost = 120 * quote.getBuiltUpArea();
	        int age = java.time.Year.now().getValue() - quote.getYearBuilt();
	        double ageReduction = 0;
	        if (age < 5) ageReduction = 0.10;
	        else if (age < 10) ageReduction = 0.20;
	        else if (age < 20) ageReduction = 0.30;
	        else ageReduction = 0.50;

	        double homeValue = constructionCost * (1 - ageReduction);
	        this.dwellingCoverage = 0.5 * quote.getMarketValue() + homeValue;

	        // 3. Detached Structures
	        this.detachedStructures = 0.10 * dwellingCoverage;

	        // 4. Personal Property
	        this.personalProperty = 0.60 * dwellingCoverage;

	        // 5. Additional Living Expense
	        this.additionalLivingExpense = 0.20 * dwellingCoverage;

	        // 6. Medical Expense (default $5000) -> already set

	        // 7. Deductible
	        this.deductible = 0.01 * quote.getMarketValue();
	        
	        Policy p=new Policy(quote,user,yearlyPremium,dwellingCoverage,detachedStructures,personalProperty,additionalLivingExpense,medicalExpense,deductible);
	        qd.saveCoverage(p);
	        
	        amount=yearlyPremium+dwellingCoverage+detachedStructures+personalProperty+additionalLivingExpense+medicalExpense+deductible;
	        pb.init(user,quote,amount);
	        
	    }

	    

	    /* =========================
	       Getters Only (Readonly)
	       ========================= */
	    public Quote getQuote() {
	        return quote;
	    }

	    public double getYearlyPremium() {
	        return yearlyPremium;
	    }

	    public double getDwellingCoverage() {
	        return dwellingCoverage;
	    }

	    public double getDetachedStructures() {
	        return detachedStructures;
	    }

	    public double getPersonalProperty() {
	        return personalProperty;
	    }

	    public double getAdditionalLivingExpense() {
	        return additionalLivingExpense;
	    }

	    public double getMedicalExpense() {
	        return medicalExpense;
	    }

	    public double getDeductible() {
	        return deductible;
	    }

}
