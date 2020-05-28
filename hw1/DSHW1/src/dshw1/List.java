package dshw1;

public class List {
	protected ListItem head;
	protected int size;
	protected ListItem index;

	public List() {
		head = null;
		size = 0;
	}
	
	public boolean IsEmpty() {return (Length() == 0);}
	public int Length() {return size;}
	
	public ListItem Retrieve(int position) {
			ListItem p = head;
			for(int i = 0; i < position; i++) {
				p = p.next;
			}
			return p;
		}
	public ListItem Head() {
		return head;
		}
	
	public boolean Insert(int newPosition, ListItem newItem) {
		if(newPosition == 0) {
			newItem.next = head;
			head = newItem;
			size++;
			return true;
		} else if(Length() > newPosition) {
			ListItem p = Retrieve(newPosition);
			newItem.next = p;
			Retrieve(newPosition - 1).next = newItem;
			size++;
			return true;
		} else if(Length() == newPosition) {
			Retrieve(newPosition - 1).next = newItem;
			size++;
			return true;
		}
		else
			return false;
	}
	public boolean AddFront(ListItem newItem) {
		Insert(0, newItem);
		return true;			
	}
	public boolean AddRear(ListItem newItem) {
		Retrieve(Length() - 1).next = newItem;
		size++;
		return true;
	}
	public boolean Delete(int position) {
		if(IsEmpty()) {return false;}
		else if(position == 0) {
			ListItem p = head;
			head = p.next;
			return true;
		}
		else {
			Retrieve(position - 1).next = Retrieve(position+1);
			size--;
			return true;
		}
	}
	public boolean DeleteFront() {
		if(IsEmpty()) return false;
		else {
			ListItem p;
			p = head;
			head = p.next;
			size--;
			return true;
		}
	}
	public void First() {
		index = head;
	}
	public void Next() {
		index = index.next;
	}
	public boolean Done() {
		if(Length() == 0) {
			return false;
		} else if(index.next == null) {
			return false;
		}
		else return true;
	}
	public ListItem Current() {
		return index;
	}
	
	public int ListSum() {
		if(Length() != 0) {
		First();
		int sum = index.getElement();
		while(Done()) {
			Next();
			int i = index.getElement();
			sum += i;
		}
		return sum;
		} else return 0;
	}
	
	public String ListPrint() {
		if(Length() != 0) {
		First();
		int i = index.getElement();
		String s = String.valueOf(i) + " ";
		while(Done()) {
			Next();
			i = index.getElement();
			s = s + String.valueOf(i) + " ";
		}
		return s;
		} else return null;
	}
	
}
