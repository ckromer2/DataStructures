//Author: Cobey Kromer
//Date: 11/29/2021
///Description: Hash Table 2 Class
public class Hash2 implements ListInterface
{

	//data holders
	long refChanges;
	long keyCompare;
	
	//hashtable
	List3[] hashTable; 
	
	
	//contructor
	public Hash2()
	{
		//initialize fields
		refChanges = 0;
		keyCompare = 0; 
		hashTable = new List3[256]; 
		for(int i = 0; i < hashTable.length; i++)
		{
			hashTable[i] = new List3(); 
		}
	}
	//adds a word to the hash map
	@Override
	public void add(String word)
	{
		//hash the string
		int hash = stringToHash(word);
		
		//add the string
		hashTable[hash].add(word);
	}

	
	//hash function
	public int stringToHash(String word)
	{
		//return the value of the first char 
		return word.charAt(0); 
	}
		

	//getter and setters
	@Override
	public long getKeyCompare()
	{
		for(int i = 0; i < hashTable.length; i++)
		{
			if(hashTable[i].list != null)
				keyCompare += hashTable[i].getKeyCompare(); 
		}
		return keyCompare;
	}

	@Override
	public long getRefChanges()
	{
		for(int i = 0; i < hashTable.length; i++)
		{
			if(hashTable[i].list != null)
				refChanges += hashTable[i].getRefChanges(); 
		}
		return refChanges;
	}

	@Override
	public int getDistinctWords()
	{
		//int to record count
		int count = 0; 
		//iterate through the hash table
		for(int i = 0; i < hashTable.length; i++)
		{
			//set a pointer to the start of that specific linked list
			LLNode list = hashTable[i].list; 
			
			while(list != null)
			{
				//increment count
				count++; 
				
				//set list to the next link
				list = list.getNext(); 
				
			}
		}
		return count; 
	}

	@Override
	public int getTotalWords()
	{
		//int to record count
		int count = 0; 
		//iterate through the hash table
		for(int i = 0; i < hashTable.length; i++)
		{
			//set a pointer to the start of that specific linked list
			LLNode list = hashTable[i].list; 
			
			while(list != null)
			{
				//increment count
				count = count + list.getCount() ; 
				
				//set list to the next link
				list = list.getNext(); 
				
			}
		}
		return count; 
	}

	//gets the length of each collision list then prints it out
	public void getLength()
	{
		for(int i = 0; i < 256; i++)
		{
			LLNode x = hashTable[i].list; 
			int count = 0; 
			while(x != null)
			{
				count++;
				x = x.getNext(); 
			}
			System.out.println(count); 
		}
	}
}
