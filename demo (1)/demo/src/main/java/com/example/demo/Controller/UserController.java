package com.example.demo.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.Model.UserDetails;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserConnection userConnection;
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/addaUserDetails")
    public UserDetails addUser(@RequestBody UserDetails userDetails){
        System.out.println("Entered in post(/user) api");
        logger.info("Entered in post(/user) api");
        logger.warn("putting details of the user");
        UserDetails userDetails1=userConnection.addUser(userDetails);
         return userDetails1;

    }





    @GetMapping("/getVowelCount/{string}")
    public String countVowels(@PathVariable("string") String input){
//     logger.info("Here inside the get api");
//     logger.warn("fsddgbsfn,BFVGH");
        System.out.println("this is mandatory");
        logger.info("this is info");
        logger.debug("this is debug");
        logger.trace("this is trace");
        logger.warn("this is warn");
        logger.error("this is error");
     return userConnection.countVowels(input);
    }





    @PutMapping("/UpdateUserName/{string}")
    public ResponseEntity<String> updateUserName(@PathVariable("string")String input){
        logger.info("The application started successfully.");
        logger.warn("The database is running low on disk space.");
        return userConnection.updateUserName(input);
    }






    @DeleteMapping("/DeleteData/{string}")
    public String  deleteData(@PathVariable("string") String input)  {
         try{
             return userConnection.deleteData(input);
         }
         catch (Exception e) {
             return "SuccessFull Deleted";
         }
    }
}







