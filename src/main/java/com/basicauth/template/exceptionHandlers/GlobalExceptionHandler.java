package com.basicauth.template.exceptionHandlers;

import javax.faces.FacesException;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class GlobalExceptionHandler extends ExceptionHandlerWrapper {

//    private static final Logger logger = Logger.getLogger("com.gbdreports.common.exception.CustomExceptionHandler");
    private final ExceptionHandler wrapped;

    public GlobalExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;

    }
    public void handle() throws FacesException {
        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();

        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context =
                    (ExceptionQueuedEventContext) event.getSource();
            // get the exception from context
            Throwable t = context.getException();


            final FacesContext fc = FacesContext.getCurrentInstance();
//            final ExternalContext externalContext = fc.getExternalContext();
            final Map<String, Object> requestMap = fc.getExternalContext().getRequestMap();
            final ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

            //here you do what ever you want with exception
            try {
                if (t.getCause() instanceof UnauthorizedException) {
                    fc.getExternalContext().redirect("unauthorized.jsf");
                }else if(t.getCause() instanceof ShouldRedirectToIndexException) {
                    fc.getExternalContext().redirect("index.jsf");
                }else{
                    fc.getExternalContext().redirect("error.jsf");
                }
                // remove the comment below if you want to report the error in a jsf error message
                //JsfUtil.addErrorMessage(t.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //remove it from queue
                i.remove();             }
        }
        //parent handle
        getWrapped().handle();
    }
}
