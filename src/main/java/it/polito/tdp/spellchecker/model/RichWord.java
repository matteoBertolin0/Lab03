package it.polito.tdp.spellchecker.model;

public class RichWord {
	String test;
	boolean isCorrect;
	
	public RichWord(String test, boolean isCorrect) {
		this.test = test;
		this.isCorrect = isCorrect;
	}

	public String getTest() {
		return test;
	}

	public boolean isCorrect() {
		return isCorrect;
	}
	
}
