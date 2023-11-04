package controllers;

/*
	In case if an invalid or past date is written as deadline in the TaskEditor, this exception is thrown
*/

public class InvalidDateException extends Exception {

	private static final long serialVersionUID = 1L; // Throws warning if this is not declared

	public InvalidDateException(String message) {
        super(message);
    }
}