package hyundai.cc.articlemanage.reply.service;

import hyundai.cc.articlemanage.reply.dto.ReplyCreateDTO;
import hyundai.cc.articlemanage.reply.dto.ReplyDTO;
import hyundai.cc.articlemanage.reply.mapper.ReplyMapper;
import hyundai.cc.domain.ArticleCriteria;
import hyundai.cc.usermanage.user.dto.UserDTO;
import hyundai.cc.usermanage.user.dto.UserDTOMapper;
import hyundai.cc.usermanage.user.dto.UserResponseDTO;
import hyundai.cc.usermanage.user.service.UserService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Log
@Service
public class ReplyServiceImpl implements ReplyService{
    private final ReplyMapper replyMapper;
    private final UserService userservice;
    private final UserDTOMapper userDTOMapper;

    public ReplyServiceImpl(ReplyMapper replyMapper, UserService userservice, UserDTOMapper userDTOMapper) {
        this.replyMapper = replyMapper;
        this.userservice=userservice;
        this.userDTOMapper = userDTOMapper;
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

    @Override
    public int getReplyTotal(long articleId) throws Exception {
        try {
            return replyMapper.getReplyTotal(articleId);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public int getReReplyTotal(long parentId) throws Exception {
        try {
            return replyMapper.getReReplyTotal(parentId);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public HashMap<String, Object> getReplyListByArticleWithPagination(ArticleCriteria replyCriteria) throws Exception {
        try {
            log.info("replyServiceImpl: " + replyCriteria.toString());
            List<ReplyDTO> replyDTOList;
            if (replyCriteria.getCursor() == null) {
                replyDTOList = replyMapper.getReplyListByArticleWithPaginationFirst(replyCriteria);
                for (ReplyDTO replyDTO : replyDTOList) {
                    long totalrecomments=replyMapper.getReReplyTotal(replyDTO.getId())-1;
                    String replyerId = replyDTO.getReplyerId();
                    UserResponseDTO user = userDTOMapper.toUserResponseDTO(userservice.getUserDetail(replyerId));
                    replyDTO.setUserResponseDTO(user);
                    replyDTO.setTotalRecomments(totalrecomments);
                }
                log.info("getReplyListByArticleWithPaginationFirst 호출: " + replyDTOList.toString());
            } else {
                replyDTOList = replyMapper.getReplyListByArticleWithPagination(replyCriteria);
                for (ReplyDTO replyDTO : replyDTOList) {
                    long totalrecomments=replyMapper.getReReplyTotal(replyDTO.getId()-1);
                    String replyerId = replyDTO.getReplyerId();
                    UserResponseDTO user = userDTOMapper.toUserResponseDTO(userservice.getUserDetail(replyerId));
                    replyDTO.setUserResponseDTO(user);
                    replyDTO.setTotalRecomments(totalrecomments);
                }
                log.info("getReplyListByArticleWithPagination 호출: " + replyDTOList.toString());
            }
            try {
                // List의 마지막 요소를 가져오기
                ReplyDTO lastReply = replyDTOList.get(replyDTOList.size() - 1);
                // 마지막 reply의 replyId를 가져오기
                long lartReplyId = lastReply.getId();
                // 출력 또는 다른 용도로 사용
                log.info("가장 마지막 replyId: " + lartReplyId);
                HashMap<String, Object> map = new HashMap<>();
                map.put("replyDTOList", replyDTOList);
                map.put("next", lartReplyId);
                return map;
            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public HashMap<String, Object> getReReplyListByArticleWithPagination(ArticleCriteria replyCriteria) throws Exception {
        try {
            log.info("replyServiceImpl: " + replyCriteria.toString());
            List<ReplyDTO> rereplyDTOList;
            if (replyCriteria.getCursor() == null) {
                rereplyDTOList = replyMapper.getReReplyListByArticleWithPaginationFirst(replyCriteria);
                for (ReplyDTO rereplyDTO : rereplyDTOList) {
                    String replyerId = rereplyDTO.getReplyerId();
                    UserResponseDTO user = userDTOMapper.toUserResponseDTO(userservice.getUserDetail(replyerId));
                    rereplyDTO.setUserResponseDTO(user);
                }
                log.info("getReplyListByArticleWithPaginationFirst 호출: " + rereplyDTOList.toString());
            } else {
                rereplyDTOList = replyMapper.getReReplyListByArticleWithPagination(replyCriteria);
                for (ReplyDTO rereplyDTO : rereplyDTOList) {
                    String replyerId = rereplyDTO.getReplyerId();
                    UserResponseDTO user = userDTOMapper.toUserResponseDTO(userservice.getUserDetail(replyerId));
                    rereplyDTO.setUserResponseDTO(user);
                }
                log.info("getReplyListByArticleWithPagination 호출: " + rereplyDTOList.toString());
            }
            try {
                // List의 마지막 요소를 가져오기
                ReplyDTO lastReply = rereplyDTOList.get(rereplyDTOList.size() - 1);
                // 마지막 reply의 replyId를 가져오기
                long lartReplyId = lastReply.getId();
                // 출력 또는 다른 용도로 사용
                log.info("가장 마지막 replyId: " + lartReplyId);
                HashMap<String, Object> map = new HashMap<>();
                map.put("replyDTOList", rereplyDTOList);
                map.put("next", lartReplyId);
                return map;
            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }
}
