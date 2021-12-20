//Author: Cobey Kromer
//Date: 11/29/2021
///Description: List 1 Class (Unsorted List)
public class List1 extends BaseList
{
	List1()
	{
		super(); 
	}
	
	//adds a word to the list
	@Override
	public void add(String word)
	{
		//first we find if the word is already on the list
		//initialize the pointer node
		LLNode current = list;
		
		//first we find if the word is already on the list
		while(current != null)
		{
			//increment keyCompare because we did a comparison in the if statement
			keyCompare++;
			//if it is on the lust just increment that nodes count 
			if(current.getWord().equals(word))
			{
				current.setCount(current.getCount() + 1);
				return; 
			}
			
			//set current to the next node in the list
			current = current.getNext(); 
				
		}

		//else add a new node to the linked list and increment refChanges
		LLNode newNode = new LLNode(word); 
		newNode.setLink(list);
		//increment refChanges due to setLink changing the reference of the added node's link
		refChanges++; 
		list = newNode;
		//increment refChanges because we are changing the reference called list
		refChanges++; 

		
		
	}

}
