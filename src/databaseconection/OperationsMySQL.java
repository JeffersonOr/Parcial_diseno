package databaseconection;

import connetion.ConnectionMySQL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperationsMySQL {
    public static void main(String[] args) {
        // Creamos un objeto de la clase ConexionMySQL
        ConnectionMySQL conexion = new ConnectionMySQL();

        // Establecemos la conexión con la base de datos
        try (Connection conn = conexion.conectarMySQL()) {
            // Verificamos si la conexión fue exitosa
            if (conn != null) {
                // Ejemplo de INSERT
                String insertSQL = "INSERT INTO nombreTabla (columna1, columna2) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, "valor1");
                    pstmt.setString(2, "valor2");
                    pstmt.executeUpdate();
                }

                // Ejemplo de UPDATE
                String updateSQL = "UPDATE nombreTabla SET columna1 = ? WHERE columna2 = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                    pstmt.setString(1, "nuevoValor");
                    pstmt.setString(2, "valor2");
                    pstmt.executeUpdate();
                }

                // Ejemplo de DELETE
                String deleteSQL = "DELETE FROM nombreTabla WHERE columna1 = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                    pstmt.setString(1, "valor1");
                    pstmt.executeUpdate();
                }
            } else {
                System.out.println("No se pudo establecer la conexión con la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al realizar las operaciones en la base de datos");
            e.printStackTrace();
        }
    }
}