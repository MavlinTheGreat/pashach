package net.pavelnikitin.pashach.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenerationTime;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=255, columnDefinition = "VARCHAR(255) DEFAULT '(без заголовка)'")
    private String title;

    @Column(nullable=false, columnDefinition = "TEXT")
    private String post_content;

    private Long pickrelated_id;

    @Column(nullable = false, columnDefinition = "VARCHAR(64) DEFAULT 'Anonymous'")
    private String author_nick;

    @Column(nullable = false, columnDefinition = "inet")
    private String author_ip;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creation_date;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean deleted;
}
