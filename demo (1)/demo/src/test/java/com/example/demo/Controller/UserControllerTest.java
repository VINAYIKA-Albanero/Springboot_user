package com.example.demo.Controller;

import com.example.demo.Model.UserDetails;
import com.example.demo.Repository.UserRepository;
//import org.junit.Test;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerTest {
    @LocalServerPort
    private int port;
 private String userName="sem";
 @Autowired
//    private WebTestClient webTestClient;
 private WebClient.Builder webTestClient;
 @MockBean
    private UserRepository userRepository;
    private UserDetails userDetails = new UserDetails("1", "sem", "vinayika saini", "vinu12@gmail.com", "xyz",1223, "abc");


    @BeforeEach
    public  void mockFun(){
      when(userRepository.save(userDetails)).thenReturn(userDetails);
       when(userRepository.findAll()).thenReturn(List.of(userDetails));
       when(userRepository.findByUserName(userName)).thenReturn(Optional.ofNullable(userDetails));

    }

    @Test
    public void addUserTest(){
        ResponseEntity<UserDetails> response = webTestClient
                .baseUrl("http://localhost:"+port)
                .build()
                .post()
                .uri("/addaUserDetails")
                .bodyValue(userDetails)
                .retrieve().toEntity(UserDetails.class).block();
        assertEquals(response.getBody().getFullName(),userDetails.getFullName());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }



    @Test
  public void getUsersTest(){
        ResponseEntity<List<String>> response = webTestClient
                .baseUrl("http://localhost:"+port)
                .build()
                .get()
                .uri("/getVowelCount/sem")
                .retrieve().toEntityList(String.class).block();
//        System.out.println(response.getBody().toString()+"hello");
        assertEquals(response.getBody().toString(),"[Number of vowels: " + "1" + ", Number of special characters: " + "0]");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
   }



   @Test
   public void updateUserTest(){
     ResponseEntity<String> response = webTestClient
                .baseUrl("http://localhost:"+port)
                .build()
                .put()
                .uri("/UpdateUserName/sem",userName)
                .retrieve().toEntity(String.class).block();

        assertEquals(response.getBody(),"Updated Successfully");
        assertEquals(response.getStatusCode(),HttpStatus.CREATED);
    }



    @Test
       public void deleteUserTest(){
         ResponseEntity<String> response = webTestClient
                .baseUrl("http://localhost:"+port)
                .build()
                .delete()
                .uri("/DeleteData/sem",userName)
                .retrieve().toEntity(String.class).block();

        assertEquals(response.getBody(),"SuccessFull Deleted");
        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }


}




