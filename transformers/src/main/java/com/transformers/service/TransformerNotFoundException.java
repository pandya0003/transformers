package com.transformers.service;

public class TransformerNotFoundException extends RuntimeException {

	  public TransformerNotFoundException(int id) {
	    super("Could not find the transformer with id " + id);
	  }
	}