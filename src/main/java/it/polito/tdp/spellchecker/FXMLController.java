package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbLanguage;

    @FXML
    private TextArea txtError;

    @FXML
    private TextArea txtInput;

    @FXML
    private Label txtNumErr;

    @FXML
    private Label txtTimeExecution;
    
    private Dictionary model;

    @FXML
    void handleClearText(ActionEvent event) {
    	txtInput.clear();
    	txtError.clear();
    	txtNumErr.setText("");
    }
    
    @FXML
    void handleLanguage(ActionEvent event) {
    	this.model.loadDictionary(cmbLanguage.getValue());
    }

    @FXML
    void handleSpellCheck(ActionEvent event) {
    	try{
    		cmbLanguage.getValue().equals("");
    		long start=System.nanoTime();
    		String input = txtInput.getText().toLowerCase();
    		input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    		List<String> inputTextList = new ArrayList<String>();
    		for(String s : input.split(" ")) {
    			inputTextList.add(s);
    		}
//    		List<RichWord> check = model.spellCheckTextLinear(inputTextList);
    		List<RichWord> check = model.spellCheckTextDichotomic(inputTextList);
    		int numErr=0;
    		for(RichWord r : check) {
    			if(!r.isCorrect()) {
    				numErr++;
    				txtError.appendText(r.getTest()+"\n");
    			}
    		}
    		if(numErr>0) {
    			txtNumErr.setText("The text contains "+numErr+" errors");    		
    		}else {
    			txtNumErr.setText("The text is correct"); 
    		}
    		long fine=System.nanoTime();
    		txtTimeExecution.setText("Spell check completed in "+Double.toString((double) (fine-start)/1000000000)+" s");
    	}catch(Exception e) {
    		txtNumErr.setText("Choose a dictionary");
    	}

    }
    
    public void setModel(Dictionary model) {
    	this.model=model;
    }

    @FXML
    void initialize() {
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtError != null : "fx:id=\"txtError\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumErr != null : "fx:id=\"txtNumErr\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTimeExecution != null : "fx:id=\"txtTimeExecution\" was not injected: check your FXML file 'Scene.fxml'.";
        
        cmbLanguage.getItems().clear();
        cmbLanguage.getItems().add("English");
        cmbLanguage.getItems().add("Italiano");

    }

}