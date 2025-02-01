package com.springbootProject.Journalapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "cacheDB")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheEntity {

    @Id
    private ObjectId id;  // MongoDB's primary key field

    private String key;
    private String value;
}
