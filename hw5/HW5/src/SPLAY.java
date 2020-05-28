import java.util.LinkedList;
import java.util.Queue;

public class SPLAY <Key extends Comparable<Key>, Value>{
	public Node root;
	public Node Smaller = null;
	public Node Bigger = null;
	public Node Pmax = null;
	public Node Pmin = null;
	public SPLAY(Key newId, Value newName)//constructor
	{
		if(newId == null && newName == null) {root = null;}
		else { root = new Node(newId, newName);}
	}
	
	public void endSplay(Node n)
	{
		if(n.getLeft()!=null) {setSmaller(n.getLeft());}
		if(n.getRight()!=null) {setBigger(n.getRight());}
		n.setLeft(Smaller);
		n.setRight(Bigger);
		root=n;
		
		Bigger=null;
		Smaller=null;
		Pmax=null;
		Pmin=null;
	}
	
	public void setBigger(Node n)
	{
		if(Bigger==null) {Bigger=n;} 
		else{Pmin.setLeft(n);}
		
		Pmin=n;
	}
	
	public void setSmaller(Node n)
	{
		if(Smaller==null) {Smaller=n;} 
		else{Pmax.setRight(n);}
		Pmax=n;
	}
	
	public void zigZigLL(Node n, Node m, Node x)
	{
		n.setLeft(m.getRight());
		m.setRight(n);
		root=x;
		m.setLeft(null);
		setBigger(m);
	}
	
	public void zigL(Node n, Node m)
	{
		n.setLeft(null);
		setBigger(n);
		root=m;
	}
	
	public void zigZigRR(Node n, Node l,Node x)
	{
		n.setRight(l.getLeft());
		l.setLeft(n);
		root=x;
		l.setRight(null);
		setSmaller(l);
	}
	
	public void zigR(Node n, Node l)
	{
		n.setRight(null);
		root=l;
		setSmaller(n);
	}
	
	public void delSplayL(Node a, Node b)
	{
		if(b.getLeft()!=null) {setSmaller(b.getLeft());}
		if(b.getRight()!=null) {setSmaller(b.getRight());}
		b.setLeft(null);
		b.setRight(null);
		a.setLeft(null);
		b=null;
		endSplay(a);
	}
	
	public void delSplayR(Node a, Node b)
	{
		if(b.getRight()!=null) {setBigger(b.getRight());}
		if(b.getLeft()!=null) {setBigger(b.getLeft());}
		b.setLeft(null);
		b.setRight(null);
		a.setRight(null);
		b=null;
		endSplay(a);
	}
	
	public Node getroot() {return root;}
	
	public Value get(Key k)//search k in the tree and print its name
	{	
		Node n = root;
		if (n==null) {return null;}
		
		int t = n.getKey().compareTo(k);
		if(t>0)//n is bigger
		{
			Node m=n.getLeft();
			if(m!=null)
			{
				int t2 = m.getKey().compareTo(k);
				if (t2>0) 
				{
					Node x = m.getLeft();
					if(x!=null){zigZigLL(n,m,x);}
					else {zigL(n,m);}
				}
				else//move to m.left or m is the target
				{
					zigL(n,m);
					if(t2==0)//m is the key
					{				
						endSplay(m);
						return (Value) m.getValue();
					}
				}
			}
			else //m is null = n is the splay node
			{
				endSplay(n);
				return null;
			}
		}
		else if(t<0)//n is smaller
		{
			Node l=n.getRight();
			if(l!=null)
			{
				int t2 = l.getKey().compareTo(k);
				if (t2<0) 
				{
					Node y = l.getRight();
					if(y!=null){zigZigRR(n,l,y);}
					else {zigR(n,l);}
				}
				else
				{
					zigR(n,l);
					if(t2==0)
					{				
						endSplay(l);
						return (Value) l.getValue();
					}
				}
			}
			else //l is null = n is the end splay
			{
				endSplay(n);
				return null;
			}
		}	
		else //t=0
		{
			endSplay(n);
			return (Value) n.getValue();
		}
		return get(k);
	}
	
	public void put(Key k, Value v) //insert (k,v) into the tree
	{
		Node n = root;
		Boolean done = false;
		
		if (n==null) //put new node as a root and merge
		{
			Node a = new Node(k,v);
			endSplay(a);
			return;
		}
		
		int t = n.getKey().compareTo(k);
		if(t>0)//n is bigger
		{
			Node m=n.getLeft();
			if(m!=null)
			{
				int t2 = m.getKey().compareTo(k);
				if (t2>0) 
				{
					Node x = m.getLeft();
					zigZigLL(n,m,x);
				}
				else//move to m.left or m is the target
				{
					zigL(n,m);
					if(t2==0)//m is the key
					{
						m.setValue(v);
						endSplay(m);
						done=true;
					}
				}
			}
			else //m is null
			{
				m = new Node(k,v);
				setBigger(n);
				endSplay(m);
				done=true;
			}
		}
		else if(t<0)//n is smaller
		{
			Node l=n.getRight();
			if(l!=null)
			{
				int t2 = l.getKey().compareTo(k);
				if (t2<0) 
				{
					Node y = l.getRight();
					zigZigRR(n,l,y);
				}
				else
				{
					zigR(n,l);
					if(t2==0)
					{
						l.setValue(v);
						endSplay(l);
						done=true;
					}
				}
			}
			else //l is null
			{
	            l = new Node(k,v);
				setSmaller(n);
				endSplay(l);
				done=true;
			}
		}	
		else //t=0
		{
			n.setValue(v);
			endSplay(n);
			done=true;
		}
		if(!done){put(k,v);}
	}
	
	public Key min()//find the minimum key in the tree
	{
		Node n = root;
		if (n==null) {return null;}//nothing in the tree
		
		Node m=n.getLeft();
		if(m==null)//n is min
		{
			endSplay(n);
			return (Key) n.getKey();
		}
		else
		{
			Node x = m.getLeft();
			if(x==null)//m is min
			{
				zigL(n,m);
				endSplay(m);
				return (Key) m.getKey();
			}
			else//if x exists
			{
				zigZigLL(n,m,x);
				return min();
			}
		}
	}
	
	public Key max()//find the maximum key in the tree
	{
		Node n = root;
		if (n==null) {return null;}//nothing in the tree
		
		Node l=n.getRight();
		if(l==null)//n is max
		{
			endSplay(n);
			return (Key) n.getKey();
		}
		else
		{
			Node y = l.getRight();
			if(y==null)//l is max
			{
				zigL(n,l);
				endSplay(l);
				return (Key) l.getKey();
			}
			else
			{
				zigZigRR(n,l,y);
				return min();
			}
		}
	}
	
	public void deleteMin()//delete the smallest key in the tree
	{
		Node n = root;//delete if there's any node in the tree
				
		Node m=n.getLeft();
		if(m==null)//min in the top
		{
			Node x = n.getRight();
			n.setRight(null);
			n=null;
			root=x;
			return;
		}
		
		else
		{
			Node x = m.getLeft();
			if(x==null)//m is min, n is the splay node
			{
				Node p = m.getRight();
				m.setRight(null);
				setSmaller(p);
				n.setLeft(null);
				m=null;
				endSplay(n);
			}
			else//if x exists, recursive deleteMin
			{
				zigL(n,m);
				deleteMin();
			}
		}
	}

	public void deleteMax()//delete the largest key in the tree
	{
		Node n = root;//delete if there are more than one nodes in the tree

		Node m=n.getRight();
		if(m==null)//max in the top
		{
			Node x = n.getRight();
			n.setRight(null);
			n=null;
			root=x;
			return;
		}
		else
		{
			Node x = m.getRight();
			if(x==null)//m is max, n is the splay node
			{
				Node p = m.getLeft();
				m.setLeft(null);
				setBigger(p);
				n.setRight(null);
				m=null;
				endSplay(n);
			}
			else//if x exists, recursive deleteMax
			{
				zigR(n,m);
				deleteMax();
			}
		}
	}

	public void delete(Key k) //delete the key k in the tree if it is in the tree
	{
		Boolean done = false;
		
		Node n = root;// suppose that there's more than two nodes in the tree
		int t = n.getKey().compareTo(k);
		
		if(t>0)//n is bigger
		{
			Node m=n.getLeft();
			if(m!=null)
			{
				int t2 = m.getKey().compareTo(k);
				if (t2>0)//m is bigger 
				{
					Node x = m.getLeft();
					if(x!=null)
					{
						if(x.getKey()==k) //x is the target, m is the splay node{}
						{
							zigL(n,m);
							delSplayL(m,x);
							done=true;
						}
						else {zigZigLL(n,m,x);}
					}
					else // n is a splay node
					{
						delSplayL(n,m);
						done=true;
					}
				}
				else if(t2>0) {zigL(n,m);}//move to m.left
				else //m is the key to be deleted = n is the splay node
				{
					delSplayL(n,m);
					done=true;
				}
			}
			else //m is null = n is the splay node = parent of null
			{
				endSplay(n);
				done=true;
			}
		}
		else if(t<0)//n is smaller
		{
			Node l=n.getRight();
			if(l!=null)
			{
				int t2 = l.getKey().compareTo(k);
				if (t2>0) 
				{
					Node y = l.getRight();
					if(y!=null)
					{
						if(y.getKey()==k) //y is the target, l is the splay node{}
						{
							zigR(n,l);
							delSplayL(l,y);
							done=true;
						}
						else 
						{zigZigRR(n,l,y);}
					}
					else // n is a splay node
					{
						delSplayR(n,l);
						done=true;
					}
				}
				else if(t2>0) {zigR(n,l);}//move to m.left
				else //l is the key to be deleted = n is the splay node
				{
					delSplayR(n,l);
					done=true;
				}
			}
			else //m is null = n is the splay node = parent of null
			{
				endSplay(n);
				done=true;
			}
		}
		else//root is the node to be deleted
		{
			if(n.getRight()==null)
			{
				if(n.getLeft()==null){return;}
				else
				{
					Node x = n.getLeft();
					n.setLeft(null);
					n=null;
					root=x;
				}
			}
			else
			{
				if(n.getLeft()==null)
				{
					Node x = n.getRight();
					n.setRight(null);
					n=null;
					root=x;
				}
				else
				{
					Node t1 = n.getRight();
					if(t1.getLeft()==null)//inorder successor
					{
						t1.setLeft(n.getLeft());
						n.setRight(null);
						n.setLeft(null);
						n=null;
						root=t1;
						done=true;
						return;
					}
					else
					{
						int i=0;
						Node t2 = n.getRight();
						while(t1.getLeft()!=null) 
						{
							t1=t1.getLeft();//inorder successor
							if(i>0) t2=t2.getLeft();//parent of inorder successor
						}
						Node originalT1Right = t1.getRight();
						
						t1.setLeft(n.getLeft());
						t1.setRight(n.getRight());
						t2.setLeft(null);
						t2.setRight(originalT1Right);
						root=t1;
						done=true;
						return;
					}
				}
			}
		}
		if(!done){delete(k);}
	}
	
	public int height(Node n)//returns the height of the tree
	{
		if(n==null) return 0;
		else
		{
			int leftT = height(n.getLeft());
			int rightT = height(n.getRight());
			return 1 + (leftT > rightT ? leftT : rightT);
		}	
	}
	
	public void print(Node root)//print the tree
	{
		st="";
	    System.out.println(inorder(root));
	    System.out.println(levelorder(root));
	}
	
	public void stringClr() {st="";}
	
	String st="";
	public String inorder(Node root) 
	{
		if(root != null)
		{
	        if(root.getLeft() != null) inorder(root.getLeft());
	        st = st + root.getKey().toString() + " ";
	        if(root.getRight() != null) inorder(root.getRight());
	    }
	    return st;
	}
	   
	 public String levelorder(Node root) 
	   {
		   Queue<Node> q = new LinkedList<Node>();
		   Node t;
		   String str = "";
		   q.add(root);
		   while(!q.isEmpty()) 
		   {
		         t = q.remove();
		         if(t != null) 
		         {
		            str = str + "(" + t.getKey().toString() + " : " + t.getValue().toString() + ")";
		            if(t.getLeft() != null) q.add(t.getLeft());
		            if(t.getRight() != null) q.add(t.getRight());
		         }
		   }
		   return str;
		}
	 
	 public void showDFS(Node node, int depth, int mode)
	 {
		 int mode_root =0;
		 int mode_right = -1;
		 int mode_left = 1;
		 if(node.getRight() != null) {showDFS(node.getRight(),depth+1,mode_right);}
		 for(int i = 0; i<depth;i++) {System.out.print("    ");}
		 if(mode==mode_root) System.out.print("  RT =");
		 else if (mode == mode_right) System.out.print("   ¦£");
		 else System.out.print("   ¦¦");
		 
		 System.out.println(String.format("%-3d",node.getKey()));
		 if(node.getLeft()!=null) showDFS(node.getLeft(),depth+1,mode_left);
	 }
	 
	 public void show()
	 {
		 System.out.println("\n<SPLAY TREE>");
		 if(root==null) System.out.println("[void SPLAY TREE]");
		 else showDFS(root,0,0);
		 System.out.println();
	 }
}
