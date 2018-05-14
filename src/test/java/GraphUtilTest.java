import com.demo.facade.GNode;
import com.demo.impl.GraphNode;
import com.demo.util.GraphUtil;

import java.util.ArrayList;

public class GraphUtilTest {

    public static  void main(String[] args) {

        GraphNode nodeA = new GraphNode("A");
        GraphNode nodeB = new GraphNode("B");
        GraphNode nodeC = new GraphNode("C");
        GraphNode nodeD = new GraphNode("D");
        GraphNode nodeE = new GraphNode("E");
        GraphNode nodeF = new GraphNode("F");
        GraphNode nodeG = new GraphNode("G");
        GraphNode nodeH = new GraphNode("H");
        GraphNode nodeI = new GraphNode("I");
        GraphNode nodeJ = new GraphNode("J");

        GraphNode[] nodeChildren = new GraphNode[]{nodeJ};
        nodeD.setChildren(nodeChildren);

        nodeChildren = new GraphNode[]{nodeD};
        nodeI.setChildren(nodeChildren);

        nodeChildren = new GraphNode[]{nodeI};
        nodeH.setChildren(nodeChildren);

        nodeChildren = new GraphNode[]{nodeH};
        nodeG.setChildren(nodeChildren);

        nodeChildren = new GraphNode[]{nodeG};
        nodeC.setChildren(nodeChildren);

        nodeChildren = new GraphNode[]{nodeC};
        nodeF.setChildren(nodeChildren);

        nodeChildren = new GraphNode[]{nodeF};
        nodeE.setChildren(nodeChildren);

        nodeChildren = new GraphNode[]{nodeE};
        nodeB.setChildren(nodeChildren);

        nodeChildren = new GraphNode[]{nodeB};
        nodeA.setChildren(nodeChildren);

        GraphUtil.walkGraph(nodeA).forEach(node -> System.out.print(node.getName() + " "));
        ArrayList<ArrayList<GraphNode>> paths = GraphUtil.paths(nodeA);

        for (int i=0; i< paths.size(); i++) {
            ArrayList<GraphNode> interimPaths = paths.get(i);
            System.out.println("-----------");
            for (GraphNode node : interimPaths) {
                System.out.println(node.getName());
            }
            System.out.println("-----------");
        }
    }
}
