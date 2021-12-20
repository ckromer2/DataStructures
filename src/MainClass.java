//Author: Cobey Kromer
//Date: 11/29/2021
//Description: Driver Class to test the different types of lists
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class MainClass
{
	//main method
	public static void main(String[] args) 
	{
		ListInterface[] Lists = new ListInterface[10]; // By creating the lists as
		 											  // an array, we can iterate
													  // with a subscript
		Lists[0] = new List1(); // Unsorted, insertions at beginning, no self-optimization
		Lists[1] = new List2(); // Sorted linked list
		Lists[2] = new List2a();//Modified SortedList
		Lists[3] = new List3(); // Unsorted, heavy-handed self-adjusting (moves repeated
		 						//word to the front of the list)
		Lists[4] = new List4(); // Unsorted, conservative self-adjusting (moves repeated
		 						//word one position towards front of list)
		Lists[5] = new SkipList(); //SkipList
		Lists[6] = new Hash1(); // Hash table
		Lists[7] = new Hash2(); // Hash Table
		Lists[8] = new Hash3(); //Hash Table
		Lists[9] = new BinarySearchTree(); // BinarySearchTree
		
		String[] ListNames = {" 1 Unsorted", " 2 Sorted", "2a Modified Sorted", " 3 Self-Adj (Front)",
				 " 4 Self-Adj (By One)", " 5 SkipList", " 6 Hash 1", " 7 Hash 2", " 8 Hash 3", " 9 BST"};
		
		//scanner object
		//initialize it to null so that we can use the object without the warning message
		//we can't initialize it with the file because that needs to be in a try catch block
		Scanner scnr = null; 
		
		//string for name of file
		String FileName;
		if (args.length > 0)
			FileName = args[0];
		else
			FileName = "Hamlet.txt";

		//double value to hold the runtime
		double runTime[] = new double[10];
		
		//int value to hold vocabulary
		int vocabulary[] = new int[10]; 
		
		//int value to hold total words
		int totalWords[] = new int[10];
		
		//long value to hold keyComp
		long keyComp[] = new long[10];
		
		//long value to hold refChgs
		long refChgs[] = new long[10];
		
		//int value to hold height
		int height[] = new int[10]; 
		
		//long value to hold the read and parse time
		long parseTime = 0; 
				
		//file
		File file = new File(FileName); 
		
		
		
		//iterate through the document 8 times
		for(int i = 0; i < 12; i++)
		{
			
			//start the clock
			long start = System.currentTimeMillis();
			
			//try catch block to make sure the file exists
			try
			{
				//scanner for file input
				scnr = new Scanner(file); 
			}
			catch(FileNotFoundException x)
			{	
				System.out.println(x.getMessage()); 
				System.exit(0); 
			}
			
			//if we have a list that we need to do additions to
			if(i > 1)
			{
				while(scnr.hasNext())
				{
					//make the String that is to be added
					String add = formatFixer(scnr.next());
					//if statement so that we do not add empty strings into the linked list
					if(add != "")
						Lists[i - 2].add(add);
				}
				
			}
			
			//if we are on the second pass we need to get a base time to run through and parse the list
			if(i < 1)
			{
				while(scnr.hasNext())
				{
					String trash = formatFixer(scnr.next()); 
				}

			}
			
			//close the scanner
			scnr.close();
			//end the clock then add it to the array
			long elapsed = System.currentTimeMillis() - start;
			//if i equals one then we need to record this time to subtract it from the others
			if(i == 1)
				parseTime = elapsed; 
			//if we are on the third pass then we are actually adding things to a list so we want to record the data
			if(i > 1)
			{
				runTime[i - 2] = (elapsed - parseTime) / 1000.0; 
				vocabulary[i - 2] = Lists[i - 2].getDistinctWords(); 
				totalWords[i - 2] = Lists[i - 2].getTotalWords();
				keyComp[i - 2] = Lists[i - 2].getKeyCompare(); 
				refChgs[i - 2] = Lists[i - 2].getRefChanges(); 
				if(i != 7 && i != 11)
					height[i - 2] = 1; 
				else if(i == 7)
					height[i - 2] = ((SkipList)Lists[i - 2]).getHeight(); 
				else
					height[i - 2] = ((BinarySearchTree)Lists[i - 2]).getHeight();  
				
			}
			
		}
		
		
		//print out the first line in the table
		System.out.printf("%2s %12s %13s %11s %12s %10s %12s %10s", "#", "List Name", "Run Time", "Vocabulary", "Total Words", "Key Comp", "Ref Chgs", "Height");
		//print out the line
		System.out.println("\n-- ----------------- --------  ----------  ----------- ------------ ------------ ----------"); 
		//print out the data
		for(int i = 1; i < 11; i++)
			System.out.printf("%-20s %-11.3f %-9d %-11d %-12d %-12d %s\n",  ListNames[i - 1], runTime[i - 1], vocabulary[i - 1], totalWords[i - 1], keyComp[i - 1], refChgs[i - 1], height[i -1 ]);

		
	}

	//formats the string to be lowercase and to get rid of punctuation
	private static String formatFixer(String input)
	{
		//set the string to lowercase
		input = input.toLowerCase();
		
		//get rid of all trailing and leading puctuation
		char front = input.charAt(0); 
		char back = input.charAt(input.length() - 1);
		
		//checks if the front char is in the string of  punctuation that needs to be trimmed off
		while("!@#$%^&*()_+-=[]\\{}|;':\"`~,./<>?".indexOf(front) != -1)
		{
			if(input.length() == 1)
				return  ""; 
			else
			{
				input = input.substring(1); 
				front = input.charAt(0); 
			}
		}
		
		//checks if the back char is in the string of  punctuation that needs to be trimmed off
		while("!@#$%^&*()_+-=[]\\{}|;':\"`~,./<>?".indexOf(back) != -1)
		{
			
				input = input.substring(0,input.length() - 1); 
				back = input.charAt(input.length() - 1);
			
		}
		
		return input; 
	}
}
