package depedency;

import java.util.HashMap;
import java.util.List;

public class CmdEnd extends Command {

    public CmdEnd(List<String> input, HashMap<String, GraphNode> dependencyGraph,
            HashMap<String, GraphNode> installedNodes) {
        super(input, dependencyGraph, installedNodes);
    }

    @Override
    public void perform() {
        //NOOP
    }

}
