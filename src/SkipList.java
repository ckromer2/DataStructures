//Author: Cobey Kromer
//Date: 11/29/2021
//Description: SkipList Class 
import java.util.Random;
public class SkipList implements ListInterface
{
	//head and tail of the top of the list
	protected SLNode head;
	protected SLNode tail; 
	
	//long variable to keep track of key information
	protected long refChanges;
	protected long keyCompare;
	protected int height; 
	protected int numberOfNodes; 
	
	
	//random object
	protected Random rand; 
	
	SkipList()
	{
		//instantiate data variables
		refChanges = 0;
		keyCompare = 0; 
		height = 1; 
		numberOfNodes = 0; 
		
		//create two nodes to be ends od the first layer that are null(no string) and true(isBottom) and true(isSentinel)
		SLNode p1 = new SLNode(null, true, true);
		SLNode p2 = new SLNode(null, true, true);
		
		//increment number of nodes
		numberOfNodes = numberOfNodes + 2; 
		
		//set the right and left of the sentinel nodes
		p1.setRight(p2);
	    p2.setLeft(p1); 
	    
	    //increment reference changes
	    refChanges = refChanges + 2; 
	    
	    //instantiate head and tail
	    head = p1;
	    tail = p2; 
	    //instantiate random variable
	    rand = new Random(); 
	    
	}

	//method to insert a word on to the list
	public void add(String word)
	{
		//create a pointer to where we currently are in the list
		SLNode current = search(word); 
		
		//if statement for if the word we are trying to add is already on the list
		//increment keyCompare as we are comparing to the word that is to be added
		keyCompare++; 
		if(current.getWord() != null && current.getWord().compareTo(word) == 0)
		{
			//increment count and return
			current.setCount(current.getCount() + 1);
			return; 
		}
		//create a new node to be added, bottom = true, sentinel = false
		SLNode newNode = new SLNode(word, true, false); 
		
		//increment number of nodes
		numberOfNodes++; 
		
		//set newNode's links
		newNode.setLeft(current);
		newNode.setRight(current.getRight());
		current.getRight().setLeft(newNode);
		current.setRight(newNode); 
		//increment reference changes
		refChanges = refChanges + 4; 
		
		//make a tower of the entry
		//make an integer to signify which level of te structure we are on
		int i = 1; 
		
		//while the random number is less than .5 we keep adding nodes 
		while(rand.nextDouble() < 0.5)
		{
			
			//check to see if we need to add a layer of sentinel nodes
			if(i >= height)
			{
				//create two nodes to be ends od the first layer that are null(no string) and true(isBottom) and true(isSentinel)
				SLNode p1 = new SLNode(null, false, true);
				SLNode p2 = new SLNode(null, false, true);
				
				//increment the number of nodes
				numberOfNodes = numberOfNodes + 2;
				
				//set the right, left, and down of the sentinel nodes
				p1.setRight(p2);
				p2.setLeft(p1);
				p1.setDown(head);
			    p2.setDown(tail);
			    
			    //set the up of the sentinel nodes on the old top row
			    head.setUp(p1);
			    tail.setUp(p2);
			    
			    //set the head and tail to these new sentinel nodes
			    head = p1;
			    tail = p2; 
			    
			    //increase reference changes
			    refChanges = refChanges + 8; 
			    
			    //increase the height
			    height++; 
			}

			//find the node on the left that has an upward node
			//need this to connect the node we are adding above current
			while(current.getUp() == null)
	        {
				current = current.getLeft();
	        }
			
			//make current refer to the node above it
			current = current.getUp(); 
			
			//create a new node that is to be added above newNode
			//the word is word, this node is not on the bottom, and it is not a sentinel node
			//no need for a count thus we made the second argument(isBottom) false so that the count become -1
			SLNode newAboveNode = new SLNode(word, false, false); 
			
			//increment number of nodes
			numberOfNodes++; 
			
			//link newAboveNode to its neighbors
			newAboveNode.setLeft(current); 
			newAboveNode.setRight(current.getRight());
			newAboveNode.setDown(newNode); 
			
			//link current to its neighbors
			current.getRight().setLeft(newAboveNode); 
			current.setRight(newAboveNode); 
			newNode.setUp(newAboveNode); 
			
			//increment refChanges
			refChanges = refChanges + 6; 

			//set newNode to the newAboveNode
			newNode = newAboveNode; 

			//increment i
			i++; 
		}
		 
	}
	
	//method to search for a word in the list
	public SLNode search(String word)
	{
		//pointer to where we are at in this list
		SLNode current = head; 
		
		while(true)
		{
			
			//increment keycompare
			keyCompare++; 
			//while not a sentinel and the word we are trying to add is not greater then the right node
			//once we get through this loop we are at the right spot in the layer now we just have to move down
			while((!(current.getRight().isSentinel())) && current.getRight().getWord().compareTo(word) <= 0)
			{

				current = current.getRight(); 
				
				//increment KeyCompare due to the second part of the while statement
				keyCompare++; 
			}
			
			//downward movement
			//if we can move down then do so
			if(current.getDown() != null)
			{
				current = current.getDown();
			}
			//else we have reached the bottom of the list
			else
			{
				break; 
			}

		}
		
		//current.word <= word
		return current; 
		
	}

	
	
	
	//getter methods 
	public long getRefChanges()
	{
		// How many reference changes did we do (how much structural work)?
		//
		return refChanges;
	}

	
	public long getKeyCompare()
	{
		// How many key comparisons (how much work was done looking for things on the
		// list)?
		//
		return keyCompare;
	}
	
	public int getHeight()
	{
		//return the height
		return height;
	}
	
	public int getNumberOfNodes()
	{
		return numberOfNodes;
	}

	public void setNumberOfNodes(int numberOfNodes)
	{
		this.numberOfNodes = numberOfNodes;
	}

	
	public int getDistinctWords()
	{
		// How many nodes are there on the list? Each corresponds to a unique word
		//
		int count = 0; // Alternate coding:
		SLNode p = head; //
		while(p.getDown() != null)
		{
			p = p.getDown(); 
		}
		p = p.getRight(); 
		while (p.getRight() != null) // for (LLNode p = list; p != null; p = p.getNext()) count++;
		{ 
			// return count;
			count++; //
			p = p.getRight(); //
		} //
		return count; //
	}

	
	public int getTotalWords()
	{
		// How many TOTAL words? That's the sum of the counts in each node.
		//
		int count = 0;
		SLNode p = head; //
		while(p.getDown() != null)
		{
			p = p.getDown(); 
		}
		p = p.getRight(); 
		while (p.getRight() != null)
		{
			count += p.getCount();
			p = p.getRight();
		}
		return count;
	}

	
	
	
}

			

