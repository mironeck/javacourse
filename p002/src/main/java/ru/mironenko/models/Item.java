package ru.mironenko.models;

import java.util.Arrays;
import java.util.Date;

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
	private Comment[] comments = new Comment[10];
	private int indexComments = 0;
	
	public Item(){}

	public Item(String name, String description){
		this.name =  name;
		this.description = description;
		this.timeOfCreation = new Date();
	}

	public int getIndexComments(){
		return this.indexComments;
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
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public Comment[] getComment(){
		Comment[] result = this.comments;
		return Arrays.copyOf(result, indexComments);
	}
	
	public void setComment(Comment[] comments){
		this.comments = comments;
	}
	
	public void addComment(Comment comment){
		if (this.indexComments == this.comments.length){
			Comment[] temp = new Comment[indexComments + 10];
			System.arraycopy(this.comments, 0, temp, 0, indexComments);
			this.comments = temp;
		}
		this.comments[indexComments++] = comment;
	}

	@Override
	public String toString(){
		return "Item - " + " id " + getId() + " name " + getName() +" description " + getDescription() +
				" time of creation " + getTimeOfCreation() + (this.indexComments == 0 ? " no comments" : " comments : " + Arrays.toString(this.getComment()) );
	}
}