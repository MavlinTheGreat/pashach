package net.pavelnikitin.pashach.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

@Service
public class LoadService {

    @Value("${app.posts.imagepath}")
    private String imagePath;

    public String LoadImage(MultipartFile file, String newName) {
        String respath = "/media/images/exe.png";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String newPath = Paths.get(imagePath, newName).toString();
                respath = "/media/images/" + newName;
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(newPath)));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                System.out.println(e.toString() + " " + e.getStackTrace().toString());
            }
        }
        return respath;
    }
}
