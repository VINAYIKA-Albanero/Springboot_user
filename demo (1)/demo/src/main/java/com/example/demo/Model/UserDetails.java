package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="User")
public class UserDetails {
        @Id
        private String _id;
        private String userName;
        private String fullName;
        private String email;
        private String address;
        private int mobileNumber;
        private String CurrentOrganization;


}




