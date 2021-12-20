//Author: Cobey Kromer
//Date: 11/29/2021
//Description: Node Class for the SkipList
public class SLNode
{
		//word that is held
		protected String word;
		
		//number of times this word appears
		protected int count; 
		
		//link to the next nodes around this one
		protected SLNode right;
		protected SLNode left;
		protected SLNode up;
		protected SLNode down;

		//boolean flag for if something is at the end of the list
		protected boolean isSentinel; 

		//constructor
		public SLNode(String word, boolean isBottom, boolean isSentinel)
		{
			//only need to keep track of the count on the bottom of the skip list
			//and it is not a sentinel
			if(isBottom && !isSentinel)
			{
				count = 1; 
			}
			else
				count = -1; 
			
			this.word = word;
			right = null; 
			left = null;
			up = null; 
			down = null; 
			this.isSentinel = isSentinel; 
		}

		
		//getters and setter
		public SLNode getRight()
		{
			return right;
		}

		public void setRight(SLNode right)
		{
			this.right = right;
		}

		public SLNode getLeft()
		{
			return left;
		}

		public void setLeft(SLNode left)
		{
			this.left = left;
		}

		public SLNode getUp()
		{
			return up;
		}

		public void setUp(SLNode up)
		{
			this.up = up;
		}

		public SLNode getDown()
		{
			return down;
		}

		public void setDown(SLNode down)
		{
			this.down = down;
		}

		public boolean isSentinel()
		{
			return isSentinel;
		}

		public void setSentinel(boolean isSentinel)
		{
			this.isSentinel = isSentinel;
		}

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

		

	

}
