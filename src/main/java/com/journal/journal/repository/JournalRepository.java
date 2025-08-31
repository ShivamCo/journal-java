package com.journal.journal.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.journal.journal.entity.JournalEntity;

public interface JournalRepository extends MongoRepository<JournalEntity, ObjectId > {

}
