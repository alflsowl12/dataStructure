
public class Node {
	protected int element;
	protected Node next;
	public Node (int item, Node node)
	{
		element = item;
		next = node;
	}
	
	public int getElement() {return element;}//return element
	public Node getNext() {return next;}//return next
	public void setElement(int newItem) {element = newItem;}//set element to newItem
	public void setNext(Node newNext) {next = newNext;}//set next to newNext
}
