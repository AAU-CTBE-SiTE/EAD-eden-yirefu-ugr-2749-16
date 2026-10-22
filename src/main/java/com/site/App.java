
package com.site;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class App {


    public static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("studentPU");


    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public static void createStudent(Student student) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(student);
            tx.commit();

            System.out.println("Student created: " + student);

        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }


    public static Student getStudentById(int id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }


    public static void updateStudentName(String newName, int id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Student student = em.find(Student.class, id);

            if (student == null) {
                tx.rollback();
                System.out.println("Student not found: " + id);
                return;
            }

            student.setName(newName);
            tx.commit();

            System.out.println("Student name updated.");

        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }


    public static void updateStudentNameAndTech(
            String newName, String newTech, int id) {

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Student student = em.find(Student.class, id);

            if (student == null) {
                tx.rollback();
                System.out.println("Student not found: " + id);
                return;
            }

            student.setName(newName);
            student.setTech(newTech);

            tx.commit();

            System.out.println(
                    "Student name and technology updated.");

        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }


    public static void deleteStudentById(int id) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Student student = em.find(Student.class, id);

            if (student == null) {
                tx.rollback();
                System.out.println("Student not found: " + id);
                return;
            }

            em.remove(student);
            tx.commit();

            System.out.println("Student deleted: " + id);

        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }


    public static void testContains(int id) {
        EntityManager em = getEntityManager();

        try {
            Student student = em.find(Student.class, id);

            if (student == null) {
                System.out.println("Student not found: " + id);
                return;
            }

            System.out.println(
                    "Before detach: " + em.contains(student));

            em.detach(student);

            System.out.println(
                    "After detach: " + em.contains(student));

        } finally {
            em.close();
        }
    }


    public static void main(String[] args) {
        try {

            Student student = new Student(
                    "Feven Tamene", "Python");

            createStudent(student);


            int id = student.getId();
            System.out.println("Generated ID: " + id);


            System.out.println(
                    "Fetched: " + getStudentById(id));


            updateStudentName("Redite", id);

            System.out.println(
                    "After name update: " + getStudentById(id));


            updateStudentNameAndTech(
                    "Redite", "Java", id);

            System.out.println(
                    "After updating both fields: "
                            + getStudentById(id));


            testContains(id);


            System.out.println(
                    "Final student: " + getStudentById(id));



             deleteStudentById(id);
             System.out.println("After delete: "
                     + getStudentById(id));

        } finally {

            if (emf.isOpen()) {
                emf.close();
            }
        }
    }
}
