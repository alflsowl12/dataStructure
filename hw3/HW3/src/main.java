import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class main {
	private static BufferedReader br;
	private static ListQueue notArriveQue = new ListQueue();
	private static ListQueue waitQue = new ListQueue();
	private static ListQueue serQue = new ListQueue();
	private static ListQueue serTime = new ListQueue();
	
	private static int maxQueSize = 0;
	private static int totalWtime = 0;
	private static int totalStime = 0;
	private static int idleTime = 0;
	private static float totalNum = 0;
	private static int doneNum = 0;//initialization
			
	public static void main(String[] args) throws IOException
	{

		br = new BufferedReader(new FileReader("C:\\hw3\\input.txt"));//read file
		PrintWriter outputFile = new PrintWriter("C:\\hw3\\2017122004.txt");//write file
		String line;
		
		int loopnum=0;//number of line
		int totalloop=0;//total cases
		
		while((line=br.readLine())!=null)//read file by line
		{
			String[] parts = line.split("\n");//split line by /n	
			
			for(String part : parts) {
				String[] command = part.split(" ");//one line is parted by commands
				
				if(loopnum==0) //first line means totalloop
				{
					int a = Integer.parseInt(command[0]);
					totalloop=a;
					loopnum++;//loopnum increases
				}
				
				else if (loopnum%3 == 1 && loopnum<=totalloop*3)//first line has the number n of calls
				{
					int a = Integer.parseInt(command[0]);
					totalNum=a;//a means total number
					loopnum++;//loopnum increases
				}
				
				else if (loopnum%3 == 2 && loopnum<=totalloop*3)//second line has arrival times of each call
				{
					for(int i=0;i<totalNum;i++) 
					{
						int a = Integer.parseInt(command[i]);//for every number in the line
						notArriveQue.insert(a);//put in the queue which customer who never tried to get service
					}
					loopnum++;//loopnum increases
				}
				
				else if (loopnum%3 == 0 && loopnum<=totalloop*3)//third line has service times of each call
				{
					for(int i=0;i<totalNum;i++) 
					{
						int a = Integer.parseInt(command[i]);//for every number in the line
						serTime.insert(a);//put in serTime queue
						totalStime+=a;
					}

					while(doneNum!=totalNum)//Timing loop(until every calls are finished)
					{						
						if(notArriveQue.size()>0) // if there's element in notArriveQue
						{
							while(notArriveQue.front().getElement()==0)//while there's call going to request service
							{
								notArriveQue.delete();//delete the element from the queue
								waitQue.insert(0);//insert new element with wait time=0
								if(notArriveQue.isEmpty()) break;//if there's no element in notArriveQueue anymore, break it
							}
						}
						
						if(!serQue.isEmpty())//call in service
						{
							if(serQue.front().getElement() == 0)//if the service ended
							{
								serQue.delete();//delete the call
								doneNum++;//done call number increases
								if(doneNum==totalNum) break;//if every call is done, break the loop
							}
						}
						
						if(serQue.isEmpty())//nothing in the service queue
						{
							if(waitQue.isEmpty())//also nothing in the waiting queue
								idleTime++;//idle time increases
							
							else//Start new call
							{
								totalWtime+=waitQue.front().getElement();//add wait time of first element in the waiting queue to total wait time
								waitQue.delete();//delete the element
								serQue.insert(serTime.front().getElement());//put the element with the service time which is the first element of the serTime queue
								serTime.delete();//delete the first element of the serTime
								
								if(!waitQue.isEmpty())//if still some elements left in the waitQue
									waitQue.addOne();//add 1-minute wait time to every element
							}
						}
						
						else if(!serQue.isEmpty())//call in service
						{
							if(!waitQue.isEmpty())//if some elements left in the waitQue
								waitQue.addOne();//add 1-minute wait time to every element
						}
						
						if(waitQue.size()>maxQueSize)//get the greatest number of calls waiting during the simulation
							maxQueSize=waitQue.size();
						
						notArriveQue.subractOne();//1-minute passed in the notArriveQue
						serQue.subractOne();//1-minute passed in subtractQue			
					}
					
					float AvgWtime = totalWtime/totalNum;//average wait time
					float AvgStime = totalStime/totalNum;//average service time

					outputFile.println(String.format("%.0f", totalNum)+" "+idleTime+" "+totalWtime+" "+maxQueSize+" "
					+String.format("%.2f", AvgWtime)+" "+String.format("%.2f", AvgStime));//print statistics of phone-call service
					
					
					notArriveQue = new ListQueue();
					waitQue = new ListQueue();
					serQue = new ListQueue();
					serTime = new ListQueue();
					
					maxQueSize = 0;
					totalWtime = 0;
					totalStime = 0;
					idleTime = 0;
					totalNum = 0;
					doneNum = 0;//initialize
					
					loopnum++;//loop number increases
				}
			}
		}
		outputFile.close();
		br.close();
	}
}