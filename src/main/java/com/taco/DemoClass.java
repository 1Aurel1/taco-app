package com.taco;

import com.taco.entities.Card;
import com.taco.entities.Role;
import com.taco.entities.User;
import com.taco.entities.enums.RoleType;
import com.taco.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoClass {

    private final UserServices userServices;

    public static void main(String[] args) {
        SpringApplication.run(DemoClass.class,args);
    }

    @Bean
    public String putDomeDefaultUsers(UserServices userServices){

        //client role
        Role role = new Role();
        role.setRoleType(RoleType.CLIENT);
        role.setId(2L);

        // admin role
        Role admin = new Role();
        admin.setRoleType(RoleType.ADMIN);
        role.setId(1L);

        //menager role
        Role menager = new Role();
        menager.setRoleType(RoleType.MANAGER);
        role.setId(3L);

        User user = new User();
        user.setFirstName("client2");
        user.setLastName("client2");
        user.setEmail("client2@atis.al");
        user.setPassword("12345");
        user.setRole(role);
        user.setAge(52);
        user.setGender("FEMALE");

        User user2 = new User();
        user2.setFirstName("menager");
        user2.setLastName("menager");
        user2.setEmail("menager@atis.al");
        user2.setPassword("12345");
        user2.setRole(menager);
        user2.setAge(22);
        user2.setGender("MALE");

            User user3 = new User();
            user3.setFirstName("admin");
            user3.setLastName("admin");
            user3.setEmail("admin@atis.al");
            user3.setPassword("12345");
            user3.setRole(admin);
            user3.setAge(22);
            user3.setGender("MALE");

       // userServices.createNewUser(user);
        userServices.createNewUser(user2);
        userServices.createNewUser(user3);
        return "Ther users are created succesffully"   + user2
                + " " + user3;
    }

    //sa here qe applikacioni te behet run, kjo klas do te jet e disponueshme per tu therritur nga Spring
/*
    @Bean
    CommandLineRunner runner (UserServices userServices){
        return args -> {
            User user = new User();
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setEmail("admin@atis.al");
            user.setPassword("12345");
            user.setRole(new Role(0L, RoleType.ADMIN,null));
            user.setAge(22);
            user.setGender("MALE");

            User tempUser = userServices.findUserByEmail(user.getEmail());
            System.out.println("The user details "+ tempUser);
            if(tempUser == null) {
                userServices.createNewUser(user);
            }
        };*/
   // }
}

