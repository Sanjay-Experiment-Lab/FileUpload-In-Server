package com.FileUploadConnection.TestFileUpload.Controller;

import com.FileUploadConnection.TestFileUpload.FileUploadHelper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getContentType());
        System.out.println(file.getName());
//        try {
//            System.out.println(new ClassPathResource("static/image/").getFile().getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            //Validation
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must contain a file");
            }

            //File should be JPEG type
            if (!file.getContentType().equals("image/jpeg")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG content type are allowed");
            }

            //file upload
            boolean f = fileUploadHelper.uploadFile(file);

            // if f is true means file uploaded successfully
            if(f){
//                return ResponseEntity.ok("File is successfully uploaded");
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
            }

        }catch (Exception e){
            e.printStackTrace();
        }


            //if f is false, then file not uploaded
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some went wrong ! Try again");
//        return ResponseEntity.ok("everything alright");
    }

}
