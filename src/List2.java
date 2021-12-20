//Author: Cobey Kromer
//Date: 11/29/2021
///Description: List 2 Class (Sorted List)
public class List2 extends BaseList
{

	List2()
	{
		super(); 
	}
	
	//adds a word to the list
	@Override
	public void add(String word)
	{
		//current and previous node
		LLNode current = list;
		LLNode previous = null; 
		
		//first see if the word is already on the list
		while(current != null)
		{
			//make an integer that is zero if they are equal and greater than zero if the word is where we need to enter the word
			int compResult = current.getWord().compareTo(word);
			
			//increment keyCompare
			keyCompare++; 
			
			//if the current point in the list is greater than the word to find then break out of the loop
			if(compResult < 0)
			{
				break; 
			}
 
			//if current equals word, increment count and stop
			if(compResult == 0)
			{
				//increment the count of the word in the node
				current.setCount(current.getCount() + 1);
				
				//we have done all we need to do so return out of the method
				return; 
			}
			
			//update the current and previous references
			previous = current; 
			current = current.getNext(); 
		}

		//then if it is not then add the node in the correct position
		LLNode newNode = new LLNode(word); 
		newNode.setLink(current);
		//increment refChanges due to setLink changing the reference of the added node's link
		refChanges++;  
		
		//if you are adding at the start of the list
		if(previous == null)
		{
			//set the front of the list to the newNode
			list = newNode;
			//increment refChanges because we are changing the reference called list
			refChanges++; 
		}
		
		//else you are adding somewhere inside the list
		else
		{
			//set the previous node's link to the newNode
			previous.setLink(newNode);
			//increment refChanges because we are changing the reference of the previous link's setlink method
			refChanges++; 
		}
		
		
		
	}
	

}
