package ru.mironenko.models;

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
	 * @params name, description, timeOfCreation, id, comments, index
	 */
	private String name;
	private String description;
	private Date timeOfCreation;
	private String id;
	private List<Comment> comments = new ArrayList<Comment>();
	
	public Item(){}

	public Item(String name, String description){
		this.name =  name;
		this.description = description;
		this.timeOfCreation = new Date();
	}

	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public Date getTimeOfCreation(){
		return this.timeOfCreation;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public List<Comment> getComment(){
		return this.comments;

	}

	public void addComment(Comment comment){
		this.comments.add(comment);
	}

	@Override
	public String toString(){
		return "Item - " + " id " + getId() + " name " + getName() +" description " + getDescription() +
				" time of creation " + getTimeOfCreation() +
				(this.comments.isEmpty() ? " no comments" : " comments : " + this.getComment().toString() );
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