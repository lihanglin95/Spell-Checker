package spellChecker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class CS245A1 {
	static Storage dataStructure;
	
	public static void main(String args[]) throws IOException {
		CS245A1 test = new CS245A1();
		String input = args[0];
		String output = args[1];
		
		test.createOutput(output);
		if(test.getStructure() == "Trie")
			dataStructure = new Trie();
		else
			dataStructure = new BST();
			
		test.creatDictionary();
		test.checkInput(input);
		//test.printOuput();
	}
	
	public void printOutput(String word) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))){
			bw.write(word);
			bw.newLine();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void printOutput(String[] suggestions) {
		// TODO Auto-generated method stub
		
	}

	public void checkInput(String input) {
		String thisLine = null;
		boolean findWord = true;
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))){
			try(BufferedReader br = new BufferedReader(new FileReader("input.txt")))
			{	         
				while ((thisLine = br.readLine()) != null) {
					//dataStructure.find(thisLine)
					if(findWord) {
						bw.write(thisLine);
						bw.newLine();
					}else
						printOutput(dataStructure.suggest(thisLine));
					//System.out.println(thisLine);
				}       
			} catch(Exception e) {
				e.printStackTrace();
			}

			
		} catch(Exception e) {
			e.printStackTrace();
		}
				
		
		
	}

	public String getStructure() {
		String DefaultStr = "Trie";
		String dst;
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("properties.txt");
			prop.load(fis);
			dst = prop.getProperty("Storage");

		} catch (Exception e1) {
			dst = DefaultStr;
		
		}
		return dst;
		
	}

	public void createOutput(String output) throws IOException {
		File outputFile = new File(output);
		
		if(outputFile.createNewFile())
			System.out.println("Create new output file successfullly.\n");
		else
			System.out.println("The output file is already exisit.\n");
		
	}
	
	public void creatDictionary() {
		String thisLine = null;
		try( BufferedReader br = new BufferedReader(new FileReader("english.0")))
		{
			
	        // open input stream input.txt for reading purpose.    
	        while ((thisLine = br.readLine()) != null) {
	        	dataStructure.insert(thisLine);
	            //System.out.println(thisLine);
	        }       
	     } catch(Exception e) {
	        e.printStackTrace();
	     }
		
	}
	

}
