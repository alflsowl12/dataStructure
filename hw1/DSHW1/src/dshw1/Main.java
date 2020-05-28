package dshw1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
	private static List list = new List();
	private static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("C:\\hw1\\input.txt"));
		PrintWriter outputFile = new PrintWriter("C:\\hw1\\2017122030.txt");
		String line;
		
		while((line = br.readLine()) != null) {
			String[] parts = line.split("\n");
			for(String part : parts) {
				String[] command = part.split(" ");
				
				if(part.startsWith("insert")) {
					int i = Integer.parseInt(command[1]);
					int j = Integer.parseInt(command[2]);
					ListItem p = new ListItem(j);
					list.Insert(i, p);
					System.out.println(list.ListPrint());
				}
				else if(part.startsWith("delete")) {
					if(command[1].startsWith("front")) {
						list.DeleteFront();
						System.out.println(list.ListPrint());
					}else {
						int i = Integer.parseInt(command[1]);
						list.Delete(i);
						System.out.println(list.ListPrint());
					}
					
				}
				else if(command[0].startsWith("is")) {
					if(list.IsEmpty()) {
						outputFile.println("yes");
					}
					else outputFile.println("no");
				}
				else if(command[0].startsWith("head")) {
					outputFile.println(list.Head().getElement());
				}
				else if(command[0].startsWith("length")) {
					outputFile.println(list.Length());
					System.out.println(list.Length());
				}
				else if(command[0].startsWith("add")) {
					int i = Integer.parseInt(command[2]);
					ListItem p = new ListItem(i);
					if(command[1].startsWith("front")) {
						list.AddFront(p);
						System.out.println(list.ListPrint());
					} else if(command[1].startsWith("rear")) {
						list.AddRear(p);
						//System.out.println(list.ListPrint());
					}
				}
				else if(command[0].startsWith("list")) {
					if(command[1].startsWith("print")) {
						System.out.println(list.Length());
						System.out.println(list.ListPrint());		
						outputFile.println(list.ListPrint());
					} else if(command[1].startsWith("sum")) {
						System.out.println(list.ListSum());
						outputFile.println(list.ListSum());
					}
				}
				else {
					list = new List();
					outputFile.println(" ");
				}
				
			}
		}
		outputFile.close();
		br.close();
	}
}
