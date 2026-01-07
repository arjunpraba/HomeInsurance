package com.botree.bean;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.botree.dao.AdminDAO;
import com.botree.model.Policy;
import com.botree.model.PolicyClaim;
import com.botree.model.User;

import jakarta.faces.context.FacesContext;

@Component
@Scope("session")
public class AdminBean {
	@Autowired
	AdminDAO ad;

	public User user;
	
	public List<Policy> policy;
	
	public List<PolicyClaim> policyClaim;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Policy> getPolicy() {
		return policy;
	}

	public void setPolicy(List<Policy> policy) {
		this.policy = policy;
	}

	public List<PolicyClaim> getPolicyClaim() {
		return policyClaim;
	}

	public void setPolicyClaim(List<PolicyClaim> policyClaim) {
		this.policyClaim = policyClaim;
	}
	
	public void init(User user) {
		this.user=user;
	}
	
	public void viewPolicy() throws IOException {
		policy=ad.getPolicies();
		FacesContext.getCurrentInstance().getExternalContext().redirect("admin_policies.xhtml");
	}
	
	public void viewPolicyClaim() throws IOException {
		policyClaim=ad.getPolicyClaims();
		FacesContext.getCurrentInstance().getExternalContext().redirect("admin_policy_claims.xhtml");
	}
	
}
