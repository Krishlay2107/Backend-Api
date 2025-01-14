package com.springbootProject.Journalapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;

    public @NonNull String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

}
