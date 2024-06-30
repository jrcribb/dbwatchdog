package com.jrcribb.watchdog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrigenDAO {

    public void create(Origen origen) throws SQLException {
        String query = "INSERT INTO ORIGENES (ORIGEN, UBICACION) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, origen.getOrigen());
            preparedStatement.setString(2, origen.getUbicacion());
            preparedStatement.executeUpdate();
        }
    }

    public Origen read(int id) throws SQLException {
        String query = "SELECT * FROM ORIGENES WHERE ID_ORIGEN = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Origen(
                        resultSet.getInt("ID_ORIGEN"),
                        resultSet.getString("ORIGEN"),
                        resultSet.getString("UBICACION")
                );
            }
        }
        return null;
    }

    public List<Origen> readAll() throws SQLException {
        List<Origen> origenes = new ArrayList<>();
        String query = "SELECT * FROM ORIGENES";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                origenes.add(new Origen(
                        resultSet.getInt("ID_ORIGEN"),
                        resultSet.getString("ORIGEN"),
                        resultSet.getString("UBICACION")
                ));
            }
        }
        return origenes;
    }

    public void update(Origen origen) throws SQLException {
        String query = "UPDATE ORIGENES SET ORIGEN = ?, UBICACION = ? WHERE ID_ORIGEN = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, origen.getOrigen());
            preparedStatement.setString(2, origen.getUbicacion());
            preparedStatement.setInt(3, origen.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String query = "DELETE FROM ORIGENES WHERE ID_ORIGEN = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
