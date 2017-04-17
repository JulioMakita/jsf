package br.com.juliomakita.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.juliomakita.model.Student;
import br.com.juliomakita.service.StudentService;
import br.com.juliomakita.util.FacesUtil;

@Named
@ViewScoped
public class HomeController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Student> students;
	
	private Student student;
	
	@Inject
	private StudentService studentService;
	
	@PostConstruct
	public void initialize(){
		
		this.students = this.studentService.findAll();
		
		if(students == null){
			this.students = new ArrayList<>();
		}
		
		clear();
	}
	
	public void clear(){
		this.student = new Student();
	}
	
	public void save(){
		
		if(!validate()){
			return;
		}
		
		try {
			this.studentService.save(this.student);
			FacesUtil.addMessage("Student Saved!");
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addMessage("Error");
		}		
	}
	
	public void delete(final Student student){
		this.studentService.delete(student);
		FacesUtil.addMessage("Delete Sucess!");
		initialize();
	}
	
	private boolean validate(){
		
		if(student.getName() == null || StringUtils.isBlank(student.getName())){
			FacesUtil.addMessage("Put the name");
			return false;
		}
		
		if(student.getAge() == null){
			FacesUtil.addMessage("Put the age");
			return false;
		}
		
		return true;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
