package hyundai.cc.articlemanage.reply.service;

import hyundai.cc.articlemanage.reply.dto.ReplyCreateDTO;
import hyundai.cc.articlemanage.reply.dto.ReplyDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ReplyService {
    public void createReply(Long articleId, String userId, ReplyCreateDTO content);
    public void createReReply(Long articleId, String userId, Long parentId, ReplyCreateDTO content);

    public List<ReplyDTO> getReply(Long articleId);
    public List<ReplyDTO> getReReply(Long parentId);
}
