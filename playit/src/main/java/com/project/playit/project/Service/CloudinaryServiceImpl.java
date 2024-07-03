package com.project.playit.project.Service;

import com.cloudinary.Cloudinary;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService{

    @Resource
    private Cloudinary cloudinary;

    @Override
    public String uploadAudioFile(MultipartFile file, String folderName) {
        try {
            Map<Object, Object> options = new HashMap<>();
            options.put("songs", folderName);
            Map uploadFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicID = (String) uploadFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicID);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
