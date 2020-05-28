import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

public class Graph {
	private int N; // number of nodes
	public Node[] graph;
	public int[] dfn;
	public int[] low;
	private int num=1;
	private Stack<Integer> st = new Stack<Integer>();
	public ArrayList<ArrayList<Integer>> lst = new ArrayList<ArrayList<Integer>>();
	
	public Graph(int n) {
		N=n;
		graph = new Node[N];
		dfn = new int[N];
		low = new int[N];
		for(int i = 0; i<N; i++)
		{
			graph[i] = new Node();
			dfn[i]=0;
			low[i]=0;//initializtion
		}
	}
	
	public ArrayList<ArrayList<Integer>> lst() {return lst;}
	
	//insertEdge : insert front
	public void insertEdge(int tail, int head) {
		graph[tail].firstEdge = new Edge(head, graph[tail].firstEdge);
		graph[tail].degree++;
	}
	
	//returns the number of nodes in graph
	public int V() {return N;}
	
	//returns node v's adjacent nodeNums as an int array
	public int[] adj(int v) {
		int count = 0;
		int[] adjNodes = new int[graph[v].degree];
		Edge p = graph[v].firstEdge;
		while(p != null) {
			adjNodes[count++] = p.nodeNum;
			p = p.nextEdge;
		}
		return adjNodes;
	}
	
	public void biconnected(int u,int v)
	{
		dfn[u]=low[u]=num++;
		for(int w:adj(u))
		{
			if(w != v && dfn[w] < dfn[u]) 
			{
				st.push(u);
				st.push(w);
			}
			if(dfn[w]==0)
			{
				biconnected(w,u);
				low[u] = min(low[u],low[w]);
				if(low[w]>=dfn[u])
				{
					ArrayList<Integer> bccLst = new ArrayList<Integer>();
					while(!st.isEmpty()) 
					{
						int a = (int) st.pop();
						int b = (int) st.pop();
						if(!bccLst.contains(a)) bccLst.add(a);
						if(!bccLst.contains(b)) bccLst.add(b);
						if((a==w) && (b==u)){break;}
					}
					Collections.sort(bccLst);
					int length = bccLst.size();
					int index = 0;
					for(index = 0; index < lst.size(); index++)
					{
						if(length==lst.get(index).size())
						{
							while(length==lst.get(index).size())
							{
								boolean finished =false;
								for(int num=0; num<length; num++) 
								{	
									if(bccLst.get(num)<lst.get(index).get(num)) 
									{
										finished=true;
										break;
									}
									else if (bccLst.get(num)>lst.get(index).get(num)){break;}
								}
								if(!finished&&index<lst.size()-1) index++;
								else break;
							}
							break;
						}
						else if (length<lst.get(index).size()){break;}
					}
					lst.add(index, bccLst);
				}
			}
			else if(w!=v) low[u]=min(low[u],dfn[w]);//(u,w) is a back edge
		}
	}
	
	public void scc(int u)
	{
		stronglyConnected(u);
		boolean terminated=false;
		while(!terminated) 
		{
			int notZero=0;
			for(int i = 0; i<N; i++) 
			{
				if(dfn[i]==0){stronglyConnected(i);}
				else notZero++;
			}
			if(notZero==N) terminated=true;
		}
	}
	
	private void stronglyConnected(int u)
	{
		dfn[u] = low[u] = num++;
		st.push(u);
		for(int w:adj(u))
		{
			if(dfn[w]==0)
			{
				stronglyConnected(w);
				low[u]=min(low[u],low[w]);//w is a child of u
			}
			else if ((dfn[w]<dfn[u])&&(st.contains(w)))
				low[u]=min(low[u],dfn[w]);
		}
		if(low[u]==dfn[u])
		{
			if(!st.isEmpty()) 
			{
				ArrayList<Integer> sccLst = new ArrayList<Integer>();
				while(st.contains(u) && !st.isEmpty())
				{
					int a = (int) st.pop();
					sccLst.add(a);
				}
				Collections.sort(sccLst);
				int length = sccLst.size();
				int index = 0;
				for(index = 0; index < lst.size(); index++)
				{
					if(length==lst.get(index).size())
					{
						while(length==lst.get(index).size())
						{
							boolean finished =false;
							for(int num=0; num<length; num++) 
							{	
								if(sccLst.get(num)<lst.get(index).get(num)) 
								{
									finished=true;
									break;
								}
								else if (sccLst.get(num)>lst.get(index).get(num)){break;}
							}
							if(!finished&&index<lst.size()-1) index++;
							else break;
						}
						break;
					}
					else if (length<lst.get(index).size()){break;}
				}
				lst.add(index, sccLst);
			}
		}
	}
	
	private int min(int a, int b)
	{
		if(a>b) return b;
		else if(a<b) return a;
		else return a;
	}
}
