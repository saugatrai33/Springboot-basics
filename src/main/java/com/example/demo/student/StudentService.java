package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    List<Student> getStudent() {
        return studentRepository.findAll();
    }

    void addNewStudent(Student newStudent) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(newStudent.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email Already Taken.");
        }
        studentRepository.save(newStudent);
    }

    void deleteStudentById(Long id) {
        boolean existsById = studentRepository.existsById(id);
        if (!existsById) {
            throw new IllegalStateException("Student with id " + id + "does not exist.");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with id "
                        + id + "does not exist."));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("Email Already Taken.");
            }
            student.setEmail(email);
        }

        studentRepository.updateStudent(id, name, email);
    }
}
