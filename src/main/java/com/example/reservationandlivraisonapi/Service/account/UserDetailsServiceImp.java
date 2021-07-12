package com.example.reservationandlivraisonapi.Service.account;

import com.example.reservationandlivraisonapi.dao.acteurs.UserRepository;
import com.example.reservationandlivraisonapi.entity.acteurs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) throw new UsernameNotFoundException(s);
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("USER"));

        if(user instanceof Client)
            authorities.add(new SimpleGrantedAuthority("CLIENT"));
        if(user instanceof Livreur)
            authorities.add(new SimpleGrantedAuthority("LIVREUR"));
        if(user instanceof Restaurant)
            authorities.add(new SimpleGrantedAuthority("RESTAURANT"));
        if(user instanceof Assistant)
            authorities.add(new SimpleGrantedAuthority("ASSISTANT"));;
        if(user instanceof AssistantExpert)
            authorities.add(new SimpleGrantedAuthority("ASSISTANTEXPERT"));
        if(user instanceof Administrateur)
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        if(user instanceof Directeur)
            authorities.add(new SimpleGrantedAuthority("DIRECTEUR"));


        return new org.springframework.security.core.userdetails.
                        User(
                            user.getUsername(), user.getPassword(), authorities
                        );
    }
}
