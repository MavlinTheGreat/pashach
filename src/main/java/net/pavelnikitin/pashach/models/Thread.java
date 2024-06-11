package net.pavelnikitin.pashach.models;

import jakarta.persistence.*;

@Entity
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long head_post_id;
    private Integer board_id;
    private Boolean can_be_replied;
    private Boolean archieved;
}
