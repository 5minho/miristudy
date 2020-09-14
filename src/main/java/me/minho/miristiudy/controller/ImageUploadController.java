package me.minho.miristiudy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.minho.miristiudy.service.S3UploadService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageUploadController {

    private final S3UploadService s3UploadService;

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String upload(MultipartFile image) throws IOException {
        return s3UploadService.upload(image);
    }

}
