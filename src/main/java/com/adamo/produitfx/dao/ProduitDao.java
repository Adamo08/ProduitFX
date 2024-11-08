package com.adamo.produitfx.dao;

import com.adamo.produitfx.models.Produit;

import java.sql.SQLException;

public interface ProduitDao {
    int add(Produit p) throws SQLException; // Pour ajouter un produit
    int update(Produit p); // Pour mettre à jour un produit
}
