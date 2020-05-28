package dshw1;

public class ListItem {
	protected int element;
	protected ListItem next;
	
	public ListItem(int i) {
		element = i;
		next = null;
	}
	
	public int getElement() {return element;}
	public ListItem getNext() {return next;}
	public void setElement(int i) {element = i;}
	public void setNext(ListItem node) {next = node;}
}
