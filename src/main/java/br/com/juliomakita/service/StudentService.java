package br.com.juliomakita.service;

import java.util.List;

import br.com.juliomakita.model.Student;
import br.com.juliomakita.repository.StudentRepository;

public class StudentService {

	private StudentRepository studentRepository;
	
	public StudentService(){
		this.studentRepository = new StudentRepository();
	}
	
	public Student save(Student student) throws Exception{
		return this.studentRepository.save(student);
	}
	
	public Student findById(final Long id){
		return this.studentRepository.get(Student.class, id);
	}
	
	public void update(final Student student)throws Exception{
		this.studentRepository.update(student);
	}
	
	public void delete(final Student student){
		this.studentRepository.delete(student);
	}
	
	public List<Student> findAll(){
		return this.studentRepository.findAll();
	}
}
