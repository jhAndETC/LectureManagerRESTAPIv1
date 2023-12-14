package hyundai.cc.articlemanage.reply.mapper;

import hyundai.cc.articlemanage.article.dto.ArticleDTO;
import hyundai.cc.articlemanage.reply.dto.ReplyCreateDTO;
import hyundai.cc.articlemanage.reply.dto.ReplyDTO;
import hyundai.cc.domain.ArticleCriteria;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface ReplyMapper {
    public void createReply(@Param("articleId")Long articleId, @Param("userId")String userId, @Param("content") ReplyCreateDTO content);
    public void createReReply(@Param("articleId")Long articleId, @Param("userId") String userId, @Param("parentId")Long parentId, @Param("content")ReplyCreateDTO content);
    public List<ReplyDTO> getReply(@Param("articleId")Long articleId);
    public List<ReplyDTO> getReReply(@Param("parentId")Long parentId);
    public int getReplyTotal(@Param("articleId") long articleId) throws Exception;
    public int getReReplyTotal(@Param("parentId") long parentId) throws Exception;
    public List<ReplyDTO> getReplyListByArticleWithPagination(@Param("replyCriteria") ArticleCriteria replyCriteria) throws SQLException;
    public List<ReplyDTO> getReplyListByArticleWithPaginationFirst(@Param("replyCriteria") ArticleCriteria replyCriteria) throws SQLException;
    public List<ReplyDTO> getReReplyListByArticleWithPagination(@Param("replyCriteria") ArticleCriteria replyCriteria) throws SQLException;
    public List<ReplyDTO> getReReplyListByArticleWithPaginationFirst(@Param("replyCriteria") ArticleCriteria replyCriteria) throws SQLException;

}
