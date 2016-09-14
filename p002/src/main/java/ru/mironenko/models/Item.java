package ru.mironenko.models;

public class Item{
	
	private String name;
	private String description;
	private long timeOfCreation;
	private String id;
	private String[] comments;
	private int index = 0;
	
	public Item(){};
	
	public Item(String name, String description, long timeOfCreation){
		this.name = name;
		this.description = description;
		this.timeOfCreation = timeOfCreation;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public long getTimeOfCreation(){
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
	
	public String[] getComment(){
		return this.comments;
	}
	
	public void setComment(String[] comments){
		this.comments = comments;
	}
	
	public void addComment(String comment){
		this.comments[index++] = comment;
	}
	
}