package com.journal.journal.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal")
@Data
@NoArgsConstructor
public class JournalEntity {
    @Id
    private ObjectId id;
@NonNull
    private String title;
    private String content;
    private LocalDateTime date;

}
