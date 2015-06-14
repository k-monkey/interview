package depedency;

import java.util.HashMap;
import java.util.List;

public class CmdDepend extends Command {
    private static final int INDEX_PRIMARY_NODE = 0;

    public CmdDepend(List<String> input, HashMap<String, GraphNode> dependencyGraph,
            HashMap<String, GraphNode> installedNodes) {
        super(input, dependencyGraph, installedNodes);
    }

    @Override
    public void perform() {
            //protect concurrent update to the dep graph
            //TODO: i try to put in simple synch() block to protect concurrent
            //access to the shared dependencyGraph object, and its nodes.
            //this surely needs be better revised. use Lock-free type like 
            //AtomicInteger maybe. 
            synchronized (dependencyGraph) {
                GraphNode node = getNodeByName(input.get(INDEX_PRIMARY_NODE)); //get the primary node
                
                for (int i=INDEX_PRIMARY_NODE+1; i<input.size(); i++) {
                    GraphNode dep = getNodeByName(input.get(i));
                    node.addDependency(dep);
                    dep.addDependent(node);
                }
            }
    }

}
