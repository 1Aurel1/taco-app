package com.taco.repository;

import com.taco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.firstName like :theName")
    List<User> getAllByFirstname(@Param("theName") String name);

    @Query("select u from User u where u.email like :email")
    User getTheUserByEmail(@Param("email") String email);

    User findByEmail(String username);

    @Query("select u from User u")
    public List<User> returnAllAvailableUsers();
}
