
public class LinkedListQueue<AnyType> implements Queue<AnyType>
{	
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> 
	{
		private AnyType data;
		private Node next;
		
		public Node(AnyType data, Node<AnyType> next) 
		{
			this.data = data;
			this.next = next;
		}

		public void setNext(Node<AnyType> next) 
		{
			this.next = next;
		}
		
		public Node<AnyType> getNext() 
		{
			return next;
		}
		
		public AnyType getData() 
		{
			return data;
		}
	}
   
	private int size = 0;		//Nombre d'elements dans la file.
	private Node<AnyType> last;	//Dernier element de la liste
	private Node<AnyType> first;
	
	public LinkedListQueue() 
	{
		first = new Node<AnyType>(null, null);
		last = first;
		size = 0;
	}
	
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexité asymptotique: O(1)
	public AnyType peek()
	{
		if(this.empty()) return null;
		return first.getNext().getData();
	}
	
	//Retire l'element en tete de file
	//complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		if(this.empty()) throw new EmptyQueueException();
		first=first.getNext();
		size--;
	}
	
	//Ajoute un element a la fin de la file
	//complexité asymptotique: O(1)
	public void push(AnyType item)
	{		
		Node<AnyType> newLast = new Node<AnyType>(item,null);
		last.setNext(newLast);
		last = newLast;
		size ++;
	}  
}
