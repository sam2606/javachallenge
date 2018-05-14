package com.demo.util;

import com.demo.facade.GNode;
import com.demo.impl.GraphNodePath;

import java.util.*;

public class GraphUtil {

    public static List<GNode> walkGraph(GNode node) {
        List traversedNodes = new ArrayList<>();
        Map<GNode, Boolean> visited = new HashMap<>();
        Queue<GNode> queue = new LinkedList<GNode>();

        visited.putIfAbsent(node, true);
        queue.add(node);

        while (queue.size() != 0)
        {
            node = queue.poll();
            traversedNodes.add(node);
            if (node != null
                    && node.getChildren() != null
                    && node.getChildren().length > 0 ) {

                for (GNode childNode : node.getChildren()) {
                    if (visited.putIfAbsent(childNode, true) == null) {
                        queue.add(childNode);
                    }
                }
            }
        }

        return traversedNodes;
    }

    public static ArrayList paths(GNode node) {
        final LinkedList<GraphNodePath> nodes = new LinkedList<GraphNodePath>();
        nodes.add(new GraphNodePath(node, new ArrayList<GNode>()));

        final ArrayList<ArrayList<GNode>> paths = new ArrayList<ArrayList<GNode>>();

        while (!nodes.isEmpty()) {
            GraphNodePath n = nodes.removeFirst();
            n.getPath().add(n.getNode());

            if (n.getNode().getChildren().length == 0) {
                paths.add(n.getPath());
            } else {
                Arrays.stream(n.getNode().getChildren()).forEach(child ->
                    nodes.add(new GraphNodePath(child, new ArrayList<GNode>(n.getPath()))));
            }
        }
        return paths;
    }
}
