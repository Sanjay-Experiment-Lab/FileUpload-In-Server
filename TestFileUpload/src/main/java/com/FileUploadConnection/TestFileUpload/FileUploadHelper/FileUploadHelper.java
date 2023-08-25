package com.FileUploadConnection.TestFileUpload.FileUploadHelper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public FileUploadHelper() throws IOException {  // getFile method throws exception
    }

//    public final String UPLOAD_DIR ="F:\\Intellij\\intellij project\\AFTER\\TestFileUpload\\src\\main\\resources\\static\\image"; //static path
    public final String UPLOAD_DIR = new ClassPathResource("static/image").getFile().getAbsolutePath(); // dynamic path



    public boolean uploadFile(MultipartFile multipartFile){
        System.out.println("Path dekho:"+UPLOAD_DIR);
        boolean f = false;

        try {

            //Fetch the data from the file
//            InputStream is = multipartFile.getInputStream();
//            byte data[] = new byte[is.available()];
//            is.read(data);
//
//            //Write the data to file
//            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+"\\"+multipartFile.getOriginalFilename());
                                            //We can also write--> UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()
//            fos.write(data);
//
//            fos.flush();
//            fos.close();

            //Alternative of above code in one line with "nio" package-->Files.copy(source,target,option)
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            f = true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return f;
    }
}
