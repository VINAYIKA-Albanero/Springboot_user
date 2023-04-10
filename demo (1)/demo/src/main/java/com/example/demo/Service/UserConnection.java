package com.example.demo.Service;

import com.example.demo.Model.UserDetails;
import com.example.demo.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConnection {
    @Autowired
    UserRepository userRepository;

    /////////getting data(get api)/////////////




//    public String countVowels(String input) {
//        int vowelCount = 0;
//        int specialCharCount = 0;
//
//        for (int i = 0; i < input.length(); i++) {
//            char chr= input.charAt(i);
//
//            if (isVowel(chr)) {
//                vowelCount++;
//            } else if (isSpecialChar(chr)) {
//                specialCharCount++;
//            }
//        }
//
//        return "Number of vowels: " + vowelCount + ", Number of special characters: " + specialCharCount;
//    }

    public String countVowels(String input) {
        int vowelCount = 0;
        int specialCharCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char chr = input.charAt(i);

            if (isVowel(chr)) {
                vowelCount++;
            } else if (isSpecialChar(chr)) {
                specialCharCount++;
            } else {
                // Handle other characters, such as consonants
            }
        }

        String message;
        if (vowelCount == 0 && specialCharCount == 0) {
            message = "No vowels or special characters found";
        } else {
            message = "Number of vowels: " + vowelCount + ", Number of special characters: " + specialCharCount;
        }

        return message;
    }






//    public boolean isVowel(char chr) {
//        return "aeiouAEIOU".indexOf(chr) != -1;
//    }
//

    public boolean isVowel(char chr) {
        if ("aeiouAEIOU".indexOf(chr) != -1) {
            return true;
        } else {
            System.out.println("'" + chr + "' is not a vowel.");
            return false;
        }
    }



//    public boolean isSpecialChar(char chr) {
//        return !Character.isLetterOrDigit(chr) && !Character.isWhitespace(chr);
//    }


    public boolean isSpecialChar(char chr) {
        if (!Character.isLetterOrDigit(chr) && !Character.isWhitespace(chr)) {
            return true;
        } else {
            System.out.println("'" + chr + "' is not a special character.");
            return false;
        }
    }





    /////////add data(post api)/////////////
//    public UserDetails addUser(UserDetails userDetails) {
//        UserDetails userDetails1=  userRepository.save(userDetails); return userDetails1;
//    }

    public UserDetails addUser(UserDetails userDetails) {
        if (userDetails == null) {
            //System.out.println("Cannot add null user.");
            return null;
        } else {
            UserDetails userDetails1 = userRepository.save(userDetails);
            return userDetails1;
        }
    }

    // just for fun
    public ResponseEntity<String> add(UserDetails userDetails){
        if(userDetails == null){
            return new ResponseEntity<>("this user is null",HttpStatus.BAD_REQUEST);
        }
        userRepository.save(userDetails);
        return new ResponseEntity<>("New user has been added",HttpStatus.CREATED);
    }






    ///////////////// //update data(put api)///////////////////
//    public ResponseEntity<String> updateUserName(String username) {
//      List<Character> setOfSpecialChars = new ArrayList<>();
//      setOfSpecialChars.add('!');
//      setOfSpecialChars.add('@');
//      setOfSpecialChars.add('$');
//      setOfSpecialChars.add('&');
//      setOfSpecialChars.add('*');
//      int size = setOfSpecialChars.size();
//      UserDetails userDetails = userRepository.findByUserName(username).get();
//      System.out.println("userDetails-->"+userDetails+username);
//      String previousFullName = userDetails.getFullName();
//      StringBuilder sb = new StringBuilder(previousFullName);
//      for (int i = 0; i < sb.length(); i++) {
//          int randoIndex = (int) Math.random() % (size+1);
//          char ch = setOfSpecialChars.get(randoIndex);
//          if (sb.charAt(i) == 'a'|| sb.charAt(i) =='e' || sb.charAt(i) == 'i' || sb.charAt(i) == 'o' || sb.charAt(i) =='u') {
//              sb.setCharAt(i, ch);
//          }
//      }
//      String newFullName = sb.toString();
//      userDetails.setFullName(newFullName);
//       userRepository.save(userDetails);
//        return new ResponseEntity<>("Updated Successfully", HttpStatus.CREATED);
//
//  }

    public ResponseEntity<String> updateUserName(String username) {
        List<Character> setOfSpecialChars = new ArrayList<>();
        setOfSpecialChars.add('!');
        setOfSpecialChars.add('@');
        setOfSpecialChars.add('$');
        setOfSpecialChars.add('&');
        setOfSpecialChars.add('*');
        int size = setOfSpecialChars.size();
        UserDetails userDetails = userRepository.findByUserName(username).orElse(null);
        if (userDetails == null) {
            //System.out.println("Cannot update user with null details.");
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        } else {
            String previousFullName = userDetails.getFullName();
            StringBuilder sb = new StringBuilder(previousFullName);
            for (int i = 0; i < sb.length(); i++) {
                int randoIndex = (int) Math.random() % (size+1);
                char ch = setOfSpecialChars.get(randoIndex);
                if (sb.charAt(i) == 'a'|| sb.charAt(i) =='e' || sb.charAt(i) == 'i' || sb.charAt(i) == 'o' || sb.charAt(i) =='u') {
                    sb.setCharAt(i, ch);
                }
            }
            String newFullName = sb.toString();
            userDetails.setFullName(newFullName);
            userRepository.save(userDetails);
            String name = userDetails.getFullName();
            return new ResponseEntity<>(name, HttpStatus.CREATED);
        }
    }






    //Delete data(delete api)
    public String deleteData( String input) throws Exception{
        UserDetails User = userRepository.findByUserName(input).get();
//        System.out.println("********88888888************enter"+User);

        if (User == null) {
          throw new Custom("User not found");

           // return "User not found";
       }
        userRepository.delete(User);
        return "Delete Successfully";
    }

    }

class Custom extends Exception{
    Custom(String string){
        super(string);
        //super("User not found");
    }
}