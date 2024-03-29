package com.aranseiki.imagehandlejava.service;

import org.springframework.http.ResponseEntity;
import com.aranseiki.imagehandlejava.model.dto.ImageRequest;

public interface ImageService {

    ResponseEntity<String> resizeImage(ImageRequest request);
}
