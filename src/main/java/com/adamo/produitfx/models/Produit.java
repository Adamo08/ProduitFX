package com.adamo.produitfx.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Produit {
    private int id;
    private String name;
    private String description;
    private double price;
    private Commercial commercial;

    public Produit(
            String name,
            String description,
            double price,
            Commercial selectedCommercial
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.commercial = selectedCommercial;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return id == produit.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
