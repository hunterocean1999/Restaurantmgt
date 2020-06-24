package com.greenacademy.restaurantmgt.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Add your description here.
 *
 * @author: thainguyen
 * @since: 10/Nov/2019
 */

@Transactional
@Service
public interface ImageService {
    String uploadFile(String uploadRootPath, MultipartFile file);
}

