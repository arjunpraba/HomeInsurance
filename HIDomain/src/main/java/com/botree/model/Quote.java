package com.botree.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quote")
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quote_id")
	private Long quoteId;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;

	@Column(name = "residence_type", nullable = false, length = 50)
	private String residenceType;

	@Column(name = "residence_use", nullable = false, length = 50)
	private String residenceUse;

	@Column(name = "built_up_area", nullable = false)
	private Integer builtUpArea;

	@Column(name = "market_value", nullable = false)
	private Double marketValue;

	@Column(name = "year_built", nullable = false)
	private Integer yearBuilt;

	@Column(name = "dwelling_style", nullable = false, length = 50)
	private String dwellingStyle;

	@Column(name = "roof_material", nullable = false, length = 50)
	private String roofMaterial;

	@Column(name = "garage_type", nullable = false, length = 50)
	private String garageType;

	@Column(name = "swimming_pool", nullable = false)
	private Boolean swimmingPool;

	/*
	 * ========================= Address Details =========================
	 */
	@Column(name = "address_line1", nullable = false, length = 100)
	private String addressLine1;

	@Column(name = "address_line2", length = 100)
	private String addressLine2;

	@Column(name = "city", nullable = false, length = 50)
	private String city;

	@Column(name = "zip", nullable = false, length = 15)
	private String zip;

	/*
	 * ========================= Constructors =========================
	 */

	/** Mandatory no-args constructor for JPA */
	public Quote() {
	}

	/** Full-args constructor for application use */
	public Quote(User user,String residenceType, String residenceUse, Integer builtUpArea, Double marketValue, Integer yearBuilt,
			String dwellingStyle, String roofMaterial, String garageType, Boolean swimmingPool, String addressLine1,
			String addressLine2, String city, String zip) {
		this.user=user;
		this.residenceType = residenceType;
		this.residenceUse = residenceUse;
		this.builtUpArea = builtUpArea;
		this.marketValue = marketValue;
		this.yearBuilt = yearBuilt;
		this.dwellingStyle = dwellingStyle;
		this.roofMaterial = roofMaterial;
		this.garageType = garageType;
		this.swimmingPool = swimmingPool;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.zip = zip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*
	 * ========================= Getters and Setters =========================
	 */
	public Long getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(Long quoteId) {
		this.quoteId = quoteId;
	}

	public String getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}

	public String getResidenceUse() {
		return residenceUse;
	}

	public void setResidenceUse(String residenceUse) {
		this.residenceUse = residenceUse;
	}

	public Integer getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(Integer builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public Double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}

	public Integer getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(Integer yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public String getDwellingStyle() {
		return dwellingStyle;
	}

	public void setDwellingStyle(String dwellingStyle) {
		this.dwellingStyle = dwellingStyle;
	}

	public String getRoofMaterial() {
		return roofMaterial;
	}

	public void setRoofMaterial(String roofMaterial) {
		this.roofMaterial = roofMaterial;
	}

	public String getGarageType() {
		return garageType;
	}

	public void setGarageType(String garageType) {
		this.garageType = garageType;
	}

	public Boolean getSwimmingPool() {
		return swimmingPool;
	}

	public void setSwimmingPool(Boolean swimmingPool) {
		this.swimmingPool = swimmingPool;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
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
}
