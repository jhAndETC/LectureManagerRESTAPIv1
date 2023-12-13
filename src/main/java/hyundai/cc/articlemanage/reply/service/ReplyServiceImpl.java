package hyundai.cc.articlemanage.reply.service;

import hyundai.cc.articlemanage.reply.dto.ReplyCreateDTO;
import hyundai.cc.articlemanage.reply.dto.ReplyDTO;
import hyundai.cc.articlemanage.reply.mapper.ReplyMapper;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class ReplyServiceImpl implements ReplyService{
    private final ReplyMapper replyMapper;

    public ReplyServiceImpl(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    @Override
    public void createReply(Long articleId, String userId, ReplyCreateDTO content) {
        log.info("service");
        log.info(userId);
        log.info(String.valueOf(articleId));
        replyMapper.createReply(articleId, userId,content);
    }

    @Override
    public void createReReply(Long articleId, String userId, Long parentId, ReplyCreateDTO content) {
        replyMapper.createReReply(articleId, userId,parentId,content);
    }

    @Override
    public List<ReplyDTO> getReply(Long articleId) {
        return replyMapper.getReply(articleId);
    }

    @Override
    public List<ReplyDTO> getReReply(Long parentId) {
        return replyMapper.getReReply(parentId);
    }
}
