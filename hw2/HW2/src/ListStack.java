import java.util.EmptyStackException;

public class ListStack {
	protected Node top;
	protected int size;
	protected int above;
	
	public ListStack() {//constructor
		top = null;
		size = 0;
		above = 0;
	}
	
	public int size() {return size;}//return size
	public int above() {return above;}//return above
	
	public boolean isEmpty() {return (size == 0);}//check whether empty or not
	
	public void push(int num) {//put new item as the top of the stack
		top = new Node(num, top);//set new item as the top
		size++;//size increases
	}
	
	public int top() throws EmptyStackException {//return element of top
		if(isEmpty()) throw new EmptyStackException();
		return top.getElement();
	}
	
	public int pop() throws EmptyStackException {//delete the top element of the stack, return deleted item
		if(isEmpty()) throw new EmptyStackException();//error handling
		int item = top.getElement();//store the item
		top=top.getNext();//move top
		size--;//size decreases
		return item;//return the popped item
	}
	
	public void makeStack(int totalnum) {//make stack
		for(int i=1;i<=totalnum;totalnum--) {//make stack with sequential numbers
			this.push(totalnum);
		}
	}
	
	public void moveStack(int selectnum) {//move one element to the top
		int totalnum = size;
		ListStack store = new ListStack();
		boolean find = false;
		int seek = 0;
		
		while(!find) {//until we find the element
			if(seek>=totalnum) {//if there's no element in the stack
				break;//break
			}
			else if (top.getElement() != selectnum) {//if the element is not the selected one
				store.push(top.getElement());//move it to another stack
				seek++;//seek increases
				this.pop();//from original stack, remove it
			}
			else if (top.getElement() == selectnum) {//if the element is the selected one
				pop();//pop the element
				Node q = store.top;
				for(int i=1;i<=seek;i++) {//from the another stack(store), move element by element
					this.push(q.getElement());
					store.pop();
					q=q.next;
				}
				this.push(selectnum);//put the selected element on the top of the stack
				above=seek;
				
				find=true;//find is true 
			}
		}
	}
}
