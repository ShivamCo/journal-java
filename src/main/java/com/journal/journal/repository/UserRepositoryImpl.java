package com.journal.journal.repository;

import com.journal.journal.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate;


    public List<UserEntity> getUserForSA() {

        Query query = new Query();

        query.addCriteria(Criteria.where("email").regex("/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$/"));

        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));


        List<UserEntity> users = mongoTemplate.find(query, UserEntity.class);
        return users;

    }

}
