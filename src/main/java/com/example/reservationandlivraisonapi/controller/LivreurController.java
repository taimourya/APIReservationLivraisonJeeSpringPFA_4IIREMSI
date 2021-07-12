package com.example.reservationandlivraisonapi.controller;


import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.metier.livreur.ILivreurMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin()
public class LivreurController {

    @Autowired
    ILivreurMetier livreurMetier;

    @GetMapping("/livreur/livraison/check")
    public Livraison checkLivraison() throws Exception {
        return livreurMetier.checkNewLivraison();
    }
    @GetMapping("/livreur/livraison/accept")
    public void accepterLivraison(@RequestParam int livraison_id) throws Exception {
        livreurMetier.accepterLivraison(livraison_id);
    }
    @GetMapping("/livreur/livraison/nextStep")
    public String livraisonNextStep() throws Exception {
        return livreurMetier.nextStepLivraison();
    }
    @GetMapping("/livreur/livraison/enCours")
    public Livraison livraisonEnCours() throws Exception {
        return livreurMetier.consulterLivraisonEnCours();
    }
    @GetMapping("/livreur/livraison/enCours/items")
    public Collection<CommandeBuyable> getItemsLivraison() throws Exception {
        return livreurMetier.consulterItemsLivraisonEnCours();
    }
    @GetMapping("/livreur/livraison/historique")
    public Collection<Livraison> historiqueLivraison() throws Exception {
        return livreurMetier.consulterHistoriqueLivraison();
    }

}
