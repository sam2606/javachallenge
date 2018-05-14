package com.demo.impl;

import com.demo.facade.GNode;

import java.util.ArrayList;

public class GraphNodePath {

    GNode node;
    ArrayList<GNode> path;

    public GraphNodePath(GNode node, ArrayList<GNode> path) {
        this.node = node;
        this.path = path;
    }

    public GNode getNode() {
        return node;
    }

    public void setNode(GNode node) {
        this.node = node;
    }

    public ArrayList<GNode> getPath() {
        return path;
    }

    public void setPath(ArrayList<GNode> path) {
        this.path = path;
    }
}
