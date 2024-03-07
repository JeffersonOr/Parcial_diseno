/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connetion.ConnectionMySQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class ControllerUser {

    private final ConnectionMySQL conection;

    public ControllerUser() throws SQLException {
        this.conection = new ConnectionMySQL();
    }

    public void createUser(int id, String name, String lastname, int age, String email, int id_product) throws SQLException {
        String createSQL = "INSERT INTO user(name, lastname, age, email, id_product ) VALUES( ?, ?, ?, ?)";
        try (PreparedStatement statement = ConnectionMySQL.conectarMySQL().prepareStatement(createSQL)) {

            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setInt(3, age);
            statement.setString(4, email);
            statement.setInt(4, id_product);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insercion exitosa");
            } else {
                System.out.println("No se pudo insertar los datos");

            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al realizar la insercion en la base de datos");
            e.printStackTrace();
        }
    }

    public void readUser(int id) throws SQLException {
        String readSQL = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement statement = ConnectionMySQL.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("lastname"));
                System.out.println(rs.getString("age"));
                System.out.println(rs.getString("email"));
                System.out.println(rs.getString("id_product"));
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }

    }

    public void updateUser(int id, String name, String lastname, int age, String email, int id_product) throws Exception {
        String updateSQL = "UPDATE product SET name = ?, lastname= ?, age = ?, email= ?, id_product = ? WHERE id = ?;";
        try (PreparedStatement statement = ConnectionMySQL.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setInt(3, age);
            statement.setString(4, email);
            statement.setInt(4, id_product);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void deleteUser(int id) throws Exception {
        String deleteSQL = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement statement = ConnectionMySQL.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);

        }
    }
}
