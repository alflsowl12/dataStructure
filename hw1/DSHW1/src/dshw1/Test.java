package dshw1;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		List list = new List();
		ListItem a = new ListItem(11);
		ListItem b = new ListItem(23);
		ListItem c = new ListItem(38);
		ListItem d = new ListItem(1);
		

		
		list = new List();
		
		list.AddFront(a);
		System.out.println(list.ListPrint());
		list.AddFront(c);
		System.out.println(list.ListPrint());
		list.Insert(2,b); //length = 3, 
		System.out.println(list.ListPrint());
		list.Insert(3,d);
		System.out.println(list.ListPrint());
		list.Insert(4,a);
		System.out.println(list.ListPrint());
		System.out.println(list.Retrieve(2));
		/*list.AddFront(d);
		System.out.println(list.ListPrint());
		System.out.println(list.ListSum());*/
	}
}
