package depedency;

import java.util.HashMap;
import java.util.List;

public class CmdList extends Command {
    public CmdList(List<String> input, HashMap<String, GraphNode> dependencyGraph,
            HashMap<String, GraphNode> installedNodes) {
        super(input, dependencyGraph, installedNodes);
    }

    @Override
    public void perform() {
        for (GraphNode node : this.installedNodes.values()) {
            System.out.println("\t" + node.getName());
        }
    }

}
