package com.springbootProject.Journalapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "cacheDB")
@Data
@NoArgsConstructor

public class CacheEntity {


    @Id
    private ObjectId id;  // MongoDB's primary key field

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    // Constructor with arguments
    public CacheEntity(ObjectId id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }
}
