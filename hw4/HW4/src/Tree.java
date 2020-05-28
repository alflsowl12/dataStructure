
public class Tree {
	protected Node[] El;//element array
	protected int[] P;//parent array
	protected boolean rightT=true;
	protected int eNum=0;// number of elements
	protected int pNum=0;// number of parents
	int[] root = null;//root array
	int treeSize =0;//tree size
	
	Tree() {};//constructor
	
	public int geteNum() {return eNum;}
	public int getpNum() {return pNum;}
	public boolean rightT() {return rightT;}
	public int treesize() {return treeSize;}//getters

	public void set(int num)//set the size of El, P, root array
	{
		El = new Node[num];
		P = new int[num];
		root = new int[num];
	};
	
	public void put(int newElem, int newPar)//put elements and set its parent
	{
		boolean existed = false;
		if(eNum!=0)//if not the first element
		{
			for(int i = 0; i<eNum; i++)
			{
				if(El[i].getNum()==newElem)//if not disjoint sets
				{
					existed = true;//existed true
					break;
				}
			}
		}
		if(existed==true)// not a tree
		{
			rightT=false;
			return;
		}
		
		else//if disjoint sets
		{
			Node a = new Node(newElem, newPar); 
			El[eNum] = a;//add element
			eNum++;
				
			boolean pExisted = false;
			for(int i = 0; i<pNum; i++)
			{
				if(P[i]==newPar)
				{
					pExisted = true;//if parent element already exists in the array
					break;//do nothing
				}
			}
			
			if(pExisted==false)//if not
			{
				P[pNum] = newPar;//add in the parent array
				pNum++;
			}
		}
	}
	
	public void size()//return the size of the tree
	{
		int rootNum =0;
		
		for (int i =0; i<pNum; i++)//for every parent node
		{
			boolean inElem = false;	
			for(int j=0; j<eNum;j++)//check whether also in the element array
				if(P[i]==El[j].getNum()){inElem = true;}
			
			if(!inElem)//if not in the element array
			{
				root[rootNum]=P[i];//the parent becomes the root
				rootNum++;
			}
		}
		
		if(rootNum != 1)//if there is more than one root
		{
			rightT=false;//not a tree
			return;
		}
		
		else//if only one root exists
		{
			int rt = root[0];
			int tSize =1;
			
			for(int a=0;a<eNum;a++)//for every node in the element array
			{
				int elemNode = rootNum(a,rt,1);//count the number of node toward the root
				if(a==0) {tSize=elemNode;}
				else if (elemNode>tSize) {tSize=elemNode;}//get the max number which is the size of the tree
			}
			treeSize=tSize;
		}
	}
	
	public int rootNum(int i, int root, int elemSize)//count the number of node toward the root
	{
		if(El[i].getPar()==root) {return (elemSize+1);}//if its parent is root, its number is elemSize+1
		
		else//if not
		{
			int par = El[i].getPar();
			for(int a=0; a<eNum; a++)
			{
				if(a==i) {continue;}
				else if (El[a].getNum()==par)//find the parent of the element
				{
					elemSize++;
					if(El[a].getPar()==root)  {return (elemSize+1);}//check whether its parent's parent is root
					else {return(rootNum(a,root,elemSize));}//or count more
				}
			}
		}
		return elemSize;
	}
	
}
