package br.com.juliomakita.service;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.juliomakita.model.Student;
import br.com.juliomakita.repository.StudentRepository;

@ManagedBean
public class StudentService {

	private StudentRepository studentRepository;
	
	public StudentService(){
		this.studentRepository = new StudentRepository();
	}
	
	public Student save(Student student) throws Exception{
		return this.studentRepository.save(student);
	}
	
	public List<Student> findAll(){
		return this.studentRepository.findAll();
	}
}
