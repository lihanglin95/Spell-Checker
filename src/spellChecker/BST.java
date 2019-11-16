package spellChecker;

public class BST implements Storage {
	BSTNode root;
	
	/*
	 * This is the constructor of class BST
	 */
	public BST() {
		root = null;
	}
	
	/*
	 * This inner class defines Node
	 */
	class BSTNode{
		String word;
		BSTNode left;
		BSTNode right;
		int instance;
		int height;
		
		/*
		 * This is the constructor of inner class Node
		 */
		public BSTNode() {
			left = null;
			right = null;
			word = "";
			instance = 0;
			height = 0;
		}
		
		public BSTNode(String testString) {
			left = null;
			right = null;
			word = testString;
			height = 0;
		}
	}

	/*
	 * This insert method calls the other recursive insert method
	 */
	public void insert(String testString) {
		root = insert(testString, root);	
	}

	/*
	 * This find method calls the other recursive find method
	 */
	public boolean find(String testString) {
		return find(testString, root);
	}
	
	private int height(BSTNode node) {
		return node == null ? -1 : node.height;
	}

	private int max(int lhs, int rhs) {
		return lhs>rhs ? lhs : rhs;
	}
	/*
	 * This print method calls the other recursive print method
	 */
	public void print() {
		print(root);
	}
	
	/**
	 * This method is to check if there is the target testString in the tree
	 * @param testString This is the String passed by class Practice08test
	 * @param node This is the current node that is checking
	 * @return This returns true if the String is found
	 */
	private boolean find(String testString, BSTNode node) {
		if(node == null)
			return false;
		
		if(testString.compareTo(node.word) == 0)
			return true;
		
		if(testString.compareTo(node.word) < 0)
			return find(testString, node.left);
		else
			return find(testString, node.right);	
	}
	
	/**
	 * This method is to insert a String to the tree
	 * @param testString This is the String passed by class Practice08test
	 * @param node This is the current node that is checking
	 * @return This returns a node that is the root
	 */
	private BSTNode insert(String testString, BSTNode node) {
		if(node == null)
			return new BSTNode(testString);
		
		if(testString.compareTo(node.word) == 0) {
			node.instance++;
			return node;
		}
		
		if(testString.compareTo(node.word) < 0) {
			node.left = insert(testString, node.left);
			if(height(node.left) - height(node.right) == 2)
				if(testString.compareTo(node.left.word) < 0)
					node = rotateWithLeftChild(node);
				else
					node = doubleWithLeftChild(node);
		}
		else {
			node.right = insert(testString, node.right);
			if(height(node.right) - height(node.left) == 2)
				if(testString.compareTo(node.right.word) > 0)
					node = rotateWithRightChild(node);
				else
					node = doubleWithRightChild(node);
		}
		node.height = max(height(node.left), height(node.right)) + 1;
		return node;
			
	}
	
	private BSTNode rotateWithLeftChild(BSTNode node) {
		BSTNode temp = node.left;
		node.left = temp.right;
		temp.right = node;
		node.height = max(height(node.left), height(node.right)) + 1;
		temp.height = max(height(temp.left), node.height) + 1;
		return temp;
	}

	private BSTNode rotateWithRightChild(BSTNode node) {
		BSTNode temp = node.right;
		node.right = temp.left;
		temp.left = node;
		node.height = max(height(node.left), height(node.right)) + 1;
		temp.height = max(height(temp.right), node.height) + 1;
		return temp;
	}

	private BSTNode doubleWithLeftChild(BSTNode node) {
		node.left = rotateWithRightChild(node.left);
		return rotateWithLeftChild(node);
	}

	private BSTNode doubleWithRightChild(BSTNode node) {
		node.right = rotateWithLeftChild(node.right);
		return rotateWithRightChild(node);
	}

	
	/**
	 * This method is to print the tree in order
	 * @param node This is the current node that is checking
	 * @return This returns the current node that is printed
	 */
	private BSTNode print(BSTNode node) {
		if(node == null)
			return null;
		
		print(node.left);
		for (int i = node.instance; i >= 0; i--){
			System.out.println(node.word);
		}
        
		print(node.right);
		return node;
	}

	public String[] suggest(String word) {
		// TODO Auto-generated method stub
		return null;
	}

}

