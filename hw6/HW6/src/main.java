import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class main {
	private static BufferedReader br;
		
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("C:\\hw6\\input.txt"));//read file
		PrintWriter outputFile = new PrintWriter("C:\\hw6\\2017122004.txt");//write file
		String line;
			
		int loopnum = 0;
		int totalloop=0;//the number of test sets
		int setNum=1;
		int totalgraph=0;//the number of total nodes
		Boolean bcc=true;
		Random random = new Random();
			
		while((line=br.readLine())!=null)//read file by line
		{
			String[] parts = line.split("\n");//split line by /n	
			
			for(String part : parts) {
				String[] command = part.split(" ");//one line is parted by commands
				
				if(loopnum==0) {//first line means totalloop
					int a = Integer.parseInt(command[0]);
					totalloop=a;
					loopnum++;//loopnum increases
				}
				
				else if (loopnum%3 == 1 && loopnum<=totalloop*3) //if the first line of the test set
				{
					if(command[0].startsWith("BCC")) {bcc=true;}
					else if(command[0].startsWith("SCC")) {bcc=false;}
					loopnum++;
				}
				
				else if (loopnum%3 == 2 && loopnum<=totalloop*3) //if the second line of the test set
				{
					int a = Integer.parseInt(command[0]);//first integer as a
					totalgraph=a;
					loopnum++;
				}
				
				else if (loopnum%3 == 0 && loopnum<=totalloop*3) //if the third line of the test set
				{
					outputFile.println(setNum);
					int pairNum = command.length/2;
					
					Graph gp = new Graph(totalgraph);
					
					for(int i = 0; i<pairNum; i++)
					{
						int a = Integer.parseInt(command[2*i]);
						int b = Integer.parseInt(command[2*i+1]);
						gp.insertEdge(a,b);
						if(bcc) gp.insertEdge(b,a);
					}
					
					int rNum = random.nextInt(totalgraph);
					
					if(bcc) gp.biconnected(rNum,-1);
					else if(!bcc) gp.scc(rNum);
					
					ArrayList<ArrayList<Integer>> endList = gp.lst();
					int listSize =endList.size();
					for(int i=0; i<endList.size(); i++) {
						for(int j=0; j<endList.get(i).size();j++) outputFile.print(endList.get(i).get(j) + " ");
						outputFile.println();
					}
					outputFile.println();
					
					loopnum++;
					setNum++;
				}
			}
		}
		outputFile.close();
		br.close();
		
	}

}
