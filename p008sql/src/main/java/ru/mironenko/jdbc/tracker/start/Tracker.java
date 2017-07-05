package ru.mironenko.jdbc.tracker.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mironenko.jdbc.lessonexamples.SQLStrorage;
import ru.mironenko.jdbc.tracker.models.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
/**
*Class Tracker is repository of items. It makes some actions with items:
*add, edit, delete, comment to item, get all items and filtered items.
*@author mironenko
*@since 04.07.2017
*@version 1
**/

 public class Tracker{
	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(SQLStrorage.class);

	/**
	 * instance of Properties;
 	 */
	private final Properties prop = new Properties();

	/**
	 * Reference to Connection of database
	 */
	private Connection conn;
	/**
	 * Store of filtered items
	 */
	private List<Item> filteredItems = new ArrayList<>();

	/**
	 * Constructor of Tracker. Reads url, username and password for connection to database.
	 * Creates tables of database if it does not exists.
	 * @throws IOException
	 */
	public Tracker() throws IOException {
        InputStream io = getClass().getClassLoader().getResourceAsStream("resources.properties");
        prop.load(io);
		String url = prop.getProperty("db.host");
		String username = prop.getProperty("db.login");
		String password = prop.getProperty("db.password");
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		createTablesIfNotExist();
	}

	/**
	 * Creates tables of database if it does not exists.
	 */
	private void createTablesIfNotExist() {
		try {
			PreparedStatement prItems = conn.prepareStatement("create table if not exists items(id serial primary key,name varchar(200),description varchar(500),create_time timestamp);" );
			PreparedStatement prComments = conn.prepareStatement("create table if not exists comments(" +
					"id serial primary key," +
					"items_id integer references items(id)," +
					"text varchar(2000));");
			prItems.execute();
			prComments.execute();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	*The method adds new item in table items
	*@param item
	*@return boolean. True if addition is succeed, false - if not.
	*/
	public boolean add(Item item) {

		boolean result = false;

		try {
			PreparedStatement pr = conn.prepareStatement("insert into items(name, description, create_time) values(?, ?, ?)");
			pr.setString(1, item.getName());
			pr.setString(2, item.getDescription());
			pr.setTimestamp(3, new java.sql.Timestamp(item.getTimeOfCreation().getTime()));
			pr.executeUpdate();
			result = true;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		return result;
	}


	/**
	*The method edits item: sets new name and description
	*@param fresh - item to update.
	*@return boolean: true if edition is succeed, false if not.
	*/
	public boolean edit(Item fresh){

		boolean result = false;

		try {
			PreparedStatement prEdit = conn.prepareStatement("update items set name = ?, description = ? where id = ?");
			prEdit.setString(1, fresh.getName());
			prEdit.setString(2, fresh.getDescription());
			prEdit.setInt(3, fresh.getId());
			prEdit.executeUpdate();
			result = true;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return  result;
	}
	
	/**
	*The method find item with key id
	*@param id - id of item to find
	*@return item with key id
	*/
	protected Item findById(int id){
		Item result = null;

		try {
			PreparedStatement prFind = conn.prepareStatement("select * from items where id = ?");
			PreparedStatement prComment = conn.prepareStatement("select * from comments where item_id = ?");
			prFind.setInt(1, id);
			ResultSet rs = prFind.executeQuery();
			if(rs.next()) {
				result = new Item(rs.getString("name"), rs.getString("description"));
			}
			if(result != null) {
				prComment.setInt(1, id);
				rs = prComment.executeQuery();
				while(rs.next()) {
					result.addComment(new Comment(rs.getString("text")));
				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	*The method returns all items created from db.
	*@return arrayList of all items created
	*/
	public List<Item> getAll(){

		List<Item> resultList = new ArrayList<>();
		try {
			PreparedStatement prAll = conn.prepareStatement("select * from items");
			ResultSet rs = prAll.executeQuery();
			while(rs.next()) {
				Item item = new Item(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getTimestamp("create_time"));
				item.setComments(getComment(item));
				resultList.add(item);
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return resultList;
	}

	/**
	 * Returns arrayList of comments of particular item.
	 * @param item - item for which gets comments
	 * @return arrayList of comments
	 */
	private List<Comment> getComment(Item item) {

		List<Comment> result = new ArrayList<>();

		PreparedStatement prComment = null;
		try {
			prComment = conn.prepareStatement("select * from comments as c where c.items_id = ?");
			prComment.setInt(1, item.getId());
			ResultSet rsComm = prComment.executeQuery();
			while(rsComm.next()) {
				result.add(new Comment(rsComm.getString("text")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	*The method deletes item from table items
	*@param id id of item to delete.
	*@return boolean true if deletion is succeed, false if not.
	 */
	public boolean deleteItem(int id){

		boolean result = false;

		try {
			PreparedStatement prDel = conn.prepareStatement("delete from items where id = ?");
			prDel.setInt(1, id);
			prDel.executeUpdate();
			result = true;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}  
	
	/**
	*The method adds comment to item in table comments
	*@param itemId id of item to comment
	*@param comment - comment
	*@return boolean , true if addition of comment is succeed, false if not.
	*/
	public boolean addComment(int itemId , Comment comment){

		boolean result = false;
		try {
			PreparedStatement prAddComm = conn.prepareStatement("insert into comments(items_id, text) values(?, ?)");
			prAddComm.setInt(1, itemId);
			prAddComm.setString(2, comment.getComment());
			prAddComm.executeUpdate();
			result = true;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		return result;
	}

	/**
	*The method returns arrayList of items sorted by filter
	*@param filter - filter
	*@return arrayList of items sorted by filter
	*/

	public List<Item> filterItems(Filter filter){

		this.filteredItems.clear();
		String text = filter.getFilter();
		try {
			PreparedStatement prFilt = conn.prepareStatement("select * from items where name like ? or description like ?");
			prFilt.setString(1, String.format("%s%s%s", "%", text, "%"));
			prFilt.setString(2, String.format("%s%s%s", "%", text, "%"));
			ResultSet rs = prFilt.executeQuery();
			while(rs.next()) {
				Item item = new Item(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getTimestamp("create_time"));
				item.setComments(getComment(item));
				this.filteredItems.add(item);
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		return  this.filteredItems;
	}
}