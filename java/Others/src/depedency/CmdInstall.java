package depedency;

import java.util.Collection;
import java.util.List;

public class CmdInstall extends Command {

    public CmdInstall(List<String> input) {
        super(input);
    }

    @Override
    public void perform() {
            GraphNode node = getNodeByName(inputTokens.get(1)); //get the primary node
            System.out.println(COMMANDS.INSTALL.toString() + " " + node.getName());
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
