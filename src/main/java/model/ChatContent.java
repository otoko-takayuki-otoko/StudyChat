package model;

public class ChatContent {
	private String boardId;
	private String userId;
	private String sentdatetime;
	private String comment;
	
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getSentdatetime() {
		return sentdatetime;
	}
	public void setSentdatetime(String sentdatetime) {
		this.sentdatetime = sentdatetime;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	public ChatContent(String boardId, String userId, String sentdatetime, String comment) {
		this.boardId = boardId;
		this.userId = userId;
		this.sentdatetime = sentdatetime;
		this.comment = comment;
	}

}
