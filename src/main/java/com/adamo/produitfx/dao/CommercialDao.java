package com.adamo.produitfx.dao;


import com.adamo.produitfx.models.Commercial;

import java.util.Set;

public interface CommercialDao {
    Set<Commercial> selectAll(); // Pour récupérer tous les commerciaux
    void delete(String matricule); // Pour supprimer un commercial par matricule
}
