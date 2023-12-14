package hyundai.cc.filemanage.mapper;

import hyundai.cc.filemanage.file.dto.AttachFileDTO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface FileMapper {
    public void uploadFileToDB(@Param("type") String type,@Param("id") String id,@Param("file")AttachFileDTO attachFileDTO) throws SQLException;
}
