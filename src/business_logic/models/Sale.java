package business_logic.models;

import java.sql.Date;

public class Sale {
	
	private int id_sale;
	private int amount;
	private Date sale_date;
	private Club seller;
	private Club buyer;
	private Player player;
	
	public Sale(int id_sale, int amount, Date sale_date, Club seller, Club buyer, Player player) {
		super();
		this.id_sale = id_sale;
		this.amount = amount;
		this.sale_date = sale_date;
		this.seller = seller;
		this.buyer = buyer;
		this.player = player;
	}

	public int getId_sale() {
		return id_sale;
	}

	public void setId_sale(int id_sale) {
		this.id_sale = id_sale;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getSale_date() {
		return sale_date;
	}

	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}

	public Club getSeller() {
		return seller;
	}

	public void setSeller(Club seller) {
		this.seller = seller;
	}

	public Club getBuyer() {
		return buyer;
	}

	public void setBuyer(Club buyer) {
		this.buyer = buyer;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	
}
