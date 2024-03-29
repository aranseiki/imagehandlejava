package com.aranseiki.imagehandlejava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.aranseiki.imagehandlejava.model.dto.ImageRequest;
import com.aranseiki.imagehandlejava.service.ImageService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class ImagehandlejavaController {

    private final ImageService imageService;

    public ImagehandlejavaController(ImageService imageService) {
        this.imageService = imageService;
    }
    
    @GetMapping("/hello")
    @ApiOperation(value = "Mensagem de saudação", response = String.class)
    public String sayHello() {
        return "Hello, world!";
    }

    @PostMapping("/resize-image")
    @ApiOperation(value = "Redimensionar imagem", notes = "Redimensiona uma imagem de acordo com os parâmetros fornecidos", response = String.class)
    public ResponseEntity<String> resizeImage(@RequestBody ImageRequest request) {
        // Chama o método do service para redimensionar a imagem
        ResponseEntity<String> response = imageService.resizeImage(request);
        // Retorna a resposta do service
        return response;
    }
}
