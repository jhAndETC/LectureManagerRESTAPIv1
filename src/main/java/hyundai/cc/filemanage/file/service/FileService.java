package hyundai.cc.filemanage.file.service;

import hyundai.cc.filemanage.file.dto.AttachFileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    public List<AttachFileDTO> upload(String type, String id, MultipartFile[] uploadFile) throws Exception;
    public ResponseEntity<byte[]> getFile(String fileName) throws Exception;
    public void uploadFileToDB(AttachFileDTO attachFileDTO) throws Exception;
//    public Resource downloadFile(String userAgent, String fileName) throws Exception;
//    public String deleteFile(String fileName, String type) throws Exception;
}
