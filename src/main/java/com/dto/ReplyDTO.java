package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ReplyDTO")
public class ReplyDTO {
	int reply_id;
	int momo_id;
	String nickname;
	String replyContent;
	String replyDate;
	String fixDate;
	public ReplyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReplyDTO(int reply_id, int momo_id, String nickname, String replyContent, String replyDate, String fixDate) {
		super();
		this.reply_id = reply_id;
		this.momo_id = momo_id;
		this.nickname = nickname;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.fixDate = fixDate;
	}
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getMomo_id() {
		return momo_id;
	}
	public void setMomo_id(int momo_id) {
		this.momo_id = momo_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	public String getFixDate() {
		return fixDate;
	}
	public void setFixDate(String fixDate) {
		this.fixDate = fixDate;
	}

	@Override
	public String toString() {
		return "ReplyDTO [reply_id=" + reply_id + ", momo_id=" + momo_id + ", nickname=" + nickname + ", replyContent="
				+ replyContent + ", replyDate=" + replyDate + ", fixDate=" + fixDate + "]";
	}
}
