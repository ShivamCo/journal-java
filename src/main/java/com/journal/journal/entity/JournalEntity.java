package com.journal.journal.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "journal")
@Data
public class JournalEntity {
    @Id
    private ObjectId Id;
    private String Title;
    private String Description;

}
