package net.pavelnikitin.pashach.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Pickrelated {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imagepath;

    public void addPic(MultipartFile file) {
        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            try {
                byte[] bytes = file.getBytes();
                String[] namemas = name.split("\\.");
                String newpath = Paths.get("D:\\pashach\\src\\main\\resources\\static\\user\\images",
                        id.toString()).toString() + "." + namemas[namemas.length - 1];
                this.imagepath = "/media/images/" + id.toString() + "." + namemas[namemas.length - 1];
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(newpath)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
