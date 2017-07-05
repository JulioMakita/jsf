package br.com.juliomakita.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.juliomakita.model.User;
import br.com.juliomakita.repository.UserRepository;

@Named
public class UserService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserRepository userRepository;
	
	public User findByNameAndPassword(final User user){
		return this.userRepository.findByNameAndPassword(user);
	}

	public void save(final User user) {
		this.userRepository.save(user);
	}

}
