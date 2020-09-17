package me.minho.miristiudy.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import me.minho.miristiudy.config.S3Config;
import me.minho.miristiudy.domain.File;
import me.minho.miristiudy.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3UploadService {

    private final AmazonS3 amazonS3;
    private final S3Config s3Config;
    private final FileRepository fileRepository;

    public String upload(MultipartFile multipartFile) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileName = multipartFile.getOriginalFilename();
        String objectKey = s3Config.getObjectBasePath().resolve(uuid).resolve(fileName).toString();

        PutObjectRequest putObjectRequest = new PutObjectRequest(
                s3Config.getBucketName(),
                objectKey,
                multipartFile.getInputStream(),
                makeObjectMetaData(multipartFile))
                .withCannedAcl(CannedAccessControlList.PublicRead);

        amazonS3.putObject(putObjectRequest);
        fileRepository.save(new File(objectKey, multipartFile.getSize()));
        return amazonS3.getUrl(s3Config.getBucketName(), objectKey).toString();
    }

    private ObjectMetadata makeObjectMetaData(MultipartFile multipartFile) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setCacheControl("public, max-age=86400");
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(multipartFile.getSize());
        return metadata;
    }
}
