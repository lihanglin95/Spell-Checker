package spellChecker;

public class Trie implements Storage {
	final int ALPHABET_SIZE = 27;
	final int SUGGESTIONS_NUMBER = 3;
	String[] res = new String[SUGGESTIONS_NUMBER];
	int word_count = 0;
	int minLevelDist;

	TrieNode root;

	/*
	Constructor
	 */
	public Trie() {
		root = new TrieNode();
	}

	/*
	This is inner class Trie Node
	 */
	class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
		boolean is_word;
		
		/*
		Constructor
		 */
		public TrieNode() {
			is_word = false;
			
			for(int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null;			
		}
	}

	/**
	 * This is the method to insert all the words into the trie
	 * @param word This is the word from dictionary 'english.0'
	 */
	public void insert(String word) {
		int index;
		TrieNode Tnode = root;
		
		for(int i = 0; i < word.length(); i++) {
			index = word.charAt(i) - 'a';

			if(index == -58)
				index = 26;

			if(Tnode.children[index] == null)
				Tnode.children[index] = new TrieNode();
			
			Tnode = Tnode.children[index];
		}
		
		Tnode.is_word = true;		
	}

	/**
	 * This method is to check if the word exist in the trie
	 * @param word This is the word from input file
	 * @return This returns true if it exist, false otherwise
	 */
	public boolean find(String word) {
		int index;
		TrieNode Tnode = root;
		
		for(int i = 0; i < word.length(); i++) {
			index = word.charAt(i) - 'a';

			if(index == -58)
				index = 26;
			
			if(Tnode.children[index] == null)
				return false;
			
			Tnode = Tnode.children[index];
		}
			
		return (Tnode != null && Tnode.is_word);
	}

	/**
	 * This method is to find up to three suggestions of the misspelling word
	 * @param word This is the misspelling word
	 * @return This returns an array of String of suggested words
	 */
	public String[] suggest(String word) {
		minLevelDist = Integer.MAX_VALUE;
		int size = word.length();
		int[] currentRow = new int[size + 1];

		for(int i = 0; i <= size; i++ ){
			currentRow[i] = i;
		}

		for(int i = 0; i < ALPHABET_SIZE - 1; i++){
			if(root.children[i] != null){
				if(i == 26){
					suggest(root.children[i], word, '\'', "", currentRow);
				}else {
					suggest(root.children[i], word, (char) (i + 'a'), "", currentRow);
				}
			}
		}
		return res;
	}

	/**
	 * This method is a recursive helper to traverses theTrie in search of the minimum Levenshtein Distance.
	 * @param node This is the current TrieNode
	 * @param word This is the misspelling word
	 * @param letter This is the current letter of the current word
	 * @param prefix This is the current word for the current node
	 * @param previousRow This is the row in the Levenshtein Distance
	 */
	private void suggest(TrieNode node, String word, char letter, String prefix, int[] previousRow){
		int size = previousRow.length;
		int[] currentRow = new int[size];
		currentRow[0] = previousRow[0] + 1;

		int distance = currentRow[0];
		int insertCost, deleteCost, replaceCost;

		for(int i = 1; i < size; i++){
			insertCost = currentRow[i - 1] + 1;
			deleteCost = previousRow[i] +1;

			if(word.charAt(i - 1) == letter){
				replaceCost = previousRow[i - 1];
			} else{
				replaceCost = previousRow[i - 1] + 1;
			}

			currentRow[i] = Math.min(Math.min(insertCost, deleteCost), replaceCost);

			if(currentRow[i] < distance){
				distance = currentRow[i];
			}
		}

		if(currentRow[size - 1] == minLevelDist && node.is_word){
			if(word_count < SUGGESTIONS_NUMBER){
				res[word_count++] = prefix + letter;
			}
		}

		if(currentRow[size - 1] < minLevelDist && node.is_word){
			minLevelDist = currentRow[size - 1];
			word_count = 0;
			res = new String[SUGGESTIONS_NUMBER];
			res[word_count++] = prefix + letter;
		}

		if(distance < minLevelDist){
			for(int i = 0; i < ALPHABET_SIZE; i++){
				if(node.children[i] != null){
					if(i == 26){
						suggest(node.children[i], word, '\'', prefix + letter, currentRow);
					}else {
						suggest(node.children[i], word, (char) (i + 'a'), prefix + letter, currentRow);
					}
				}
			}
		}

	}


}
