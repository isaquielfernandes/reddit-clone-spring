package com.blueberry.exceptions;

public class PostNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1254874533299205142L;

	public PostNotFoundException(String msg) {
		super(msg);
	}

}
