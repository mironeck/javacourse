package ru.mironenko.jdbc.tracker.models;

/**
 * Class Comment
 */
public class Comment{

	/**
	 * Value of comment.
	 */
	private String comment;

	/**
	 * Constructor
	 * @param comment - String value of comment.
	 */
	public Comment(String comment){
		this.comment = comment;
	}

	/**
	 * Getter of comment.
	 * @return String value of comment.
	 * */
	public String getComment() {
		return comment;
	}


	@Override
	public String toString(){
		return getComment();
	}
}