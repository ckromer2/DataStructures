//Author: Cobey Kromer
//Date: 11/29/2021
//Description: Node Class for the BinarySearchTree
public class BSTNode
{
	//word that is held
	String word;
	//number of times this word appears
	int count; 
	//link to the right node
	BSTNode right;
	//link to the left node
	BSTNode left; 
	
	//constructor
	public BSTNode(String word)
	{
		//instantiate the field variables
		this.word = word;
		right = null;
		left = null; 
		count = 1; 
	}

	//getter and setters
	public void setCount(int num)
	{
		this.count = num;
	}

	public int getCount()
	{
		return count;
	}
	
	public void setWord(String word)
	{
		this.word = word;
	}

	public String getWord()
	{
		return word;
	}

	public BSTNode getRight()
	{
		return right;
	}

	public void setRight(BSTNode right)
	{
		this.right = right;
	}

	public BSTNode getLeft()
	{
		return left;
	}

	public void setLeft(BSTNode left)
	{
		this.left = left;
	}

	
		
		
}
