package hyundai.cc.filemanage.file.dto;

import lombok.Data;

@Data
public class BoardAttachVO {
    private Long fileId;
    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean fileType;

}
