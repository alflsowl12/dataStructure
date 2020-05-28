
public class Node {//class for making singly linked list
	protected int element;
	protected Node next;
	public Node(int item, Node node) {
		element = item;
		next = node;
	}
	public int getElement() {return element;}//get Element
	public Node getNext() {return next;}//get Next
	public void setElement(int newItem) {element = newItem;}//set element as newItem
	public void setNext(Node newNext) {next=newNext;}//set next as newNext
}
