package br.com.juliomakita.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.juliomakita.model.Student;
import br.com.juliomakita.service.StudentService;
import br.com.juliomakita.util.FacesUtil;

@Named
@ViewScoped
public class StudentController implements Serializable{

	private static final long serialVersionUID = 1L;

	private Student student;
	
	@Inject
	private StudentService studentService;
	
	@PostConstruct
	public void initialize(){}
	
	public void update(){
		try {
			this.studentService.update(this.student);
			FacesUtil.addMessage("Success!");
		} catch (Exception e) {
			FacesUtil.addMessage("Error");
		}
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
