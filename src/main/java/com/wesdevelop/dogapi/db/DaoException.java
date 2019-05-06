package com.wesdevelop.dogapi.db;

public class DaoException extends RuntimeException {
    private static final long serialVersionUID = 37L;

    public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}