//package hyundai.cc.filemanage.file.controller;
//
//import hyundai.cc.filemanage.file.domain.AttachFileDTO;
//import lombok.extern.java.Log;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//@Log
//@RestController
//@RequestMapping("files")
//@PropertySource("classpath:app.properties")
//public class FileController {
//
//    @Value("${uploadPath}")
//    private String uploadFolder;
//
//    private String getFolder() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String str = sdf.format(date);
//        log.info(str);
//        return str.replace("-", File.separator);
//    }
//
//    private boolean checkImageType(File file) {
//        try {
//            String contentType = Files.probeContentType(file.toPath());
//            return contentType.startsWith("image");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    public ResponseEntity<List<AttachFileDTO>> uploadForm(MultipartFile[] uploadFile, Model model) {
//        log.info("upload");
//        List<AttachFileDTO> list = new ArrayList<>();
//
//        // make folder -------
//        File uploadPath = new File(uploadFolder, getFolder());
//        log.info("upload path: " + uploadPath);
//
//        if (uploadPath.exists() == false) {
//            uploadPath.mkdirs();
//        }  // make yyyy/mm/dd folder
//
//        log.info(uploadFile.toString());
//
//        for (MultipartFile multipartFile : uploadFile) {
//            log.info("----------------");
//            log.info("Upload File Name: " + multipartFile.getOriginalFilename());
//            log.info("Upload File Size: " + multipartFile.getSize());
//
//            AttachFileDTO attachFileDTO = new AttachFileDTO();
//            String uploadFileName = multipartFile.getOriginalFilename();
//            UUID uuid = UUID.randomUUID();
//            uploadFileName = uuid.toString() + "_" + uploadFileName;
//            attachFileDTO.setFileName(uploadFileName);
//
//            try {
//                File saveFile = new File(uploadPath, uploadFileName);
//                multipartFile.transferTo(saveFile);
//                attachFileDTO.setUuid(uuid.toString());
//                attachFileDTO.setUploadPath(uploadFileName);
//
//                // check image type file
//                if (checkImageType(saveFile)) {
//                    attachFileDTO.setImage(true);
//                }
//
//                // add to List
//                list.add(attachFileDTO);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }  // end for
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @GetMapping("/display")
//    @ResponseBody
//    public ResponseEntity<byte[]> getFile(String fileName){
//        log.info("fileName: " + fileName);
//        File file = new File(uploadFolder + fileName);
//        log.info("file: " + file);
//
//        ResponseEntity<byte[]> result = null;
//
//        try{
//            HttpHeaders header = new HttpHeaders();
//            header.add("Content-Type", Files.probeContentType(file.toPath()));
//            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//}
