import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class main {
	private static BufferedReader br;
	private static SPLAY sp = new SPLAY(null,null);
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new FileReader("C:\\hw5\\input.txt"));//read file
		PrintWriter outputFile = new PrintWriter("C:\\hw5\\2017122004.txt");//write file
		String line;
		
		int loopnum = 1;
		
		while((line=br.readLine())!=null)//read file by line
		{
			String[] parts = line.split("\n");//split line by /n	
			
			for(String part : parts) 
			{
				String[] commands = part.split("\\(");//one line is parted by commands

				if(loopnum<=20) {//first 20 lines to be put
					if(part.startsWith("put"))
					{
						String[] input = commands[1].split(",");
						int key = Integer.parseInt(input[0]);//key
						String value = input[1].substring(0, input[1].length()-1);
						sp.put(key, value);
					}
				}
				else
				{
					if(part.startsWith("put")) //if starts with put
					{
						String[] input = commands[1].split(",");
						int key = Integer.parseInt(input[0]);//key
						String value = input[1].substring(0, input[1].length()-1);
						sp.put(key, value);
					}	
					else if(part.startsWith("height")) 
					{outputFile.println(sp.height(sp.getroot()));}
					else if(part.startsWith("get"))
					{
						String[] input = commands[1].split("\\)");
						int key = Integer.parseInt(input[0]);//key
						if (sp.get(key)==null) {outputFile.println(key+"is not in the tree");}
						else {outputFile.println(sp.get(key));}
						
					}
					else if(part.startsWith("min"))
					{outputFile.println(sp.min());}
					
					else if(part.startsWith("print"))
					{
						sp.show();
						outputFile.println("Tree visualization is in the console");
						sp.stringClr();
						outputFile.println(sp.inorder(sp.getroot()));
						outputFile.println(sp.levelorder(sp.getroot()));
					}
					else if(part.startsWith("deleteMin")){sp.deleteMin();}
					else if(part.startsWith("deleteMax")){sp.deleteMax();}
					else//delete(key k)
					{
						String[] input = commands[1].split("\\)");
						int key = Integer.parseInt(input[0]);//key
						sp.delete(key);
					}
				}
				loopnum++;
			}
		}
		outputFile.close();
		br.close();
	}

}




