package hyundai.cc.filemanage.mapper;

import hyundai.cc.filemanage.file.dto.AttachFileDTO;

import java.sql.SQLException;

public interface FileMapper {
    public void uploadFileToDB(AttachFileDTO attachFileDTO) throws SQLException;
}
