package com.site;

import com.site.Exception.StudentNotFoundException;
import com.site.model.Student;
import com.site.Services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AOPLabApplication implements CommandLineRunner {

    private final StudentService studentService;

    public AOPLabApplication(StudentService studentService) {
        this.studentService = studentService;
    }

    public static void main(String[] args) {

        SpringApplication.run(AOPLabApplication.class, args);
    }

    @Override
    public void run(String... args) {

        System.out.println();
        System.out.println("======================================");
        System.out.println("       STUDENT MANAGEMENT SYSTEM");
        System.out.println("             USING SPRING AOP");
        System.out.println("======================================");




        System.out.println();
        System.out.println();
        System.out.println("        ADD STUDENTS");
        System.out.println();

        Student student1 = new Student(
                101,
                "Hiwot Solomon",
                "Radiology",
                3,
                "Human Anatomy",
                "A"
        );

        Student student2 = new Student(
                102,
                "Yante Bekele",
                "Computer Science",
                2,
                "Database Management",
                "B+"
        );

        studentService.addStudent(student1);

        System.out.println();

        studentService.addStudent(student2);



        System.out.println();
        System.out.println();
        System.out.println("        DISPLAY STUDENT");
        System.out.println();

        studentService.displayStudent(101);




        System.out.println();
        System.out.println();
        System.out.println("        DISPLAY ALL STUDENTS");
        System.out.println();

        studentService.displayAllStudents();




        System.out.println();
        System.out.println();
        System.out.println("        TEST ERROR HANDLING");
        System.out.println();

        try {

            studentService.displayStudent(999);

        } catch (StudentNotFoundException error) {

            System.out.println();
            System.out.println("Main program caught the error:");
            System.out.println(error.getMessage());
        }




        System.out.println();
        System.out.println();
        System.out.println("======================================");
        System.out.println("          PROGRAM COMPLETED");
        System.out.println("======================================");
    }
}