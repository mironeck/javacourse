package ru.mironenko.models;

public class Task extends Item{
	
	private String name;
	private String description;
	
	public Task(String name, String description){
		this.name =  getName();
		this.description = getDescription();
	}
}