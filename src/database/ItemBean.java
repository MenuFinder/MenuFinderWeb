package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemBean extends Bean {

	private static final long serialVersionUID = 1L;
	private static final String table = "menufinder.item";

	private long id;
	private String name;
	private String description;
	private double price;
	private double score;
	private long restaurant;

	public ItemBean() {
		super();
	}

	public ItemBean(long id, String name, String description, double price, double score, long restaurant) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.score = score;
		this.restaurant = restaurant;
		isNewRecord = false;
	}

	public ItemBean(String name, String description, double price, double score, long restaurant) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.score = score;
		this.restaurant = restaurant;
		isNewRecord = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public long getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(long restaurant) {
		this.restaurant = restaurant;
	}

	private long id;
	private String name;
	private String description;
	private double price;
	private double score;
	private long restaurant;

	protected String getDeleteQuery() {
		return "DELETE FROM " + table + " WHERE id = " + id;
	}

	protected String getUpdateQuery() {
		return "UPDATE " + table + " SET name = '" + name + "', description = '" + description + "', price = '" + price
				+ "', score = '" + score + "', restaurant = " + restaurant + "' WHERE id = " + id;
	}

	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (name, description, price, score, restaurant) VALUES ('" + name + "', '"
				+ description + "', '" + price + "', '" + score + "', " + restaurant + "')";
	}

	public static ItemBean getRestaurantById(long id) {
		try {
			DBManager db = DBManager.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM " + table + " WHERE id = " + id);
			rs.next();
			return getItemsFromRS(rs);
		} catch (SQLException ex) {
			System.err.println("Error retrieving items with id: '" + id + "'.");
			ex.printStackTrace();
			return null;
		}
	}

	private static ItemBean getItemsFromRS(ResultSet rs) {
		try {
			return new ItemBean(rs.getLong("id"), rs.getString("name"), rs.getString("description"),
					rs.getDouble("price"), rs.getDouble("score"), rs.getLong("restaurant"));
		} catch (SQLException e) {
			System.err.println("Error retrieving bean.");
			e.printStackTrace();
			return null;
		}
	}

}