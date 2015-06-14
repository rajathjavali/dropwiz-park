import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import sun.security.provider.certpath.Vertex;

import java.util.HashMap;

public class SampleProg {

    public static void main(String[] args) {

OrientGraph graph=new OrientGraph("remote","","");
        graph.createVertexType("Car");
        graph.createVertexType("Slot");


    }


}
