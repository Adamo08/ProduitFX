package com.adamo.produitfx.controllers;

import com.adamo.produitfx.dao.CommercialDaoImpl;
import com.adamo.produitfx.dao.ProduitDaoImpl;
import com.adamo.produitfx.models.Commercial;
import com.adamo.produitfx.models.Produit;
import com.adamo.produitfx.util.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

public class AjoutProduitController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<Commercial> commercialComboBox;

    @FXML
    private Label errorLabel;

    private final ProduitDaoImpl produitDao;
    private final CommercialDaoImpl commercialDao;

    public AjoutProduitController() {
        Connection connection = DatabaseConnection.getConnection();
        this.produitDao = new ProduitDaoImpl(connection);
        this.commercialDao = new CommercialDaoImpl(connection);
    }

    @FXML
    public void initialize() {
        loadCommercials();
    }

    private void loadCommercials() {
        Set<Commercial> commercials = commercialDao.selectAll();
        ObservableList<Commercial> commercialList = FXCollections.observableArrayList(commercials);
        commercialComboBox.setItems(commercialList);
    }

    @FXML
    private void handleAddProduct() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String priceText = priceField.getText();
        Commercial selectedCommercial = commercialComboBox.getValue();

        if (name.isEmpty() || description.isEmpty() || priceText.isEmpty() || selectedCommercial == null) {
            errorLabel.setText("Veuillez remplir tous les champs.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText.replace(",", "."));
        } catch (NumberFormatException e) {
            errorLabel.setText("Prix invalide. Utilisez le format 45.30 ou 45,30.");
            return;
        }

        Produit produit = new Produit(0,name, description, price, selectedCommercial);
        int result = produitDao.add(produit);

        if (result > 0) {
            errorLabel.setText("Produit ajouté avec succès!");
            clearForm();
        } else {
            errorLabel.setText("Erreur lors de l'ajout du produit.");
        }
    }

    private void clearForm() {
        nameField.clear();
        descriptionField.clear();
        priceField.clear();
        commercialComboBox.getSelectionModel().clearSelection();
    }
}
