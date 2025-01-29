package com.springbootProject.Journalapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cacheDB")  // Indicates collection "cacheDb" in MongoDB
@NoArgsConstructor
@AllArgsConstructor
public class CacheEntity {

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
}
