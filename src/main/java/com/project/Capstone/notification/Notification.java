package com.project.Capstone.notification;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;

    private String userId;         // ID of the user who receives the notification
    private String message;        // The notification message
    private LocalDateTime timestamp = LocalDateTime.now();
}

