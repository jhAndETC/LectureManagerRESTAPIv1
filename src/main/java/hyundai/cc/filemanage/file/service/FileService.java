package hyundai.cc.filemanage.file.service;

import hyundai.cc.filemanage.file.domain.AttachFileDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    public List<AttachFileDTO> upload(MultipartFile[] uploadFile) throws Exception;
    public ResponseEntity<byte[]> getFile(String fileName) throws Exception;
    public Resource downloadFile(String userAgent, String fileName) throws Exception;
    public String deleteFile(String fileName, String type) throws Exception;
}
