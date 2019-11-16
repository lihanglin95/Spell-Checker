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

	/**
	 * This is the main method
	 * @param args This is the arguments of this program
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		CS245A1 test = new CS245A1();
		String input = args[0];
		String output = args[1];
		
		test.createOutput(output);
		if(test.getStructure() == "Trie")
			dataStructure = new Trie();
		else
			dataStructure = new BST();
			
		test.createDictionary();
		test.checkInput(input, output);
		//test.printOutput();
	}
	
	public void printOutput(String[] suggestions) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is to check if the word is in the dictionary
	 * write the word into the output file if it exist
	 * write suggestion words if it does not
	 * @param input This is the name of the input file
	 * @param output This is the name of the output file
	 */
	public void checkInput(String input, String output) {
		String thisLine = null;
		//boolean findWord = true;
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(output))){
			try(BufferedReader br = new BufferedReader(new FileReader(input)))
			{	         
				while ((thisLine = br.readLine()) != null) {
					//dataStructure.find(thisLine)
					if(dataStructure.find(thisLine.toLowerCase())) {
						bw.write(thisLine);
						bw.newLine();
					}else
						//printOutput(dataStructure.suggest(thisLine.toLowerCase()));
						bw.write("not found\n");
					//System.out.println(thisLine);
				}       
			} catch(Exception e) {
				e.printStackTrace();
			}

			
		} catch(Exception e) {
			e.printStackTrace();
		}
				
		
		
	}

	/**
	 * This method is to set the data structure to use
	 * @return This returns the requirement of data structure
	 * 			return default data structure if there is no properties file
	 */
	public String getStructure() {
		String DefaultDS = "Trie";
		String dst;
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("properties.txt");
			prop.load(fis);
			dst = prop.getProperty("Storage");

		} catch (Exception e1) {
			dst = DefaultDS;
		
		}
		return dst;
		
	}

	/**
	 * This method is to create an output file
	 * @param output This is the name of the output file
	 * @throws IOException
	 */
	public void createOutput(String output) throws IOException {
		File outputFile = new File(output);
		
		if(outputFile.createNewFile())
			System.out.println("Create new output file successfullly.\n");
		else
			System.out.println("The output file is already exisit.\n");
		
	}

	/*
	This method is to read the dictionary and insert words to the tree
	 */
	public void createDictionary() {
		String thisLine = null;
		try( BufferedReader br = new BufferedReader(new FileReader("english.0")))
		{
			
	        // open input stream input.txt for reading purpose.    
	        while ((thisLine = br.readLine()) != null) {
	        	dataStructure.insert(thisLine.toLowerCase());
	            //System.out.println(thisLine);
	        }       
	     } catch(Exception e) {
	        e.printStackTrace();
	     }
		
	}
	

}
