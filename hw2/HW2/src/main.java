import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class main {
	
	private static ListStack ls = new ListStack();
	private static BufferedReader bf;
	private static PrintWriter output;

	public static void main(String[] args) throws IOException
	{
		bf = new BufferedReader(new FileReader("C:\\hw2\\input.txt"));//read file
		output = new PrintWriter("C:\\hw2\\2017122004.txt");
		String line;
		int loopnum=1;//the number of line in the txt time
		int totalloop=0;//the number of test sets
		int innerloop=0;//the number of moving stack element in the stack
		
		while((line=bf.readLine())!=null)//read file by line
		{
			String[] parts = line.split("\n");//split line by /n	
			
			for(String part : parts) {
				String[] command = part.split(" ");//one line is parted by commands
				
				if(loopnum==1) {//first line means totalloop
					int a = Integer.parseInt(command[0]);
					totalloop=a;
					loopnum++;//loopnum increases
				}
				
				else if (loopnum%2 == 0 && loopnum<=totalloop*2) {//if the first line of the test set
					int a = Integer.parseInt(command[0]);//first integer as a
					int b = Integer.parseInt(command[1]);//first integer as b
					ls.makeStack(a);//make stack with a number elements
					innerloop = b;//move stack b times
					loopnum++;//loopnum increases
				}
				
				else if  (loopnum%2 == 1 && loopnum<=(totalloop*2)+1){//if the second line of the test set
					for(int i=0;i<innerloop;i++) {//for innerloop times
						int a = Integer.parseInt(command[i]);
						ls.moveStack(a);//move a in the stack

						output.print(ls.above() + " ");//print the number of elements to move the element
						
						if(i+1 == innerloop) {//if the innerloop finished
							ls = new ListStack();//reset the liststack
							loopnum++;//loopnum increases
							output.println();//new line
						}
					}
				}
			}
		}
		output.close();
		bf.close();
	}			
}
