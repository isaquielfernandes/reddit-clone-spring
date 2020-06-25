package com.blueberry.exceptions;

public class SubRedditNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3641194113947315528L;

	public SubRedditNotFoundException(String msg) {
		super(msg);
	}

}
