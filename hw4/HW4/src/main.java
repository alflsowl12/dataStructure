import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class main {
	private static BufferedReader br;
	private static Tree tr = new Tree();

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new FileReader("C:\\hw4\\input.txt"));//read file
		PrintWriter outputFile = new PrintWriter("C:\\hw4\\2017122004.txt");//write file
		String line;
		
		int loopnum=1;//the number of line in the txt time
		int totalloop=0;//the number of test sets
		int totaltree=0;//the number of total nodes
		
		while((line=br.readLine())!=null)//read file by line
		{
			String[] parts = line.split("\n");//split line by /n	
			
			for(String part : parts) {
				String[] command = part.split(" ");//one line is parted by commands
				
				if(loopnum==1) {//first line means totalloop
					int a = Integer.parseInt(command[0]);
					totalloop=a;
					loopnum++;//loopnum increases
				}
				
				else if (loopnum%2 == 0 && loopnum<=totalloop*2) //if the first line of the test set
				{
					int a = Integer.parseInt(command[0]);//first integer as a
					totaltree=a;
					tr.set(a);//set number of nodes in tree as a
					loopnum++;
				}
				
				else if  (loopnum%2 == 1 && loopnum<=(totalloop*2)+1)//if the second line of the test set
				{
					for(int i = 0; i<totaltree; i++) 
					{
						int a = Integer.parseInt(command[i*2]);
						int b = Integer.parseInt(command[i*2+1]);
						tr.put(b, a);//put first one as a parent and second as an element
					}
					tr.size();//count the size of the tree
					outputFile.print(tr.rightT()+" ");//print whether the right tree or not
					if(tr.rightT()) {outputFile.println(tr.treesize());}//if right tree, print size
					else {outputFile.println(" ");}//if false tree, print nothing
					tr= new Tree();//make new tree
					
					loopnum++;//loopnum increases
				}
			}
		}
		outputFile.close();
		br.close();
	}

}
