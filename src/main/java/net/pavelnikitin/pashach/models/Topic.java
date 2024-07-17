package net.pavelnikitin.pashach.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thread_id;

    @Column(length=255, columnDefinition = "VARCHAR(255)")
    @ColumnDefault("'(без заголовка)'")
    private String title;

    @OneToMany
    @JoinColumn(name="topic_id")
    private List<Post> posts = new ArrayList<>();

    private Boolean can_be_replied;
    private Boolean archieved;
    private Boolean pinned;

    public void addPost(Post post) {
        posts.add(post);
    }
}
