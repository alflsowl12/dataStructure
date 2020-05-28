
public class Node {
	int parent;//its parent
	int num;//its element
	
	Node(int elem, int par){//constructor
		num=elem;
		parent = par;
	}
	
	public int getNum() {return num;}
	public int getPar() {return parent;}//getters
}
