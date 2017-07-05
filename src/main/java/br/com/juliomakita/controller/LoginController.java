package br.com.juliomakita.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.juliomakita.model.User;
import br.com.juliomakita.service.UserService;
import br.com.juliomakita.util.FacesUtil;

@Named
@ViewScoped
public class LoginController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	@Inject
	private UserService userService;
	
	@PostConstruct
	public void initialize(){
		this.user = new User();
	}
	
	public String login(){
		
		User user = this.userService.findByNameAndPassword(this.user);
		
		if(user == null){
			FacesUtil.addMessage("This user doesn't exist!");
			return "login?faces-redirect=true";
		}
		
		HttpSession httpSession = FacesUtil.getHttpSession();
		httpSession.setAttribute("loginUser", user);
		
		return "index?faces-redirect=true";
	}
	
	public void logout(){
		HttpSession httpSession = FacesUtil.getHttpSession();
		httpSession.invalidate();
		FacesUtil.redirect("login.xhtml?faces-redirect=true");
	}
	
	public User userSession(){
		return FacesUtil.getUserSession();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
