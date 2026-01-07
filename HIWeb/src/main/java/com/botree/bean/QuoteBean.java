package com.botree.bean;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.QuoteDAO;
import com.botree.model.Quote;
import com.botree.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Component
@Scope("session")
public class QuoteBean {
	@Autowired
	QuoteDAO qd;
	@Autowired
	CoverageBean cb;
	
    private User user;

	private String residenceType;
    private String residenceUse;
    private Integer builtUpArea;
    private Double marketValue;
    private Integer yearBuilt;
    private String dwellingStyle;
    private String roofMaterial;
    private String garageType;
    private Boolean swimmingPool;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zip;


    private List<String> residenceTypes;
    private List<String> residenceUses;
    private List<String> dwellingStyles;
    private List<String> roofMaterials;
    private List<String> garageTypes;

    
    
    
    @PostConstruct
    public void init() {

        residenceTypes = qd.getresidenceTypes();


        residenceUses = qd.getResidenceUses();

        dwellingStyles = qd.getDwellingStyle();

        roofMaterials = qd.getRoodMeterial();

        garageTypes = qd.getGarageTyps();


    }
    public void init1(User user) {
    	this.user=user;
    }


    public void generateQuote() throws IOException {

    	System.out.println("USER IN QUOTEBEAN = " + user);

        Quote quote = new Quote(
        		user,
                residenceType,
                residenceUse,
                builtUpArea,
                marketValue,
                yearBuilt,
                dwellingStyle,
                roofMaterial,
                garageType,
                swimmingPool,
                addressLine1,
                addressLine2,
                city,
                zip
        );

        qd.generateQuote(quote);
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Quote Generated Successfully",
                        "Quote created for " + city
                )
        );
        
        cb.init(quote,user);
        
    }

    /* =========================
       Getters and Setters
       ========================= */

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

    public List<String> getResidenceTypes() {
        return residenceTypes;
    }

    public List<String> getResidenceUses() {
        return residenceUses;
    }

    public List<String> getDwellingStyles() {
        return dwellingStyles;
    }

    public List<String> getRoofMaterials() {
        return roofMaterials;
    }

    public List<String> getGarageTypes() {
        return garageTypes;
    }



}
