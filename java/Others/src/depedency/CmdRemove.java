package depedency;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CmdRemove extends Command {
    private static final int INDEX_PRIMARY_NODE = 0;
    
    public CmdRemove(List<String> input, HashMap<String, GraphNode> dependencyGraph,
            HashMap<String, GraphNode> installedNodes) {
        super(input, dependencyGraph, installedNodes);
    }

    @Override
    public void perform() {
            GraphNode node = getNodeByName(input.get(INDEX_PRIMARY_NODE));

            if (! isInstalled(node.getName())) {
                System.out.println("\t " + node.getName() + " does not exist");
                return;
            }
            
            Collection<GraphNode> deps = node.getDependents();
            if ( deps.size() > 0) {
                System.out.println("\t " + node.getName() + " is still needed");
                return;
            }
            
            this.installedNodes.remove(node.getName());
            System.out.println("\t Removing " + node.getName());
    }

}
