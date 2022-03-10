package model;

public class GuestBookVO {
	private int guestbookNo;
	private String title;
	private String content;

	public GuestBookVO() {
		super();
	}

	//등록용 생성자: bookNo는 sequence에 의해 자동 생성
	public GuestBookVO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	//조회용
	public GuestBookVO(int guestbookNo, String title, String content) {
		super();
		this.guestbookNo = guestbookNo;
		this.title = title;
		this.content = content;
	}
	
	public void setGuestbookNo(int guestbookNo) {
		this.guestbookNo = guestbookNo;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "GuestBookVO [guestbookNo=" + guestbookNo + ", title=" + title + ", content=" + content + "]";
	}

}
