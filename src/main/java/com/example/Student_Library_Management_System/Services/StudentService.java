package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.StudentMobileDTO;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public String createStudent(Student student){

        Card card =new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudentVariableName(student);

        student.setCard(card);

        studentRepository.save(student);//by cascading card will save automatically

        return "student and card added successfully";
    }

    public String findNameByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        return student.getName();
    }
    public String upDateMobNo(StudentMobileDTO studentReq){

        Student originalStudent=(studentRepository.findById(studentReq.getId()).get());
        originalStudent.setMobileNo(studentReq.getMobileNo());
        studentRepository.save(originalStudent);

        return "student has been updated successfully";
    }
}
