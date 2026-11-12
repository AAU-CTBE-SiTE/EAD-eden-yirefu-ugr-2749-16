package com.site.model;

public class Student {

    private int id;
    private String name;
    private String department;
    private int year;
    private String course;
    private String grade;

    public Student() {
    }

    public Student(
            int id,
            String name,
            String department,
            int year,
            String course,
            String grade
    ) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.year = year;
        this.course = course;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getYear() {
        return year;
    }

    public String getCourse() {
        return course;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Department: " + department + "\n" +
                "Year: " + year + "\n" +
                "Course: " + course + "\n" +
                "Grade: " + grade;
    }
}