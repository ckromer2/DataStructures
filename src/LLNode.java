//Author: Cobey Kromer
//Date: 11/29/2021
//Description: Node Class used in Lists 1-4
public class LLNode
{
	//word that is held
	protected String word;
	//number of times this word appears
	protected int count; 
	//link to the next node
	protected LLNode link;

	//constructor
	public LLNode(String word)
	{
		this.word = word;
		link = null;
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

	public void setLink(LLNode link)
	{
		this.link = link;
	}

	public LLNode getNext()
	{
		return link;
	}
	
	

}
