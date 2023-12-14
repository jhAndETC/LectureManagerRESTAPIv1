package hyundai.cc.articlemanage.reply.service;

import hyundai.cc.articlemanage.reply.dto.ReplyCreateDTO;
import hyundai.cc.articlemanage.reply.dto.ReplyDTO;
import hyundai.cc.domain.ArticleCriteria;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

public interface ReplyService {
    public void createReply(Long articleId, String userId, ReplyCreateDTO content);
    public void createReReply(Long articleId, String userId, Long parentId, ReplyCreateDTO content);

    public List<ReplyDTO> getReply(Long articleId);
    public List<ReplyDTO> getReReply(Long parentId);
    public int getReplyTotal(long articleId) throws Exception;
    public int getReReplyTotal(long parentId) throws Exception;
    public HashMap<String, Object> getReplyListByArticleWithPagination(ArticleCriteria replyCriteria) throws Exception;
    public HashMap<String, Object> getReReplyListByArticleWithPagination(ArticleCriteria replyCriteria) throws Exception;

}
