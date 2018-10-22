package com.basicauth.template.beans;

import com.basicauth.template.exceptionHandlers.ShouldRedirectToIndexException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@SessionScoped
public class RedirectorBean implements Serializable {
    public void redirectToIndexIfObjectIsNull(Object obj) throws ShouldRedirectToIndexException {
        if(obj == null) throw  new ShouldRedirectToIndexException();
    }

    public void redirectToIndexIfObjectIsNotNull(Object obj) throws ShouldRedirectToIndexException {
        if(obj != null) throw  new ShouldRedirectToIndexException();
    }

    public void redirectToIndexIfListIsEmpty(Collection collection) throws ShouldRedirectToIndexException {
        if(collection.size() <= 0) throw  new ShouldRedirectToIndexException();
    }
}
