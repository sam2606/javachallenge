package com.demo.impl;

import com.demo.facade.GNode;

import java.util.ArrayList;
import java.util.List;

public class GraphNode implements GNode {

    private String name;
    private GraphNode[] children;

    public void setChildren(GraphNode[] children) {
        this.children = children;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GraphNode(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public GNode[] getChildren() {
        if (children == null) {
            return new GNode[0];
        }
        return children;
    }
}
