package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	List<String> dizionario;
	
	public Dictionary() {
		this.dizionario = new LinkedList<String>();
	}
	
	public void loadDictionary(String language) {
		try {
			FileReader fr=new FileReader("src/main/resources/English.txt");
			if(language.equals("English")) {
				fr = new FileReader("src/main/resources/English.txt");
			}else if(language.equals("Italiano")){
				fr = new FileReader("src/main/resources/Italian.txt");
			}
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
				dizionario.add(word);
			}
			br.close();
			 } catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		 List<RichWord> result = new LinkedList<RichWord>();
		for(String s : inputTextList){
			if(this.dizionario.contains(s)) {
				RichWord r = new RichWord(s, true);
				result.add(r);
			}else {
				RichWord r = new RichWord(s, false);
				result.add(r);
			}
		}
		return result;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		List<RichWord> result = new LinkedList<RichWord>();
		
		for(String s : inputTextList){
			int low = 0;
			boolean ok=false;
			int high = dizionario.size();
			while(low<=high) {
				int mid = low + ((high - low) / 2);
				if(s.equals(dizionario.get((int)mid))) {
					ok=true;
					break;
				}else if(s.compareTo(dizionario.get((int)mid))<0) {
					high = mid - 1;
				}else if(s.compareTo(dizionario.get((int)mid))>0) {
					low = mid + 1;
				}				
			}
			if(ok) {
				RichWord r = new RichWord(s, ok);
				result.add(r);
			}else {
				RichWord r = new RichWord(s, ok);
				result.add(r);
			}
		}
		return result;
	}
	
}
