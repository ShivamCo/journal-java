package com.journal.journal.repository;

import com.journal.journal.entity.JournalEntity;
import com.journal.journal.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

    UserEntity findByUserName(String userName);

}
