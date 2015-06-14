package depedency;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CmdInstall extends Command {
    private static final int INDEX_PRIMARY_NODE = 0;
    
    public CmdInstall(List<String> input, HashMap<String, GraphNode> dependencyGraph,
            HashMap<String, GraphNode> installedNodes) {
        super(input, dependencyGraph, installedNodes);
    }

    @Override
    public void perform() {
            GraphNode node = getNodeByName(input.get(INDEX_PRIMARY_NODE));
            recursivelyInstall(node);
    }

    /**
     * install the node and its dependencies
     * 
     * @param node
     */
    private void recursivelyInstall(GraphNode node) {
        if ( isInstalled(node.getName())) {
            System.out.println("\t " + node.getName() + " is already installed.");
            return;
        }
        
        Collection<GraphNode> deps = node.getDependencies();
        for (GraphNode dep : deps) {
            recursivelyInstall(dep);
        }
        
        this.installedNodes.put(node.getName(), node);
        System.out.println("\t Installing " + node.getName());
    }
}
