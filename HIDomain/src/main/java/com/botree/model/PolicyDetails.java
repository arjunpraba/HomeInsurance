package com.botree.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "policy_details")
public class PolicyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    /* =========================
       Relationship
       ========================= */
    @ManyToOne
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quote;   // Quote to buy

    /* =========================
       Payment Details
       ========================= */

    @Column(name = "card_type", nullable = false)
    private String cardType;      // Debit Card, Credit Card

    @Column(name = "vendor", nullable = false)
    private String vendor;        // HDFC, ICICI, American Express

    @Column(name = "card_brand", nullable = false)
    private String cardBrand;     // VISA, MASTER, AMEX

    @Column(name = "card_number", nullable = false, length = 20)
    private String cardNumber;

    @Column(name = "name_on_card", nullable = false)
    private String nameOnCard;

    @Column(name = "cvv", nullable = false, length = 4)
    private String cvv;

    @Column(name = "expiry_date", nullable = false)
    private String expiryDate;

    @Column(name = "amount", nullable = false)
    private Double amount;

    /* =========================
       Policy Info
       ========================= */

    @Column(name = "policy_start_date", nullable = false)
    private LocalDate policyStartDate;

    /* =========================
       Constructors
       ========================= */

    public PolicyDetails() {
    }

    public PolicyDetails(Quote quote,
                         String cardType,
                         String vendor,
                         String cardBrand,
                         String cardNumber,
                         String nameOnCard,
                         String cvv,
                         String expiryDate,
                         Double amount,
                         LocalDate policyStartDate) {

        this.quote = quote;
        this.cardType = cardType;
        this.vendor = vendor;
        this.cardBrand = cardBrand;
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.amount = amount;
        this.policyStartDate = policyStartDate;
    }

    /* =========================
       Getters and Setters
       ========================= */

    public Long getPolicyId() {
        return policyId;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPolicyStartDate() {
        return policyStartDate;
    }

    public void setPolicyStartDate(LocalDate policyStartDate) {
        this.policyStartDate = policyStartDate;
    }
}
