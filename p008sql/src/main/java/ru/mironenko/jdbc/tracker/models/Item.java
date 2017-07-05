package ru.mironenko.jdbc.tracker.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
*Class Item to create items in tracker
*@author mironenko
*@since 24.08.2016
*@version 1
**/
public class Item{

	/**
	 * name of item.
	 */
	private String name;

	/**
	 * Description of item.
	 */
	private String description;

	/**
	 * Time of item's creation.
	 */
	private Date timeOfCreation;

	/**
	 * Item's id.
	 */
	private int id;

	/**
	 * Comments of item.
	 */
	private List<Comment> comments = new ArrayList<Comment>();

	/**
	 * Default constructor
	 */
	public Item(){}

	/**
	 * Constructor of Item with two parameters
	 * @param name - name of item
	 * @param description - description of item
	 */
	public Item(String name, String description){
		this.name =  name;
		this.description = description;
		this.timeOfCreation = new Date();
	}

	/**
	 * Constructor of item
	 * @param id - id of item from table items
	 * @param name - name of item
	 * @param description - description of item
	 * @param create_time - date of creation of item
	 */
	public Item(int id, String name, String description, Timestamp create_time) {
		this.id = id;
		this.name =  name;
		this.description = description;
		this.timeOfCreation = create_time;
	}

	/**
	 * Getter of name
	 * @return name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Getter of description
	 * @return description
	 */
	public String getDescription(){
		return this.description;
	}

	/**
	 * Getter of time of creation
	 * @return time of creation
	 */
	public Date getTimeOfCreation(){
		return this.timeOfCreation;
	}

	/**
	 * Getter of id
	 * @return id
	 */
	public int getId(){
		return this.id;
	}

	/**
	 * Setter of id
	 * @param id - id of item
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Setter of comment
	 * @param comments - list of item's comments
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Adds comment to list of item's comments
	 * @param comment - new comment for addition
	 */
	public void addComment(Comment comment){
		this.comments.add(comment);
	}

	@Override
	public String toString(){
		return "Item - " + " id " + getId() + " name " + getName() +" description " + getDescription() +
				" time of creation " + getTimeOfCreation() +
				(this.comments.isEmpty() ? " no comments" : " comments : " + this.comments.toString() );
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}