package com.springbootProject.Journalapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

       @Id
       private ObjectId id;

    @NonNull
       public  String getUserName() {
              return userName;
       }
    @NonNull
       public void setUserName( String userName) {
              this.userName = userName;
       }

       @Indexed(unique = true)
       @NonNull
       private String userName;
       @NonNull
       private String password;

        private  String email;

        private  boolean sentimenatAnalysis;

        private List<String> roles;
       @DBRef
       private List<JournalEntry> journalEntries= new ArrayList<>();
}
