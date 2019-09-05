import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * This represents performs a graph search
 *
 * @author Dan Sun
 * @author version 1.0
 */
public class GraphSearch {

	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using General Graph Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be visited.
	 *
	 * The structure(struct) passed in is an empty structure may behave as a Stack or Queue and the
	 * correspondingly search function should execute DFS(Stack) or BFS(Queue) on the graph.
	 *
	 * @param start
	 * @param struct
	 * @param adjList
	 * @param goal
	 * @return true if path exists false otherwise
	 */
	public static <T> boolean generalGraphSearch(T start, Structure<T> struct, Map<T, List<T>> adjList, T goal) {
		if (start == null || goal == null) {
			throw new IllegalArgumentException();
		}
		if (!adjList.keySet().contains(start) || !adjList.keySet().contains(goal)) {
			System.out.println("Start or Goal is not a node in the adjacency list!");
			return false;
		}
		if (start.equals(goal)) {
			return true;
		}
		Map<T, Boolean> visited = new HashMap<T, Boolean>();
		for (T item: adjList.keySet()) {
			visited.put(item, false);
		}
		visited.put(start, true);
		struct.add(start);
		
		while (!struct.isEmpty()) {

			T current = struct.remove();
			Iterator<T> it = adjList.get(current).iterator();
		
			while (it.hasNext()) {
				T neighbour = it.next();
				if (neighbour.equals(goal)) {
					return true;
				}
				if (!visited.get(neighbour)) {
					struct.add(neighbour);
					visited.put(neighbour, true);
				}
			}
		}
		return false;
	}
	
	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using Bredth First Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be visited.
	 *
	 * @param start
	 * @param adjList
	 * @param goal
	 * @return true if path exists false otherwise
	 */
	public static <T> boolean breadthFirstSearch(T start, Map<T, List<T>> adjList, T goal) {
		return generalGraphSearch(start, new StructureQueue<T>(), adjList, goal);
	}
	
	/**
	 * Searches the Graph passed in as an AdjcencyList(adjList) to find if a path exists from the start node to the goal node
	 * using Depth First Search.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be visited.
	 *
	 * @param start
	 * @param adjList
	 * @param goal
	 * @return true if path exists false otherwise
	 */
	public static <T> boolean depthFirstSearch(T start, Map<T, List<T>> adjList, T goal) {
		return generalGraphSearch(start, new StructureStack<T>(), adjList, goal);
	}
	
	/**
	 * Find the shortest distance between the start node and the goal node in the given a weighted graph
	 * in the form of an adjacency list where the edges only have positive weights
	 * Return the aforementioned shortest distance if there exists a path between the start and goal,-1
	 * otherwise.
	 *
	 * Assume the AdjacencyList contains adjacent nodes of each node in the order they should be visited.
	 * There are no negative edge weights in the graph.
	 *
	 * @param start
	 * @param adjList
	 * @param goal
	 * @return the shortest distance between the start and the goal node
	 */
	public static <T> int djikstraShortestPathAlgorithm(T start, Map<T, List<Pair<T, Integer>>> adjList, T goal) {
		if (start == null || goal == null) {
			throw new IllegalArgumentException();
		}
		if (!adjList.keySet().contains(start) || !adjList.keySet().contains(goal)) {
			System.out.println("Start or Goal is not a node in the adjacency list!");
			return -1;
		}
		if (start.equals(goal)) {
			return 0;
		}

		Comparator<Pair<T, Integer>> pairComparator = new Comparator<Pair<T, Integer>>() { 
			public int compare(Pair<T, Integer> node1, Pair<T, Integer> node2) {
				return node1.b - node2.b;
			}
		};
		PriorityQueue<Pair<T, Integer>> priorityQueue = new PriorityQueue<Pair<T, Integer>>(10, pairComparator);

		for (T node: adjList.keySet()) {
			if (node.equals(start)) {
				priorityQueue.add(new Pair<T, Integer>(node, 0));
			} else {
				priorityQueue.add(new Pair<T, Integer>(node, 2147483647));
			}
		}

		Set<T> visited = new HashSet<T>(10);
		Set<Pair<T, Integer>> results = new HashSet<Pair<T, Integer>>(10);

		while (priorityQueue.size() != 0) {
			
			Pair<T, Integer> current = priorityQueue.poll();

			List<Pair<T, Integer>> neighbours = adjList.get(current.a);
			for (Pair<T, Integer> neighbour: neighbours) {
				if (!visited.contains(neighbour.a)) {
					int distance1 = -1;
					int distance2 = -1;
					for (Pair<T, Integer> item: adjList.get(current.a)) {
						if (item.a.equals(neighbour.a)) {
							distance1 = item.b + current.b;
						}
					}
					Iterator<Pair<T, Integer>> it = priorityQueue.iterator();
					boolean found = false;
					while (it.hasNext() && !found) {
						Pair<T, Integer> next = it.next();
						if (next.a.equals(neighbour.a)) {
							distance2 = next.b;
							found = true;
							if (distance2 == -1 || distance2 > distance1) {
								priorityQueue.add(new Pair<T, Integer>(next.a, distance1));
								priorityQueue.remove(new Pair<T, Integer>(next.a, distance2));
							}
						}
					}
				} 
			}
			visited.add(current.a);
			results.add(current);
		}
		for (Pair<T, Integer> result: results) {
			if (result.a.equals(goal)) {
				return result.b;
			}
		}
		return -1;
	}
}