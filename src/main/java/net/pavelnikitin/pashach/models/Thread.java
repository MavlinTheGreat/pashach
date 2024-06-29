package net.pavelnikitin.pashach.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thread_id;

    @OneToMany
    @JoinColumn(name="thread_id")
    private List<Post> posts;

    private Boolean can_be_replied;
    private Boolean archieved;
}
