package com.basicauth.template.beans;

import com.basicauth.template.domain.Role;
import com.basicauth.template.domain.User;
import com.basicauth.template.exceptionHandlers.UnauthorizedException;
import com.basicauth.template.service.AuthServiceBean;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthBean implements Serializable {

    @Inject
    private AuthServiceBean authServiceBean;

    private User currentUser;
    private User unauthenticatedUser = new User();

    public void authenticateUser(){
        this.currentUser = authServiceBean.getAuthenticatedUser(unauthenticatedUser);
    }

    public void logOutUser() throws IOException {
        this.currentUser = null;
    }

    private String redirectToRoleStartPage() {
        if(isUserOfRole(Role.ADMIN)) {
            return "adminOverview.jsf";
        }else if(isUserOfRole(Role.USER)) {
            return "index.jsf";
        }else{
            return "unauthorized.jsf";
        }
    }

    private boolean isUserOfRole(Role role){
        return currentUser != null && this.currentUser.getRole().equals(role);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User getUnauthenticatedUser() {
        return unauthenticatedUser;
    }

    public void setUnauthenticatedUser(User unauthenticatedUser) {
        this.unauthenticatedUser = unauthenticatedUser;
    }

    public boolean isSomeoneLoggedIn(){
        if(currentUser == null){
            return false;
        }
        return true;
    }

    public boolean checkIfUserIsAuthorizedAsAdmin(boolean shouldRedirect) throws IOException, UnauthorizedException {
        if(!isUserOfRole(Role.ADMIN)){
            if(shouldRedirect) {
                throw new UnauthorizedException();
            }
            return false;
        }
        return true;
    }

    public boolean checkIfUserIsAuthorizedAsUser(boolean shouldRedirect) throws IOException, UnauthorizedException {
        if(!isUserOfRole(Role.USER)){
            if(shouldRedirect) {
                throw new UnauthorizedException();
            }
            return false;
        }
        return true;
    }

    public String processLogout() {
        return "index.jsf?faces-redirect=true";
    }

    public String prepareLogin() {
        return "login.jsf";
    }

}
