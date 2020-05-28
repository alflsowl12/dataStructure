
public class Edge {
	public int nodeNum;
	public Edge nextEdge;
	public Edge(int num, Edge e) {
		nodeNum = num;
		nextEdge = e;
	}
}