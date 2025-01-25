package com.springbootProject.Journalapp.repository;


import com.springbootProject.Journalapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserRepoImpl {
   @Autowired
    private MongoTemplate mongoTemplate;
   // mongo template is class in the which is provuded by Mongorepositary to enetatrsct with the user and the database

    public List<User> getUserforSA(){

        Query query= new Query();
        query.addCriteria(Criteria.where("userName").is("asu"));
         List<User> users=mongoTemplate.find(query,User.class);
          return users;


    }

}
