package net.pavelnikitin.pashach.services;

import net.pavelnikitin.pashach.models.Pickrelated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PickrelatedService {
    @Autowired
    private LoadService loadService;

    public void addPic(Pickrelated pickrelated, MultipartFile file) {
        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            String[] namemas = name.split("\\.");
            String newname = pickrelated.getId().toString() + "." + namemas[namemas.length - 1];
            String imagePath = loadService.LoadImage(file, newname);
            pickrelated.setImagepath(imagePath);
        }

    }
}
