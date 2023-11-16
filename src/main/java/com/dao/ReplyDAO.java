package com.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dto.ReplyDTO;

@Repository
public class ReplyDAO {
    
    @Autowired
    private SqlSessionTemplate sqlSession;

    public int addReply(ReplyDTO replyDTO) {
    	System.out.println("ReplyDAO"+replyDTO);
        return sqlSession.insert("ReplyMapper.addReply", replyDTO);
    }

    public List<ReplyDTO> findAll() {
        return sqlSession.selectList("ReplyMapper.selectAllReplies");
    }

    public ReplyDTO findById(int reply_id) {
        return sqlSession.selectOne("ReplyMapper.selectReplyById", reply_id);
    }

    public void updateReply(ReplyDTO replyDTO) {
        sqlSession.update("ReplyMapper.updateReply", replyDTO);
    }

    public void deleteById(int id) {
        sqlSession.delete("ReplyMapper.deleteReplyById", id);
    }
    public List<ReplyDTO> selectReplyByNickname(String nickname) {
        return sqlSession.selectList("ReplyMapper.selectReplyByNickname",nickname);
    }
    
    
}