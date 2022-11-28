import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;

public class Node {
	public Integer id;
	public String name;
	public Node p;
	public float d;
	public List<Edge> outboundEdges = new LinkedList<Edge>();
	
	public Node(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	// Done for you
	public void addEdge(Edge edge) {
		this.outboundEdges.add(edge);	
	}
	
	// Implemented for you:
	public Edge getBackEdge(Node node) {
		return null;
	}
	
	// Implemented for you:
	@Override
	public String toString() {
		String edgeString = this.id + "\t";
		edgeString += this.name + "\t";
		edgeString += (this.p != null) ? this.p.name : '*';
		return edgeString;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject entry = new JSONObject();
		JSONObject data = new JSONObject();
		data.put("id", this.id);
		data.put("distance", this.d);
		data.put("label", this.name);
		entry.put("data", data);
		return entry;
	}
		

}
