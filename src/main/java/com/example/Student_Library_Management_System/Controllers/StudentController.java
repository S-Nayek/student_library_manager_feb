package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.StudentMobileDTO;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    @GetMapping("/get-user")
    public String getNameByEmail(@RequestParam("email") String email){
        return studentService.findNameByEmail(email);
    }
    @PutMapping("/update_mobile")
    public String updateMobNo(@RequestBody StudentMobileDTO studentMobileDTO){
        return  studentService.upDateMobNo(studentMobileDTO);
    }
}
