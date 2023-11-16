package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ReplyDAO;
import com.dto.ReplyDTO;

@Service
@Transactional
public class ReplyService {

    @Autowired
    private ReplyDAO replyDAO;

    public int addReply(ReplyDTO replyDTO) {
        return replyDAO.addReply(replyDTO);
    }

    public List<ReplyDTO> getAllReplies() {
        return replyDAO.findAll();
    }

    public ReplyDTO getReplyById(int reply_id) {
        return replyDAO.findById(reply_id);
    }

    public void updateReply(ReplyDTO replyDTO) {
        replyDAO.updateReply(replyDTO);
    }

    public void deleteReplyById(int id) {
        replyDAO.deleteById(id);
    }
    public List<ReplyDTO> selectReplyByNickname(String nickname){
    	return replyDAO.selectReplyByNickname(nickname);
    }
    
}