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
public class ControllerInventory {

    private final ConnectionMySQL conection;

    public ControllerInventory() throws SQLException {
        this.conection = new ConnectionMySQL();
    }

    public void createInventory(String description, String date_buy, int price) throws SQLException {
        String createSQL = "INSERT INTO product(description, date_buy, price) VALUES( ?, ?, ?, ?)";
        try (PreparedStatement statement = ConnectionMySQL.conectarMySQL().prepareStatement(createSQL)) {

            statement.setString(1, description);
            statement.setString(2, date_buy);
            statement.setInt(3, price);

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

    public void readInventory(int id) throws SQLException {
        String readSQL = "SELECT * FROM producto WHERE id = ?";
        try (PreparedStatement statement = ConnectionMySQL.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("id_product"));
                System.out.println(rs.getString("description"));
                System.out.println(rs.getString("date_buy"));
                System.out.println(rs.getString("price"));

            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }

    }

    public void updateInventory(int id_product, String description, String date_buy, int price) throws Exception {
        String updateSQL = "UPDATE product SET name = ?, description = ?, category = ?, price = ?, price = ? WHERE id = ?;";
        try (PreparedStatement statement = ConnectionMySQL.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setInt(3, id_product);
            statement.setString(1, description);
            statement.setString(2, date_buy);
            statement.setInt(3, price);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void deleteInventory(int id_product) throws Exception {
        String deleteSQL = "DELETE FROM product WHERE id = ?";
        try (PreparedStatement statement = ConnectionMySQL.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setInt(1, id_product);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
        
        }
    }
}
