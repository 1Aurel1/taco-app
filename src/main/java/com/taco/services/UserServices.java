package com.taco.services;

import com.taco.repository.UserRepository;
import com.taco.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServices implements UserDetailsService {

    private final UserRepository userRepository;
    //me ane te ksaj klase do te bejm encriptimin e passwordit qe do na vij nga useri
    private final PasswordEncoder passwordEncoder;

    //create user
    public User createNewUser(User user){
        //ktu marrim passw e userit dhe e enkriptojme perpara se te futet ne DB
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //update user
    public User updateUser(User user){
        //ktu marrim passw e userit dhe e enkriptojme perpara se te futet ne DB
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //get All users
    public List<User> getAllUsers() {
        return userRepository.returnAllAvailableUsers();
    }

    //get user by name
    public List<User> getUsersByName(String name) {
        return userRepository.getAllByFirstname(name);
    }

    @Override
    //kjo eshte metoda qe Spring do te perdor per te load-uar userat nga DB dhe akseset ne baz te roleve qe do te ken
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("The user not found in database");
                 }
        else {
            System.out.printf("The user %s found in database ", user);
        }

        //ktu marr rolin e userit dhe e shtoj tek lista
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleType().toString()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }

    public User findUserByEmail(String email) {
        return userRepository.getTheUserByEmail(email);
    }
}
