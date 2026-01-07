package com.botree.model;

import jakarta.persistence.*;

@Entity
@Table(name = "policy")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    @OneToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double yearlyPremium;
    private double dwellingCoverage;
    private double detachedStructures;
    private double personalProperty;
    private double additionalLivingExpense;
    private double medicalExpense;
    private double deductible;

    // Optional: purchase date, customer info, etc.

    public Policy() {}

    public Policy(Quote quote,User user,double yearlyPremium, double dwellingCoverage, double detachedStructures,
                  double personalProperty, double additionalLivingExpense, double medicalExpense, double deductible) {
        this.quote = quote;
        this.user=user;
        this.yearlyPremium = yearlyPremium;
        this.dwellingCoverage = dwellingCoverage;
        this.detachedStructures = detachedStructures;
        this.personalProperty = personalProperty;
        this.additionalLivingExpense = additionalLivingExpense;
        this.medicalExpense = medicalExpense;
        this.deductible = deductible;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public double getYearlyPremium() {
		return yearlyPremium;
	}

	public void setYearlyPremium(double yearlyPremium) {
		this.yearlyPremium = yearlyPremium;
	}

	public double getDwellingCoverage() {
		return dwellingCoverage;
	}

	public void setDwellingCoverage(double dwellingCoverage) {
		this.dwellingCoverage = dwellingCoverage;
	}

	public double getDetachedStructures() {
		return detachedStructures;
	}

	public void setDetachedStructures(double detachedStructures) {
		this.detachedStructures = detachedStructures;
	}

	public double getPersonalProperty() {
		return personalProperty;
	}

	public void setPersonalProperty(double personalProperty) {
		this.personalProperty = personalProperty;
	}

	public double getAdditionalLivingExpense() {
		return additionalLivingExpense;
	}

	public void setAdditionalLivingExpense(double additionalLivingExpense) {
		this.additionalLivingExpense = additionalLivingExpense;
	}

	public double getMedicalExpense() {
		return medicalExpense;
	}

	public void setMedicalExpense(double medicalExpense) {
		this.medicalExpense = medicalExpense;
	}

	public double getDeductible() {
		return deductible;
	}

	public void setDeductible(double deductible) {
		this.deductible = deductible;
	}

    // Getters and setters
}
