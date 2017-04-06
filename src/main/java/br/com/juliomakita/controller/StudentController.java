package br.com.juliomakita.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.juliomakita.model.Student;
import br.com.juliomakita.service.StudentService;
import br.com.juliomakita.util.FacesUtil;
import org.apache.commons.lang3.StringUtils;

@ManagedBean
@ViewScoped
public class StudentController implements Serializable{

	private static final long serialVersionUID = 1L;

	private Student student;
	
	private StudentService studentService;
	
	@PostConstruct
	public void init(){

		this.studentService = new StudentService();

		final Map<String, String> request = FacesUtil.getRequestParameterMap();
		String idStudent = request.get("idStudent");
		
		if(idStudent != null || StringUtils.isNotBlank(idStudent)){
			this.student = studentService.findById(Long.valueOf(idStudent));
		}
	}
	
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
