package com.example.reservationandlivraisonapi.controller;


import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.metier.livreur.ILivreurMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class LivreurController {

    @Autowired
    ILivreurMetier livreurMetier;

    @GetMapping("/livraison/check")
    public Livraison checkLivraison(@RequestParam int livreur_id) throws Exception {
        return livreurMetier.checkNewLivraison(livreur_id);
    }
    @GetMapping("/livraison/accept")
    public void accepterLivraison(@RequestParam int livreur_id, @RequestParam int livraison_id) throws Exception {
        livreurMetier.accepterLivraison(livreur_id, livraison_id);
    }
    @GetMapping("/livraison/nextStep")
    public String livraisonNextStep(@RequestParam int livreur_id) throws Exception {
        return livreurMetier.nextStepLivraison(livreur_id);
    }
    @GetMapping("/livraison/enCours")
    public Livraison livraisonEnCours(@RequestParam int livreur_id) throws Exception {
        return livreurMetier.consulterLivraisonEnCours(livreur_id);
    }
    @GetMapping("/livraison/enCours/items")
    public Collection<CommandeBuyable> getItemsLivraison(@RequestParam int livreur_id) throws Exception {
        return livreurMetier.consulterItemsLivraisonEnCours(livreur_id);
    }
    @GetMapping("/livraison/historique")
    public Collection<Livraison> historiqueLivraison(@RequestParam int livreur_id) throws Exception {
        return livreurMetier.consulterHistoriqueLivraison(livreur_id);
    }

}
