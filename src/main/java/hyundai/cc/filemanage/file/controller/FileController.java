package hyundai.cc.filemanage.file.controller;

import hyundai.cc.filemanage.file.domain.AttachFileDTO;
import hyundai.cc.filemanage.file.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import lombok.extern.java.Log;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;

@Log
@RestController
@RequestMapping("blob")
@PropertySource("classpath:app.properties")
public class FileController {
    @Value("azure-blob://jhandetc/")
    private String uploadFolder;
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<?> upload(MultipartFile[] uploadFile) throws Exception {
        log.info("upload");
        try {
            List<AttachFileDTO> list = fileService.upload(uploadFile);
            log.info("File Controller: " + list.toString());
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>("upload Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/display")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String fileName) throws Exception {
        log.info("fileName: " + fileName);
        ResponseEntity<byte[]> result = fileService.getFile(fileName);
        return result;
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {
        log.info("download file: " + fileName);
//        Resource resource = fileService.downloadFile(userAgent, fileName);

        Resource resource = new FileSystemResource(uploadFolder + fileName);
        if (resource.exists() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("resource: " + resource);
        String resourceName = resource.getFilename();
        String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
        HttpHeaders headers = new HttpHeaders();
        try {
            String downloadName = null;
            if (userAgent.contains("Trident")) {
                log.info("IE browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("/+", " ");
            } else if (userAgent.contains("Edges")) {
                log.info("Edge browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
                log.info("Edge name: " + downloadName);
            } else {
                log.info("Chrome browser");
                downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
            }
            log.info("downloadName: " + downloadName);

            headers.add("Content-Deiposition",
                    "attachment; filename=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);

    }

    @DeleteMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<String> deleteFile(String fileName, String type) {
        log.info("deleteFile: " + fileName);
        File file;
        try {
            file = new File(uploadFolder + URLDecoder.decode(fileName, "UTF-8"));
            file.delete();
            if (type.equals("image")) {
                String largeFileName = file.getAbsolutePath().replace("s_", "");
                log.info("largeFileName: " + largeFileName);
                file = new File(largeFileName);
                file.delete();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }
}
