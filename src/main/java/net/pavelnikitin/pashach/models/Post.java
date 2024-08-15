package net.pavelnikitin.pashach.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable=false, columnDefinition = "TEXT")
    private String postContent;

    @OneToMany
    @JoinColumn(name = "pic_id")
    private List<Pickrelated> pickrelated;

    @Column(nullable = false, columnDefinition = "VARCHAR(64)")
    @ColumnDefault("'Анон'")
    private String authorPseudo;

    @Column(nullable = false)
    private String authorIp;

    @Column(columnDefinition = "TIMESTAMP")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(columnDefinition = "BOOLEAN")
    @ColumnDefault("FALSE")
    private Boolean deleted;



    public void addPickrelated( Pickrelated pc ) {
        if (pickrelated == null)
            this.pickrelated = new ArrayList<>();
        pickrelated.add(pc);
    };
}
