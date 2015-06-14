package com.parking;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;

public class DBConn {

    public static OrientGraph getGraph() {
        OrientGraph graph = new OrientGraph("remote:localhost/park", "admin", "admin");
        return graph;
    }
}
