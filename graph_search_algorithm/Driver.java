import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Driver {

	public static void main (String[] args) {

		Map<String, List<Pair<String, Integer>>> adjacencyList = new HashMap<String, List<Pair<String, Integer>>>();

		List<Pair<String, Integer>> aList = new LinkedList<Pair<String, Integer>>();
		List<Pair<String, Integer>> bList = new LinkedList<Pair<String, Integer>>();
		List<Pair<String, Integer>> cList = new LinkedList<Pair<String, Integer>>();
		List<Pair<String, Integer>> dList = new LinkedList<Pair<String, Integer>>();
		List<Pair<String, Integer>> eList = new LinkedList<Pair<String, Integer>>();
		List<Pair<String, Integer>> fList = new LinkedList<Pair<String, Integer>>();

		aList.add(new Pair<String, Integer>("B", 7));
		aList.add(new Pair<String, Integer>("C", 9));
		aList.add(new Pair<String, Integer>("F", 14));

		bList.add(new Pair<String, Integer>("A", 7));
		bList.add(new Pair<String, Integer>("C", 10));
		bList.add(new Pair<String, Integer>("D", 15));

		cList.add(new Pair<String, Integer>("A", 9));
		cList.add(new Pair<String, Integer>("B", 10));
		cList.add(new Pair<String, Integer>("D", 11));
		cList.add(new Pair<String, Integer>("F", 2));
		
		dList.add(new Pair<String, Integer>("B", 15));
		dList.add(new Pair<String, Integer>("C", 11));
		dList.add(new Pair<String, Integer>("E", 6));

		eList.add(new Pair<String, Integer>("D", 6));
		eList.add(new Pair<String, Integer>("F", 9));

		fList.add(new Pair<String, Integer>("A", 14));
		fList.add(new Pair<String, Integer>("C", 2));
		fList.add(new Pair<String, Integer>("E", 9));

		adjacencyList.put("A", aList);
		adjacencyList.put("B", bList);
		adjacencyList.put("C", cList);
		adjacencyList.put("D", dList);
		adjacencyList.put("E", eList);
		adjacencyList.put("F", fList);

		int result = GraphSearch.djikstraShortestPathAlgorithm("A", adjacencyList, "Y");
		System.out.println(result);
		result = GraphSearch.djikstraShortestPathAlgorithm("Y", adjacencyList, "C");
		System.out.println(result);
		result = GraphSearch.djikstraShortestPathAlgorithm("A", adjacencyList, "D");
		System.out.println(result);
		result = GraphSearch.djikstraShortestPathAlgorithm("A", adjacencyList, "E");
		System.out.println(result);
		result = GraphSearch.djikstraShortestPathAlgorithm("A", adjacencyList, "F");
		System.out.println(result);
	} 
}