//Author: Cobey Kromer
//Date: 11/29/2021
//Description: List 3 Class (Heavy handed adjustment list)
public class List3 extends BaseList
{

	List3()
	{
		super(); 
	}
	
	//adds a word to the list
	@Override
	public void add(String word)
	{
		//initialize the pointer nodes
		LLNode current = list;
		LLNode previous = null; 
		
		//iterate through the list
		while(current != null)
		{
			
			//increment keyCompare
			keyCompare++; 
			
			//if the current word equals the word to add
			if(current.getWord().equals(word))
			{
				//increment the count in the node
				current.setCount(current.getCount() + 1);
				
				//only do the rest of the code if the current node is not already the first node in the list
				if(previous != null)
				{
					//set the link of previous to the link of current so that we can remove this current node and stick in the front 
					previous.setLink(current.getNext());
					refChanges++; 
					
					//make the current node the front of the list
					current.setLink(list);
					refChanges++; 
					
					//set the head of the list to this newNode
					list = current; 
					refChanges++; 
				}
				
				//we have done all we need to do so return out of the method
				return; 
				
			}
			
			//update the current and previous references
			previous = current; 
			current = current.getNext(); 

		}
		
		//the word is  not on the list
		//so we make a new node and add it to the list in the starting position
		LLNode newNode = new LLNode(word); 
		newNode.setLink(list);
		refChanges++; 
		
		//change what the front of the list is
		list = newNode; 
		refChanges++; 
		
		
	}

}
