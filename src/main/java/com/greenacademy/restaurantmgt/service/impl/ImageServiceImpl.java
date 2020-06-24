package com.greenacademy.restaurantmgt.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.greenacademy.restaurantmgt.service.ImageService;

/**
 * Add your description here.
 *
 * @author: thainguyen
 * @since: 10/Nov/2019
 */

@Transactional
@Service
public class ImageServiceImpl implements ImageService {
    private String rootName = "/tmp";

    
    public String uploadFile(String uploadRootPath, MultipartFile file) {
        try {
        	InputStream fis = file.getInputStream();
            byte[] data = new byte[fis.available()];
            fis.read(data);

            FileOutputStream out = new FileOutputStream(new File(uploadRootPath + "/" + getFileName(file)));

            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("That bai");
        }

        System.out.println("Thanh cong : " + uploadRootPath + "/" + getFileName(file));

        return getFileName(file);
    }

    
    public String getFileName(MultipartFile filepart) {
       return filepart.getOriginalFilename().replace(" ","-");
    }

    
    private void createDirectoryIfNorExist(Path path) throws IOException {
        if (Files.notExists(path, new LinkOption[0])) {
            Files.createDirectory(path);
        }
    }

    
    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    
    public String getRootName() {
        return this.rootName;
    }

    
    private String buildRootPath() {
        return this.getRootName() + "/" + "products" + "/";
    }
}
