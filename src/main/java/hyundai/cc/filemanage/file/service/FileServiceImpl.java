package hyundai.cc.filemanage.file.service;

import hyundai.cc.filemanage.file.controller.BucketController;
import hyundai.cc.filemanage.file.dto.AttachFileDTO;
import hyundai.cc.filemanage.mapper.FileMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Log
@Service
@PropertySource("classpath:app.properties")
public class FileServiceImpl implements FileService {

    @Value("azure-blob://jhandetc/")
    private String uploadFolder;

    private final BucketController bucketController;
    private final FileMapper fileMapper;

    public FileServiceImpl(BucketController bucketController, FileMapper fileMapper) {
        this.bucketController = bucketController;
        this.fileMapper = fileMapper;
    }

    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        log.info(str);
        return str.replace("-", File.separator);
    }

    private boolean checkImageType(File file) {
        try {
            String contentType = Files.probeContentType(file.toPath());
            return contentType.startsWith("image");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<AttachFileDTO> upload(String type, String id, MultipartFile[] uploadFile) throws Exception {
        List<AttachFileDTO> list = new ArrayList<>();

        for (MultipartFile multipartFile : uploadFile) {
            log.info("----------------");
            log.info("Upload File Name: " + multipartFile.getOriginalFilename());
            log.info("Upload File Size: " + multipartFile.getSize());

            AttachFileDTO attachFileDTO = new AttachFileDTO();
            String uploadFileName = multipartFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            uploadFileName = uuid.toString() + "_" + uploadFileName;
            attachFileDTO.setFileName(uploadFileName);
            String contentType = multipartFile.getContentType();
            log.info("FileServiceImpl contentType: " + contentType);
            attachFileDTO.setAttachType(contentType);
            attachFileDTO.setByteSize(multipartFile.getSize());

            try {
                String FileURL = bucketController.UploadObject(type, id,uploadFileName, contentType, multipartFile);
                attachFileDTO.setUploadPath(FileURL);
                // add to List
                list.add(attachFileDTO);
                try{
                    fileMapper.uploadFileToDB(attachFileDTO);
                } catch (Exception e){
                    log.info(e.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  // end for
        return list;
    }

    @Override
    public ResponseEntity<byte[]> getFile(String fileName) throws Exception {
        File file = new File(uploadFolder + fileName);
        log.info("fileService getFile file: " + file);
        ResponseEntity<byte[]> result = null;
        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void uploadFileToDB(AttachFileDTO attachFileDTO) throws Exception {
        try{
            fileMapper.uploadFileToDB(attachFileDTO);
        } catch (Exception e){
            log.info(e.getMessage());
        }
    }


}
