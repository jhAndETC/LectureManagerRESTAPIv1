package hyundai.cc.articlemanage.reply.mapper;

import hyundai.cc.articlemanage.reply.dto.ReplyCreateDTO;
import hyundai.cc.articlemanage.reply.dto.ReplyDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
    public void createReply(@Param("articleId")Long articleId, @Param("userId")String userId, @Param("content") ReplyCreateDTO content);
    public void createReReply(@Param("articleId")Long articleId, @Param("userId") String userId, @Param("parentId")Long parentId, @Param("content")ReplyCreateDTO content);
    public List<ReplyDTO> getReply(@Param("articleId")Long articleId);
    public List<ReplyDTO> getReReply(@Param("parentId")Long parentId);
}
