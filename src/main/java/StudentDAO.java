package com.site;

import java.sql.*;

public class StudentDAO {

    public void getAllStudents() {

        String sql = "SELECT * FROM students";

        try (
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql)
        ) {

            System.out.println("\n===== ALL STUDENTS =====");

            while (result.next()) {
                System.out.println(
                        result.getInt("id") + " | " +
                                result.getString("name") + " | " +
                                result.getInt("age") + " | " +
                                result.getString("department") + " | " +
                                result.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ADD STUDENT
    public void addStudent(String name, int age, String department, String email) {

        String sql = """
                INSERT INTO students (name, age, department, email)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, department);
            statement.setString(4, email);

            statement.executeUpdate();

            System.out.println("\nStudent added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // UPDATE STUDENT
    public void updateStudent(
            int id,
            String name,
            int age,
            String department,
            String email) {

        String sql = """
                UPDATE students
                SET name = ?, age = ?, department = ?, email = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, department);
            statement.setString(4, email);
            statement.setInt(5, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("\nStudent updated successfully!");
            } else {
                System.out.println("\nStudent ID not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("\nStudent deleted successfully!");
            } else {
                System.out.println("\nStudent ID not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // SEARCH STUDENT
    public void searchStudent(String name) {

        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, "%" + name + "%");

            ResultSet result = statement.executeQuery();

            System.out.println("\n===== SEARCH RESULTS =====");

            while (result.next()) {
                System.out.println(
                        result.getInt("id") + " | " +
                                result.getString("name") + " | " +
                                result.getInt("age") + " | " +
                                result.getString("department") + " | " +
                                result.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}