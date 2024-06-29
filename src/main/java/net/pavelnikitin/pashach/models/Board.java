package net.pavelnikitin.pashach.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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

    @OneToOne
    @JoinColumn(name = "thread_id")
    private Thread thread;

}
