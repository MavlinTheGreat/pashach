package net.pavelnikitin.pashach.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenerationTime;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String post_content;

    private byte[] pickrelated;

    @Column(nullable = false, columnDefinition = "VARCHAR(64) DEFAULT 'Anonymous'")
    private String author_name;

    @Column(nullable = false, columnDefinition = "inet")
    private String author_ip;
    private Long thread_id;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creation_date;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean deleted;
}
