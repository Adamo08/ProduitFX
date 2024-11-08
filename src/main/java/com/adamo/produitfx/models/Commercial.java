package com.adamo.produitfx.models;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Commercial {
    private String matricule;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String email;

    private Set<Produit> produits;

    @Override
    public String toString() {
        return matricule;
    }
}

