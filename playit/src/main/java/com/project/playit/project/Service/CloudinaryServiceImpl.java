package com.project.playit.project.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.Resource;
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
    public Map<String, String> uploadAudioFile(MultipartFile file, String audioName) throws IOException {
        Map<String, Object> uploadFile = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto",
                "context", "custom_name=" + audioName));

        Map<String, String> uploadFileData = new HashMap<>();
        uploadFileData.put("file_url",uploadFile.get("secure_url").toString());
        uploadFileData.put("public_id", uploadFile.get("public_id").toString());

        return uploadFileData;
    }

    @Override
    public void deleteAudioFile(String cloudinaryFilePublicID) throws IOException {
        System.out.println(cloudinary.uploader().destroy(cloudinaryFilePublicID, ObjectUtils.emptyMap()));
    }
}
