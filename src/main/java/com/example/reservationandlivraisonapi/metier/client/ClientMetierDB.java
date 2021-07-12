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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Client inscription(String username, String password, String confimPassword) throws Exception {
        if(!password.equals(confimPassword)) throw new Exception("les mots de passe ne se resemble pas ! ");
        return clientRepository.save(
                new Client(null, username, encoder.encode(password),
                        null, null, null, null,
                        null, null, null,
                        null, username+ " " + username)
        );
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
    public Livraison commanderLivraison(float longitude, float latitude) throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        Livraison livraison = panierService.toLivraison(client, longitude, latitude);
        return commandeRepository.save(livraison);
    }

    @Override
    public Reservation commanderReservation(Date dateReservation) throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        Reservation reservation = panierService.toReservation(client, dateReservation);
        return commandeRepository.save(reservation);
    }

    @Override
    public Collection<Livraison> consulterLivraisonEnCours() throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        return livraisonRepository.findByClientEnCours(client);
    }

    @Override
    public Collection<Reservation> consulterReservationEnCours() throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        return reservationRepository.findByClientAndDateReservationAfter(client, new Date());
    }

    @Override
    public Note noterLivreur(int livreur_id, String message, int etoile) throws Exception {
        Livreur livreur = (Livreur) userMetier.consulterUser(livreur_id);
        Client client = (Client) userMetier.consulterAuthentificateUser();
        Note note = new Note(null, message, etoile, null, livreur, client);
        return noteRepository.save(note);
    }

    @Override
    public Note noterRestaurant(int restaurant_id, String message, int etoile) throws Exception {
        Restaurant restaurant = (Restaurant) userMetier.consulterUser(restaurant_id);
        Client client = (Client) userMetier.consulterAuthentificateUser();
        Note note = new Note(null, message, etoile, restaurant, null, client);
        return noteRepository.save(note);
    }

    @Override
    public String getCodePromo() throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        return client.getCodePromo();
    }

    @Override
    public Paraignage useCodePromo(String codePromo) throws Exception {
        Client parrainer = (Client) userMetier.consulterAuthentificateUser();
        Client parraineur = clientRepository.findByCodePromo(codePromo);
        if(parraineur == null)
            throw new Exception("code promos incorrect");
        Paraignage paraignage = new Paraignage(null, parraineur, parrainer);
        if(parrainer.getCodePromo().equals(parraineur.getCodePromo()))
            throw new Exception("paraignage impossible");
        return paraignageRepository.save(paraignage);
    }

    @Override
    public Collection<Commande> consulterHistorique() throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        return client.getCommandes();
    }

    @Override
    public PanierWithInfo consulterPanier() throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        return panierService.toPanierWithInfo(client.getUser_id());
    }

    @Override
    public void ajouterAuPanier(int buyable_id, int qtn) throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        panierService.add(buyable_id, qtn, client.getUser_id());
    }

    @Override
    public void removeQtnPanier(int buyable_id, int qtn) throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        panierService.remove(buyable_id, qtn, client.getUser_id());
    }

    @Override
    public void supprimerDuPanier(int buyable_id) throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        panierService.delete(buyable_id, client.getUser_id());
    }

    @Override
    public void viderPanier() throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        panierService.clear(client.getUser_id());
    }

    @Override
    public float totalPanier() throws Exception {
        Client client = (Client) userMetier.consulterAuthentificateUser();
        return panierService.getTotal(client.getUser_id());
    }
}
