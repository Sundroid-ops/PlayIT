package com.project.playit.project.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService{

    @Resource
    private Cloudinary cloudinary;

    @Override
    public String uploadAudioFile(MultipartFile file, String audioName) {
        try {
            Map<String, Object> uploadFile = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto",
                                    "public_id", audioName));
            return uploadFile.get("secure_url").toString();

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
