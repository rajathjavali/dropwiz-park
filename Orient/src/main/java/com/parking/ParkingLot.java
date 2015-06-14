package com.parking;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertexIterator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.IteratorUtils;
import sun.security.provider.certpath.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ParkingLot {
    private int maxSlots;
    private int allocated;
    private OrientGraph graph;

    public ParkingLot(int i) {
        graph = DBConn.getGraph();
        maxSlots = i;
        allocated = 0;
    }

    public Slot park(String myCar) {
        Slot newSlot = null;
        String cmd0 = "select Count(*) from Car where name=" + myCar;

        for(OrientVertex v : (Iterable<OrientVertex>)graph.command(new OCommandSQL(cmd0)).execute())
        {
              v.getProperty("Count");
        }

//        Iterable<OrientVertex> iterable =  graph.command(new OCommandSQL(cmd0)).execute();
//
//        long count = ((OrientVertex) IteratorUtils.toList(iterable.iterator()).get(0)).getProperty("Count");
//        List list = IterableUtils.toList(iterable);

        if (maxSlots > allocated) {
            String cmd1 = "insert into Car content {\"name\": \"" + myCar + "\"}";
            graph.command(new OCommandSQL(cmd1)).execute();
            String cmd2 = "insert into Slot content {\"slotPos\": " + allocated + "}";
            graph.command(new OCommandSQL(cmd2)).execute();
            String cmd3 = "create EDGE OccupiedBy from (SELECT FROM Slot WHERE slotPos = \"" + allocated + "\") TO (SELECT FROM Car WHERE name = \"" + myCar + "\")";
            graph.command(new OCommandSQL(cmd3)).execute();
            newSlot = new Slot(myCar, allocated);
            allocated++;
        }
        return newSlot;
    }


    public List<Slot> getFilledSlots() {
        return null;
    }
}
