package com.site;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {


        System.out.println("\n1. TIGHT COUPLING");
        Student directStudent = new Student();
        directStudent.getHello();

        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("beansConfig.xml")) {


            System.out.println("\n2. SPRING IoC");
            Student basic = context.getBean("basicStudent", Student.class);
            basic.getHello();


            System.out.println("\n3. MANUAL SETTERS");
            basic.setId(101);
            basic.setName("Yohana Keflege");
            basic.setAge(34);
            basic.getDetails();


            System.out.println("\n4. LITERAL / SETTER INJECTION");
            Student literal = context.getBean("literalStudent", Student.class);
            literal.getDetails();
            System.out.println("Student Name: " + literal.getName());

            // 5. Constructor by index
            System.out.println("\n5. CONSTRUCTOR BY INDEX");
            System.out.println(context.getBean("constructorIndex", Student.class));

            // 6. Constructor by position
            System.out.println("\n6. CONSTRUCTOR BY POSITION");
            System.out.println(context.getBean("constructorPosition", Student.class));


            System.out.println("\n7. CONSTRUCTOR BY NAME");
            System.out.println(context.getBean("constructorName", Student.class));


            System.out.println("\n8. OBJECT SETTER INJECTION");
            System.out.println(context.getBean("objectStudent", Student.class));


            System.out.println("\n9. CONSTRUCTOR OBJECT INJECTION");
            System.out.println(context.getBean("constructorObject", Student.class));


            System.out.println("\n10. LIST INJECTION");
            Student listStudent = context.getBean("listStudent", Student.class);
            listStudent.getDetails();


            System.out.println("\n11. ARRAY INJECTION");
            Student arrayStudent = context.getBean("arrayStudent", Student.class);
            arrayStudent.getDetails();


            System.out.println("\n12. SET INJECTION");
            Student setStudent = context.getBean("setStudent", Student.class);
            setStudent.getDetails();


            System.out.println("\n13. SINGLETON SCOPE");
            Student s1 = context.getBean("singletonStudent", Student.class);
            Student s2 = context.getBean("singletonStudent", Student.class);
            System.out.println("Same object: " + (s1 == s2));
            s1.setName("Yohana kefeleg");
            System.out.println("Student 2 name: " + s2.getName());

            System.out.println("\n14. PROTOTYPE SCOPE");
            Student p1 = context.getBean("prototypeStudent", Student.class);
            Student p2 = context.getBean("prototypeStudent", Student.class);
            System.out.println("Same object: " + (p1 == p2));
            p1.setName("Yohana kefeleg");
            System.out.println("Student 2 name: " + p2.getName());
        }
    }
}