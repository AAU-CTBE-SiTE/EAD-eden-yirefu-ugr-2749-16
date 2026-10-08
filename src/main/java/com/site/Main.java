package com.site;

public class Main {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        studentDAO.getAllStudents();

        studentDAO.addStudent(
                "Michael",
                23,
                "Software Engineering",
                "michael@example.com"
        );

        studentDAO.getAllStudents();

        studentDAO.updateStudent(
                1,
                "Eden Updated",
                22,
                "Artificial Intelligence",
                "edennew@example.com"
        );

        studentDAO.getAllStudents();

        studentDAO.searchStudent("Eden");

        studentDAO.deleteStudent(2);

        studentDAO.getAllStudents();
    }
}