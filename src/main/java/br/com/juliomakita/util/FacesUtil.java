package br.com.juliomakita.util;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.juliomakita.model.User;

public class FacesUtil {
	
    public static void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static Map<String, String> getRequestParameterMap(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().getRequestParameterMap();
    }
    
    public static HttpSession getHttpSession(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (HttpSession) facesContext.getExternalContext().getSession(true);
    }
    
    public static User getUserSession(){
    	FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session =  (HttpSession) facesContext.getExternalContext().getSession(true);
		return (User)session.getAttribute("loginUser");
    }
    
    public static void redirect(final String url){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
