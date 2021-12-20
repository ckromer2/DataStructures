//Author: Cobey Kromer
//Date: 11/29/2021
//Description: List 4 Class (Light handed adjustment list)
public class List4 extends BaseList
{

	List4()
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
		LLNode previous2 = null; 
		
		//iterate through the list
		while(current != null)
		{
			
			//increment keyCompare because of the upcoming if statement
			keyCompare++; 
			
			//if the current word equals the word to add
			if(current.getWord().equals(word))
			{
				//increment the count in the node
				current.setCount(current.getCount() + 1);
	
				//if previous2 is null we are trying to switch the first and second nodes so it is a special case
				if(previous2 == null && previous != null)
				{
					//set previous' link to current's link
					previous.setLink(current.getNext());
					refChanges++; 
					
					//set current's link to 
					current.setLink(previous);
					refChanges++; 
					
					//set the head of the list to current
					list = current;
					refChanges++; 
				}
				//if previous is null then we are trying to switch the front node forward which is impossible
				//thus we do not ever have a case for it as there is nothing to do to it
				else if(previous != null)
				{
					//set previous' link to current's link
					previous.setLink(current.getNext());
					refChanges++; 
					
					//set current's link to 
					current.setLink(previous);
					refChanges++; 
					
					//set previous2's link to current 
					previous2.setLink(current);
					refChanges++;
					
				}
							
				//we have done all we need to do so return out of the method
				return; 
				
			}

			//update the current, previous and previous2 nodes
			previous2 = previous; 
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
