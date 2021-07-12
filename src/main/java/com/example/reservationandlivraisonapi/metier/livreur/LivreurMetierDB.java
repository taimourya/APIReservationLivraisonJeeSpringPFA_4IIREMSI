package com.example.reservationandlivraisonapi.metier.livreur;

import com.example.reservationandlivraisonapi.dao.commande.CommandeBuyableRepository;
import com.example.reservationandlivraisonapi.dao.commande.LivraisonRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.Livreur;
import com.example.reservationandlivraisonapi.entity.commande.CommandeBuyable;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.metier.user.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Component
@Transactional
public class LivreurMetierDB implements ILivreurMetier{
    @Autowired
    IUserMetier userMetier;

    @Autowired
    LivraisonRepository livraisonRepository;
    @Autowired
    CommandeBuyableRepository commandeBuyableRepository;

    @Override
    public void accepterLivraison(int livraison_id) throws Exception {
        Livreur livreur = (Livreur) userMetier.consulterAuthentificateUser();

        if(livraisonRepository.findCurrentLivraison(livreur) != null)
            throw new Exception("vous avez deja une livraison en cours");

        Optional<Livraison> lopt = livraisonRepository.findById(livraison_id);

        if (lopt.isPresent()) {
            Livraison livraison = lopt.get();
            if(livraison.getStat() != 0)
                throw new Exception("Livraison pris essayez a nouveau");

            livraison.setLivreur(livreur);
            livraison.setStat(1);
            livraisonRepository.save(livraison);
            return;
        }

        throw new Exception("Livraison introuvable");
    }
    @Override
    public Livraison checkNewLivraison() throws Exception {
        Livreur livreur = (Livreur) userMetier.consulterAuthentificateUser();
        if(livraisonRepository.findCurrentLivraison(livreur) != null)
            throw new Exception("vous avez deja une livraison en cours");

        Livraison livraison = livraisonRepository.findLivraisonNonPris();
        if(livraison == null)
            throw new Exception("Rien trouver");
        return livraison;
    }

    @Override
    public String nextStepLivraison() throws Exception {
        Livraison livraison = consulterLivraisonEnCours();
        livraison.setStat(livraison.getStat() + 1);
        String msg = "";
        if(livraison.getStat() == 2) {
            msg = "en route";
        }
        if(livraison.getStat() == 3) {
            msg = "livrer";
            livraison.setLivredDate(new Date());
        }
        livraisonRepository.save(livraison);

        return msg;
    }

    @Override
    public Livraison consulterLivraisonEnCours() throws Exception {
        Livreur livreur = (Livreur) userMetier.consulterAuthentificateUser();
        Livraison livraison = livraisonRepository.findCurrentLivraison(livreur);
        if(livraison == null)
            throw new Exception("vous n'avez aucune livraison en cours");

        return livraison;
    }

    @Override
    public Collection<CommandeBuyable> consulterItemsLivraisonEnCours() throws Exception {
        Livreur livreur = (Livreur) userMetier.consulterAuthentificateUser();
        Livraison livraison = livraisonRepository.findCurrentLivraison(livreur);
        return livraison.getCommandeBuyables();
    }


    @Override
    public Collection<Livraison> consulterHistoriqueLivraison() throws Exception {
        Livreur livreur = (Livreur) userMetier.consulterAuthentificateUser();
        return livraisonRepository.findByLivreur(livreur);
    }

}
