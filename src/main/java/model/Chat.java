package model;

public class Chat {
	private String boardId;
	private String userId;
	private String title;
	private String categoryId;
	
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
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String titel) {
		this.title = titel;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
	public Chat(String boardId, String userId, String title, String categoryId) {
		this.boardId = boardId;
		this.userId = userId;
		this.title = title;
		this.categoryId = categoryId;
	}
	public Chat() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
