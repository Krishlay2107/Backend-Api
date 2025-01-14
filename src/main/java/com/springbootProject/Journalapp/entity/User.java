package com.springbootProject.Journalapp.entity;


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
public class User {

       @Id
       private ObjectId id;

       public @NonNull String getUserName() {
              return userName;
       }

       public void setUserName(@NonNull String userName) {
              this.userName = userName;
       }

       @Indexed(unique = true)
       @NonNull
       private String userName;
       @NonNull
       private String password;

        private List<String> roles;
       @DBRef
       private List<JournalEntry> journalEntries= new ArrayList<>();
}
