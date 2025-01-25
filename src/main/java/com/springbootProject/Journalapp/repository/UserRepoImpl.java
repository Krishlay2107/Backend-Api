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
        // showing that find tye user of userName equals to asu ( way without creating the criteria object
       // query.addCriteria(Criteria.where("userName").is("asu"));
        //query.addCriteria(Criteria.where("age").is("21"));
        // ypu can also write abpve using andoperator

         //# by creating criterai object

        // # match through the regex

        Criteria criteria= new Criteria();
         query.addCriteria(criteria.andOperator(
                 Criteria.where("email").exists(true)
        ,Criteria.where("sentimenatAnalysis").is(true)));

      //  return  mongoTemplate.find(query,User.class); also return using this
         List<User> users=mongoTemplate.find(query,User.class);

          return users;


    }

}
