package spellChecker;

public class BST implements Storage {
	BSTNode root;
	
	/*
	This is the constructor of class BST
	 */
	public BST() {
		root = null;
	}
	
	/*
	This inner class defines Node
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

		/*
		 * This is the constructor of inner class Node
		 */
		public BSTNode(String testString) {
			left = null;
			right = null;
			word = testString;
			height = 0;
		}
	}

	/*
	This insert method calls private insert method
	 */
	public void insert(String testString) {
		root = insert(testString, root);	
	}

	/*
	This find method calls private find method
	 */
	public boolean find(String testString) {
		return find(testString, root);
	}

	/**
	 * This method is to check height of the node
	 * @param node This is the node that needs to check height
	 * @return This returns the height
	 */
	private int height(BSTNode node) {
		return node == null ? -1 : node.height;
	}

	/**
	 * This method is to find larger number between a and b
	 * @param a This is the first number
	 * @param b THis is the second number
	 * @return This returns the larger number
	 */
	private int max(int a, int b) {
		return a>b ? a : b;
	}
	
	/**
	 * This method is to check if the target testString is in the tree
	 * @param testString This is the String
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
	 * @param testString This is the String
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

	/**
	 * This method is to rotate binary search tree node with left child
	 * @param node This is the node that needs to rotate
	 * @return This returns the node after rotation
	 */
	private BSTNode rotateWithLeftChild(BSTNode node) {
		BSTNode temp = node.left;
		node.left = temp.right;
		temp.right = node;
		node.height = max(height(node.left), height(node.right)) + 1;
		temp.height = max(height(temp.left), node.height) + 1;
		return temp;
	}

	/**
	 * This method is to rotate binary search tree node with right child
	 * @param node This is the node that needs to rotate
	 * @return This returns the node after rotation
	 */
	private BSTNode rotateWithRightChild(BSTNode node) {
		BSTNode temp = node.right;
		node.right = temp.left;
		temp.left = node;
		node.height = max(height(node.left), height(node.right)) + 1;
		temp.height = max(height(temp.right), node.height) + 1;
		return temp;
	}

	/**
	 * This method is to double rotate binary search node : first left child
	 * with its right child
	 * @param node This is the node that needs to double rotate
	 * @return This returns new left child ofter rotation
	 */
	private BSTNode doubleWithLeftChild(BSTNode node) {
		node.left = rotateWithRightChild(node.left);
		return rotateWithLeftChild(node);
	}

	/**
	 * This method is to double rotate binary search node : first right child
	 *  with its left child
	 * @param node This is the node that needs to double rotate
	 * @return This returns new right child ofter rotation
	 */
	private BSTNode doubleWithRightChild(BSTNode node) {
		node.right = rotateWithLeftChild(node.right);
		return rotateWithRightChild(node);
	}

	public String[] suggest(String word) {
		// TODO Auto-generated method stub
		return null;
	}

}

