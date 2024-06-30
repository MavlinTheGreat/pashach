package net.pavelnikitin.pashach.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer board_id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable=false, length = 64)
    private String codename;

    @Column(nullable = false, length = 1024)
    private String description;

    @OneToMany
    @JoinColumn(name = "threads")
    private List<Thread> threads = new ArrayList<>();

    public void addThread(Thread thread) {
        threads.add(thread);
    }


}
