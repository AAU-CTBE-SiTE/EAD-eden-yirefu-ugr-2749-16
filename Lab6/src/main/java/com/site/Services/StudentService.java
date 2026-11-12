package com.site.Services;

import com.site.Exception.StudentNotFoundException;
import com.site.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {

        students.add(student);

        System.out.println("Student added successfully!");
    }

    public Student displayStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {

                System.out.println("Student Found!");
                System.out.println();
                System.out.println(student);

                return student;
            }
        }

        throw new StudentNotFoundException(
                "Student with ID " + id + " was not found!"
        );
    }

    public List<Student> displayAllStudents() {

        System.out.println("ALL STUDENTS");
        System.out.println();

        for (Student student : students) {

            System.out.println(student);
            System.out.println("--------------------------------");
        }

        return students;
    }
}
