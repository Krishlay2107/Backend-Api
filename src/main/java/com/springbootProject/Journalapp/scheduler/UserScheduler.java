package com.springbootProject.Journalapp.scheduler;

import com.springbootProject.Journalapp.entity.JournalEntry;
import com.springbootProject.Journalapp.entity.User;
import com.springbootProject.Journalapp.enums.Sentiment;

import com.springbootProject.Journalapp.repository.UserRepoImpl;
import com.springbootProject.Journalapp.services.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class UserScheduler {
    @Autowired
    private EmailServices emailServices;

    @Autowired
    private UserRepoImpl userRepo;

    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepo.getUserforSA();

        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x ->x.getSentiment())
                    .filter(sentiment -> sentiment != null)
                    .collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {

                    emailServices.sendEmail(user.getEmail(), "Sentiment for previous week", mostFrequentSentiment.toString());

            }
        }
    }


}
