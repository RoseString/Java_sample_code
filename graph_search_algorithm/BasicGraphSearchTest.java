import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * A few simple tests for the GraphSearch homework (hw 9)
 *
 * @author Justin Buchanan
 */
public class BasicGraphSearchTest {
    Map<String, List<String>> adjList;
    
    Map<String, List<Pair<String, Integer>>> weightedGraph;
    
    @Before
    public void setUp() {
        adjList = new HashMap<>();
        
        LinkedList<String> aList = new LinkedList<>();
        aList.add("B");
        aList.add("D");
        aList.add("F");
        adjList.put("A", aList);
        
        LinkedList<String> bList = new LinkedList<>();
        bList.add("C");
        bList.add("D");
        adjList.put("B", bList);
        
        LinkedList<String> cList = new LinkedList<>();
        adjList.put("C", cList);
        
        LinkedList<String> dList = new LinkedList<>();
        adjList.put("D", dList);
        
        LinkedList<String> fList = new LinkedList<>();
        fList.add("G");
        adjList.put("F", fList);
        
        LinkedList<String> gList = new LinkedList<>();
        gList.add("H");
        adjList.put("G", gList);
        
        LinkedList<String> hList = new LinkedList<>();
        adjList.put("H", hList);
        
        
        // weighted graph
        ////////////////////////////////////////////////////////////////////////////////////
        
        weightedGraph = new HashMap<>();
        
        LinkedList<Pair<String, Integer>> aListWeighted = new LinkedList<>();
        aListWeighted.add(new Pair<String, Integer>("B", 3));
        aListWeighted.add(new Pair<String, Integer>("D", 2));
        aListWeighted.add(new Pair<String, Integer>("F", 1));
        weightedGraph.put("A", aListWeighted);
        
        LinkedList<Pair<String, Integer>> bListWeighted = new LinkedList<>();
        bListWeighted.add(new Pair<String, Integer>("C", 1));
        bListWeighted.add(new Pair<String, Integer>("D", 1));
        weightedGraph.put("B", bListWeighted);
        
        LinkedList<Pair<String, Integer>> cListWeighted = new LinkedList<>();
        weightedGraph.put("C", cListWeighted);
        
        LinkedList<Pair<String, Integer>> dListWeighted = new LinkedList<>();
        weightedGraph.put("D", dListWeighted);
        
        LinkedList<Pair<String, Integer>> fListWeighted = new LinkedList<>();
        fListWeighted.add(new Pair<String, Integer>("G", 2));
        weightedGraph.put("F", fListWeighted);
        
        LinkedList<Pair<String, Integer>> gListWeighted = new LinkedList<>();
        gListWeighted.add(new Pair<String, Integer>("H", 3));
        weightedGraph.put("G", gListWeighted);
        
        LinkedList<Pair<String, Integer>> hListWeighted = new LinkedList<>();
        weightedGraph.put("H", hListWeighted);
    }
    
    @Test
    public void testDepthFirstSearch() {
        assertTrue(GraphSearch.depthFirstSearch("A", adjList, "H"));
        assertFalse(GraphSearch.depthFirstSearch("A", adjList, "J"));
    }
    
    @Test
    public void testBreadthFirstSearch() {
        assertTrue(GraphSearch.breadthFirstSearch("A", adjList, "H"));
        assertFalse(GraphSearch.breadthFirstSearch("A", adjList, "J"));
    }
    
    @Test
    public void testDijkstras() {
        assertEquals(GraphSearch.djikstraShortestPathAlgorithm("A", weightedGraph, "H"), 6);
        assertEquals(GraphSearch.djikstraShortestPathAlgorithm("C", weightedGraph, "H"), -1);
        assertEquals(GraphSearch.djikstraShortestPathAlgorithm("A", weightedGraph, "D"), 2);
    }
    @After
    public void tearDown() {
        adjList = null;
    }
}