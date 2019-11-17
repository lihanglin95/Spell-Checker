package spellChecker;

public class Trie implements Storage {
	final int ALPHABET_SIZE = 27;
	final int SUGGESTIONS_NUMBER = 3;
	String[] res = new String[SUGGESTIONS_NUMBER];
	final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
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

		@Override
		public String toString() {
			String rep = "";
			for (int i = 0; i < ALPHABET_SIZE - 1; i++) {
				if (children[i] != null) {
					rep += ALPHABET.charAt(i);
				}
			}
			return rep;
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
	 *
	 * @param word
	 * @return
	 */
	public String[] suggest(String word) {
			return suggest(word, root);
	}

	private String[] suggest(String word, TrieNode node) {
		int minLevDis = Integer.MAX_VALUE;
		String prefix = "";
		int[] currentRow = new int[word.length() + 1];

		for(int i = 0; i <= word.length(); i++)
			currentRow[i] = i;

		for(int i = 0; i <ALPHABET_SIZE - 1; i++) {
			findSuggestions(node, word, i + 'a', prefix + (char) (i + 'a'), res, minLevDis, currentRow, 0);
		}

		return res;
	}

	private void findSuggestions(TrieNode node, String word, int letter, String prefix, String[] res, int minLevDis, int[] previousRow, int word_count) {
		int[] currentRow = new int[previousRow.length];
		currentRow[0] = previousRow[0] + 1;

		int distance = currentRow[0];
		int insertCost;
		int deleteCost;
		int replaceCost;

		for(int i = 1; i < currentRow.length; i++){
			insertCost = currentRow[i - 1] + 1;
			deleteCost = previousRow[i] + 1;

			if(word.charAt(i - 1) == (char)(letter))
				replaceCost = previousRow[i - 1];
			else
				replaceCost = previousRow[i - 1] + 1;

			currentRow[i] = Math.min(Math.min(insertCost, deleteCost), replaceCost);

			if(currentRow[i] < distance)
				distance = currentRow[i];
		}

		//if(res == null)


		if(currentRow[currentRow.length - 1] == minLevDis && node.is_word)
			if(word_count < 3)
				res[word_count++] = prefix;

		if(currentRow[currentRow.length - 1] < minLevDis && node.is_word) {
			minLevDis = currentRow[currentRow.length - 1];
//			res = new String[SUGGESTIONS_NUMBER];
			word_count = 0;

			res[word_count++] = prefix;
		}

		if(distance < minLevDis)
			for(int i = 0; i < ALPHABET_SIZE; i++)
				if(node.children[i] != null)
					findSuggestions(node.children[i], word, i + 'a', prefix, res, minLevDis, currentRow, word_count);

	}

}
