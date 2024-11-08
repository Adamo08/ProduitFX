package com.adamo.produitfx.dao;


import com.adamo.produitfx.models.Commercial;
import com.adamo.produitfx.models.Produit;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CommercialDaoImpl implements CommercialDao {
    private Connection connection;

    public CommercialDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Set<Commercial> selectAll() {
        Set<Commercial> commercials = new HashSet<>();
        String query = "SELECT * FROM COMMERCIAL";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String matricule = rs.getString("matricule");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                String tel = rs.getString("tel");
                String email = rs.getString("email");

                Commercial commercial = new Commercial(
                        matricule, nom, prenom, adresse, tel, email, new HashSet<>()
                );


                // Fetch associated products for the commercial
                commercial.setProduits(fetchProduitsForCommercial(matricule));
                commercials.add(commercial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commercials;
    }

    private Set<Produit> fetchProduitsForCommercial(String matricule) {
        Set<Produit> produits = new HashSet<>();
        String query = "SELECT * FROM PRODUIT WHERE commercial_matricule = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, matricule);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");

                    Produit produit = new Produit(id, name, description, price, null);
                    produits.add(produit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    @Override
    public void delete(String matricule) {
        String query = "DELETE FROM COMMERCIAL WHERE matricule = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, matricule);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Commercial with matricule " + matricule + " deleted successfully");
            } else {
                System.out.println("No commercial found with matricule " + matricule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
