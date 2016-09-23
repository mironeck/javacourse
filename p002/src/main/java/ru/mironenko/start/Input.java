package ru.mironenko.start;

/**
 * @intetface Input for in/out system
 * @author mironenko
 * @since 17.10.2016
 */
public interface Input{
	
	String ask(String question);
	int ask(String question, int[] range);
}