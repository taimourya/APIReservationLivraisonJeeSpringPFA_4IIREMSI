package com.example.reservationandlivraisonapi.metier.client;

import com.example.reservationandlivraisonapi.Service.panier.IPanierService;
import com.example.reservationandlivraisonapi.dao.acteurs.ClientRepository;
import com.example.reservationandlivraisonapi.dao.acteurs.RestaurantRepository;
import com.example.reservationandlivraisonapi.dao.buyable.BuyableRepository;
import com.example.reservationandlivraisonapi.dao.commande.CommandeRepository;
import com.example.reservationandlivraisonapi.dao.commande.LivraisonRepository;
import com.example.reservationandlivraisonapi.dao.commande.ReservationRepository;
import com.example.reservationandlivraisonapi.dao.note.NoteRepository;
import com.example.reservationandlivraisonapi.dao.paraignage.ParaignageRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.Client;
import com.example.reservationandlivraisonapi.entity.acteurs.Livreur;
import com.example.reservationandlivraisonapi.entity.acteurs.Restaurant;
import com.example.reservationandlivraisonapi.entity.buyable.Buyable;
import com.example.reservationandlivraisonapi.entity.commande.Commande;
import com.example.reservationandlivraisonapi.entity.commande.Livraison;
import com.example.reservationandlivraisonapi.entity.commande.Reservation;
import com.example.reservationandlivraisonapi.entity.note.Note;
import com.example.reservationandlivraisonapi.entity.paraignage.Paraignage;
import com.example.reservationandlivraisonapi.entityService.Panier.PanierWithInfo;
import com.example.reservationandlivraisonapi.metier.restaurant.IRestaurantMetier;
import com.example.reservationandlivraisonapi.metier.restaurant.RestaurantMetierDB;
import com.example.reservationandlivraisonapi.metier.user.IUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class ClientMetierDB  implements IClientMetier{

    @Autowired
    IRestaurantMetier restaurantMetier;
    @Autowired
    IUserMetier userMetier;
    @Autowired
    IPanierService panierService;

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    BuyableRepository buyableRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    LivraisonRepository livraisonRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    ParaignageRepository paraignageRepository;


    @Override
    public void Inscription(String username, String password, String passwordConfirm, String email, String adresse, String telephone, String firstname, String lastname, String CIN, Date dateNaissance) {

    }

    @Override
    public Collection<Buyable> consulterMenu(String mc) {
        return buyableRepository.findByNameContains(mc);
    }

    @Override
    public Collection<Buyable> consulterMenuRestaurant(int restaurant_id, String mc) throws Exception {
        return restaurantMetier.conuslterListBuyable(restaurant_id, mc);
    }

    @Override
    public Collection<Restaurant> consulterRestaurantProche(float longitude, float latitude) {

        Collection<Restaurant> restaurants = restaurantRepository.findAll();
        Collection<Restaurant> restaurantsProche = restaurantRepository.findAll();

        for(Restaurant r : restaurants) {
            //trouver dist min
            //add to restaurantsProche
        }

        return restaurants;
    }

    @Override
    public Livraison commanderLivraison(int client_id, float longitude, float latitude) throws Exception {
        Client client = (Client) userMetier.consulterUser(client_id);
        Livraison livraison = panierService.toLivraison(client, longitude, latitude);
        return commandeRepository.save(livraison);
    }

    @Override
    public Reservation commanderReservation(int client_id, Date dateReservation) throws Exception {
        Client client = (Client) userMetier.consulterUser(client_id);
        Reservation reservation = panierService.toReservation(client, dateReservation);
        return commandeRepository.save(reservation);
    }

    @Override
    public Collection<Livraison> consulterLivraisonEnCours(int client_id) throws Exception {
        Client client = (Client) userMetier.consulterUser(client_id);
        return livraisonRepository.findByClientEnCours(client);
    }

    @Override
    public Collection<Reservation> consulterReservationEnCours(int client_id) throws Exception {
        Client client = (Client) userMetier.consulterUser(client_id);
        return reservationRepository.findByClientAndDateReservationAfter(client, new Date());
    }

    @Override
    public Note noterLivreur(int client_id, int livreur_id, String message, int etoile) throws Exception {
        Livreur livreur = (Livreur) userMetier.consulterUser(livreur_id);
        Client client = (Client) userMetier.consulterUser(client_id);
        Note note = new Note(null, message, etoile, null, livreur, client);
        return noteRepository.save(note);
    }

    @Override
    public Note noterRestaurant(int client_id, int restaurant_id, String message, int etoile) throws Exception {
        Restaurant restaurant = (Restaurant) userMetier.consulterUser(restaurant_id);
        Client client = (Client) userMetier.consulterUser(client_id);
        Note note = new Note(null, message, etoile, restaurant, null, client);
        return noteRepository.save(note);
    }

    @Override
    public String getCodePromo(int client_id) throws Exception {
        Client client = (Client) userMetier.consulterUser(client_id);
        return client.getCodePromo();
    }

    @Override
    public Paraignage useCodePromo(int client_id, String codePromo) throws Exception {
        Client parrainer = (Client) userMetier.consulterUser(client_id);
        if(parrainer == null)
            throw new Exception("code promo incorrect");
        Client parraineur = clientRepository.findByCodePromo(codePromo);
        Paraignage paraignage = new Paraignage(null, parraineur, parrainer);
        if(parrainer.equals(paraignage))
            throw new Exception("paraignage impossible");
        return paraignageRepository.save(paraignage);
    }

    @Override
    public Collection<Commande> consulterHistorique(int client_id) throws Exception {
        Client client = (Client) userMetier.consulterUser(client_id);
        return client.getCommandes();
    }

    @Override
    public PanierWithInfo consulterPanier(int client_id) throws Exception {
        return panierService.toPanierWithInfo(client_id);
    }

    @Override
    public void ajouterAuPanier(int buyable_id, int qtn, int client_id) throws Exception {
        panierService.add(buyable_id, qtn, client_id);
    }

    @Override
    public void removeQtnPanier(int buyable_id, int qtn, int client_id) throws Exception {
        panierService.remove(buyable_id, qtn, client_id);
    }

    @Override
    public void supprimerDuPanier(int buyable_id, int client_id) throws Exception {
        panierService.delete(buyable_id, client_id);
    }

    @Override
    public void viderPanier(int client_id) throws Exception {
        panierService.clear(client_id);
    }

    @Override
    public float totalPanier(int client_id) throws Exception {
        return panierService.getTotal(client_id);
    }
}
