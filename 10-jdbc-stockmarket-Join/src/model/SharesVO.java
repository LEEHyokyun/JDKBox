package model;

public class SharesVO {
	private String id;
	private String name;
	private String symbol; // 주식명
	private int quantity;
	private int totalPrice;

	public SharesVO() {
		super();
	}

	public SharesVO(String name, String symbol, int quantity, int totalPrice) {
		super();
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public SharesVO(String id, String name, String symbol, int quantity, int totalPrice) {
		super();
		this.id = id;
		this.name = name;
		this.symbol = symbol;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "SharesVO [id=" + id + ", name=" + name + ", symbol=" + symbol + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + "]";
	}

}
