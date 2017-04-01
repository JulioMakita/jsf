package br.com.juliomakita.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.com.juliomakita.model.Student;
import br.com.juliomakita.service.StudentService;

@ManagedBean
@ViewScoped
public class HomeController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Student> students;
	
	private Student student;
	
	@ManagedProperty(value="#{studentService}")
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
			this.students.add(this.student);
			addMessage("Salvo com Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			addMessage("Erro ao Salvar");
		}		
	}
	
	private boolean validate(){
		
		if(student.getName() == null || "".equals(student.getName())){
			addMessage("Digite o nome");
			return false;
		}
		
		if(student.getAge() == null){
			addMessage("Digite a idade");
			return false;
		}
		
		return true;
	}
	
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
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

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
}
