package depedency;

import java.util.Collection;
import java.util.List;

public class CmdRemove extends Command {

    public CmdRemove(List<String> input) {
        super(input);
    }

    @Override
    public void perform() {
            GraphNode node = getNodeByName(inputTokens.get(1)); //get the primary node
            System.out.println(COMMANDS.REMOVE.toString() + " " + node.getName());

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
