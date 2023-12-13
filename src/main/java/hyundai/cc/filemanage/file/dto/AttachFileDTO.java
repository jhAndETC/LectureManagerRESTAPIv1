package hyundai.cc.filemanage.file.dto;

import lombok.Data;

@Data
public class AttachFileDTO {
    private String attachType;
    private long byteSize;
    private String fileName;
    private String uploadPath;
//    private boolean image;
}
