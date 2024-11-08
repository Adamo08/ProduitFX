package com.adamo.produitfx.dao;

import com.adamo.produitfx.models.Produit;

import java.sql.*;

public class ProduitDaoImpl implements ProduitDao {
    private final Connection connection;

    public ProduitDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(Produit produit) {
        String query = "INSERT INTO PRODUIT (name, description, price, commercial_matricule) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, produit.getName());
            pstmt.setString(2, produit.getDescription());
            pstmt.setDouble(3, produit.getPrice());
            pstmt.setString(4, produit.getCommercial().getMatricule());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }

            // Retrieve the auto-generated ID
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produit.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }

            return affectedRows;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: Product ID already exists.");
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public int update(Produit produit) {
        String query = "UPDATE PRODUIT SET name = ?, description = ?, price = ?, commercial_matricule = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, produit.getName());
            pstmt.setString(2, produit.getDescription());
            pstmt.setDouble(3, produit.getPrice());
            pstmt.setString(4, produit.getCommercial().getMatricule());
            pstmt.setInt(5, produit.getId());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}


