package com.example.demo.Service;

import com.example.demo.Controller.UserController;
import com.example.demo.Model.UserDetails;
import com.example.demo.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserconnectionTest {


    @Autowired
    private UserConnection userConnection;

    @Autowired
    private UserController userController;
    @MockBean
    private UserRepository userRepository;
    private UserDetails userDetails = new UserDetails("1", "sunil", "vinayika saini", "vinu12@gmail.com", "xyz",1223, "abc");

    @BeforeEach
    public  void mockFun() {

        when(userRepository.save(userDetails)).thenReturn(userDetails);
    }


    @Test
    public void countVowelsTest() {

        assertEquals(userConnection.countVowels("VINAYIKA"), "Number of vowels: 4, Number of special characters: 0");
        //invalid user
        assertEquals(userConnection.countVowels("bcdf"), "No vowels or special characters found");

    }




    @Test
    public void isVowelTest() {
        assertTrue(userConnection.isVowel('2'));
        assertEquals(userConnection.isVowel('a'), true);
        assertEquals(userConnection.isVowel('b'), false);
        assertEquals(userConnection.isVowel('1'), false);
        //Invalid test cases
        char nonVowel = '1';
        assertFalse(userConnection.isVowel(nonVowel));
    }





     @Test
     public void isSpecialCharTest() {
       assertEquals(userConnection.isSpecialChar('!'), true);
        assertEquals(userConnection.isSpecialChar('a'), false);
        //        Invalid Test Case...
         char nonSpecialChar = 'z';
         assertFalse(userConnection.isSpecialChar(nonSpecialChar));
    }


    @Test
    public void addUserTest() {

        assertEquals(userConnection.addUser(userDetails), userDetails);
        //        invalid test case..
       assertEquals(userConnection.addUser(null),null);
    }

    @Test
    @DisplayName("This is for experiment")
    public void experiment(){
        ResponseEntity<String> expected = new ResponseEntity<>("New user has been added", HttpStatus.CREATED);
        ResponseEntity<String> actual = userConnection.add(userDetails);
        assertEquals(expected,actual);
    }



    @Test
    public void updateUserTest(){
        String oldName = userDetails.getFullName();
        String newName = userController.updateUserName("sunil").getBody();
        int expectedLength = userDetails.getFullName().length();
        int actualLength = newName.length();
        assertEquals(expectedLength,actualLength);
        //Invalid Test Case...
      // assertEquals(userConnection.updateUserName(""),"User not found.");
    }




    @Test
        public void deleteUserTest() throws Exception {
        assertEquals(userConnection.deleteData("vick12"),"Delete Successfully");
        // Invalid Test Case...


    }

}














