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

    public @NonNull String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
       private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private  String email;

    public boolean isSentimenatAnalysis() {
        return sentimenatAnalysis;
    }

    public void setSentimenatAnalysis(boolean sentimenatAnalysis) {
        this.sentimenatAnalysis = sentimenatAnalysis;
    }

    private  boolean sentimenatAnalysis;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    private List<String> roles;

    public List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }

    @DBRef
       private List<JournalEntry> journalEntries= new ArrayList<>();
}
