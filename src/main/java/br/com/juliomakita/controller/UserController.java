package br.com.juliomakita.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.juliomakita.model.User;
import br.com.juliomakita.service.UserService;
import br.com.juliomakita.util.FacesUtil;

@Named
@ViewScoped
public class UserController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	@Inject 
	private UserService userService;

	@PostConstruct
	public void initialize(){
		this.user = new User();
	}
	
	public void save(){
		this.userService.save(user);
		FacesUtil.addMessage("User Saved!");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
