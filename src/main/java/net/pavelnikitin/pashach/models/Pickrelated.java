package net.pavelnikitin.pashach.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import net.pavelnikitin.pashach.services.LoadService;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pickrelated {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imagepath;

}
