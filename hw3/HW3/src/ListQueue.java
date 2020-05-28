import java.util.EmptyStackException;

public class ListQueue {
	protected Node front;//indicates the first one
	protected Node rear;//indicates the last one
	protected int size;//size
	public ListQueue() {//constructor
		front = null;
		rear = null;
		size = 0;
	}
	public int size(){return size;}//return size
	public boolean isEmpty() {return (size==0);}//check whether queue is empty or not
	public Node front() {return front;}//get the first item in the queue
	
	public void insert(int newItem)//insert new item
	{
		Node prear = rear;//copy rear to prear
		rear = new Node(newItem, null);//insert new item at last
		if (size == 0)//if it's the first item
			front = rear;//front and rear indicates the same one
		else//if not
			prear.setNext(rear);//rear becomes prear's next
		size++;//size increases
	}
	public void delete() throws EmptyStackException//delete element
	{
		if (size==0)//if there's nothing in it, error
			throw new EmptyStackException();
		
		front = front.getNext();//first in first out
		size--;
		if(size == 0) rear = null ; // Queue is now empty
	}
	public void addOne()//for every element in the queue, add 1
	{
		Node p = front;
		for(int i=0;i<size;i++)
		{
			p.setElement(p.getElement()+1);
			p=p.next;
		}
	}
	
	public void subractOne()//for every element in the queue, subtract 1
	{
		Node p = front;
		for(int i=0;i<size;i++)
		{
			if(p.getElement()>0)//only if the element is larger tha 0
			{
				p.setElement(p.getElement()-1);
				p=p.next;
			}
		}
	}
}
