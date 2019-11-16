package spellChecker;

public class Trie implements Storage {
	final int ALPHABET_SIZE = 27;
	TrieNode root;
	
	public Trie() {
		root = null;
	}
	
	class TrieNode {
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
		boolean is_word;
		
		public TrieNode() {
			is_word = false;
			
			for(int i = 0; i < ALPHABET_SIZE; i++)
				children[i] = null;			
		}
	}

	public void insert(String word) {
		int index;
		TrieNode Tnode = root;
		
		for(int i = 0; i < word.length(); i++) {
			index = word.charAt(i) - 'a';
			
			if(Tnode.children[index] == null)
				Tnode.children[index] = new TrieNode();
			
			Tnode = Tnode.children[index];
		}
		
		Tnode.is_word = true;		
	}

	public boolean find(String word) {
		int index;
		TrieNode Tnode = root;
		
		for(int i = 0; i < word.length(); i++) {
			index = word.charAt(i) - 'a';
			
			if(Tnode.children[index] == null)
				return false;
			
			Tnode = Tnode.children[index];
		}
			
		return (Tnode != null && Tnode.is_word);
	}

	public String[] suggest(String word) {
		// TODO Auto-generated method stub
		return null;
	}

}
