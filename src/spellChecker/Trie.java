package spellChecker;

public class Trie implements Storage {
	final int ALPHABET_SIZE = 27;
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
	 *
	 * @param word
	 * @return
	 */
	public String[] suggest(String word) {
		// TODO Auto-generated method stub
		return null;
	}

}
