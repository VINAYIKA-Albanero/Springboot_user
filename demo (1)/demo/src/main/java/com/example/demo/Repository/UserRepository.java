package com.example.demo.Repository;

import com.example.demo.Model.UserDetails;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<UserDetails,String> {
//    @Query(select * from userdetails where username=username )

    //public UserDetails findByUserName(String username);
    Optional<UserDetails> findByUserName(String username);
    void delete(UserDetails User);

    //void delete(UserDetails user);
    //Optional<UserDetails> findByUserName(String username);
}
