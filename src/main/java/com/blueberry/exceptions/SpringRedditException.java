package com.blueberry.exceptions;

public class SpringRedditException extends RuntimeException{

	private static final long serialVersionUID = 7388608959690773847L;

	public SpringRedditException(String msg, Exception ex) {
		super(msg, ex);
	}

	public SpringRedditException(String msg) {
		super(msg);
	}
	
}
