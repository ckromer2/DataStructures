//Author: Cobey Kromer
//Date: 10/04/2021
///Description: Binary Search Tree Class
public class BinarySearchTree implements ListInterface
{
	//reference to root of the tree
	BSTNode root;
	
	//data holders
	long refChanges;
	long keyCompare;
	int distinctWords; 
	int totalWords; 
	int maxHeight; 
	
	//constructor
	public BinarySearchTree()
	{
		//instantiate fields
		root = null; 
		refChanges = 0; 
		keyCompare = 0; 
		distinctWords = 0; 
		totalWords = 0; 
		maxHeight = -1; 
	}
	
	//adds a word to the binary search tree
	@Override
	public void add(String word)
	{
		//node reference that starts at root
		BSTNode location = root; 
		
		//node reference that starts one behind location
		BSTNode previous = null; 
		
		while(location != null)
		{
			//make an integer that is zero if they are equal and greater than zero if the word we are adding is to the right of the nodeRef
			int compResult = location.getWord().compareTo(word);
			
			//increment keyCompare
			keyCompare++;  
			
			//we have found the word and thus increment the count
			if(compResult == 0)
			{
				location.setCount(location.getCount() + 1);
				return; 
			}
			//we go right because the root is less than the node we are looking to add
			if(compResult < 0)
			{
				previous = location; 
				location = location.right; 
			}
			//we go left because the root is greater than the node we are looking to add
			else
			{
				previous = location; 
				location = location.left; 
			}
			
		}
		
		//create node to add
		BSTNode add = new BSTNode(word); 
		//if we are still at the root node, make a one node tree
		if(previous == null)
		{
			root = add; 
			refChanges++; 
			return;
		}
		//make an integer that is zero if they are equal and greater than zero if the word we are adding is to the right of the previous node
		int compResult = previous.getWord().compareTo(word);
		//increment keyCOmpare
		keyCompare++; 
		//if the previous word is less than the word to add, then we add the word to the right
		if(compResult < 0)
		{
			previous.right = add; 
			refChanges++;
		}
		//if the previous word is greater than the word to add, then we add the word to the left
		else
		{
			previous.left = add; 
			refChanges++; 
		}
	}
	
	//this method traverses the tree in recursive fashion
	//this method is what does the work in calculating distinct words and total words and height
	public void traverse(BSTNode node, int h)
	{
		//update maxHeight variable
		if(h > maxHeight)
			maxHeight = h; 
		
		//if null then return
		if(node == null)
			return;
		
		//do all the nodes to the left of this node
		traverse(node.getLeft(), h + 1);
		
		//collect the values
		totalWords += node.getCount(); 
		distinctWords++; 
		 
		//do all the nodes to the right of this node
		traverse(node.getRight(), h + 1);
		
	}
	
	//getter and setters
	@Override
	public long getKeyCompare()
	{		
		return keyCompare;
	}

	@Override
	public long getRefChanges()
	{
		return refChanges;
	}

	@Override
	public int getDistinctWords()
	{
		//need to traverse the list to get the statistics and we start height at -1
		traverse(root, -1);
		return distinctWords;  
		
	}

	@Override
	public int getTotalWords()
	{
		//no need to run traverse as it only needs ran onceand collected all its data in global variables

		return totalWords; 
	}
	
	public int getHeight()
	{
		//no need to run traverse as it only needs ran once and collected all its data in global variables

		return maxHeight; 
	}
	
}
