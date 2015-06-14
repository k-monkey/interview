package depedency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Command {
    protected List<String> input;
    protected Map<String, GraphNode> dependencyGraph;
    protected Map<String, GraphNode> installedNodes;
    
    public static enum COMMANDS {
        DEPEND, INSTALL, REMOVE, LIST, END 
    };
    
    public static Command getCommandByName(String name, List<String> input,
            HashMap<String, GraphNode> dependencyGraph,
            HashMap<String, GraphNode> installedNodes) throws Exception {
        switch (COMMANDS.valueOf(name)) {
        case DEPEND:
            return new CmdDepend(input, dependencyGraph, installedNodes);
        case INSTALL:
            return new CmdInstall(input, dependencyGraph, installedNodes);
        case REMOVE:
            return new CmdRemove(input, dependencyGraph, installedNodes);
        case LIST:
            return new CmdList(input, dependencyGraph, installedNodes);
        case END:
            return new CmdEnd(input, dependencyGraph, installedNodes);
        default:
            throw new Exception("Command not defined: " + name);
        }
    }
    
    public Command(List<String> input, HashMap<String, GraphNode> dependencyGraph,
            HashMap<String, GraphNode> installedNodes) {
        this.input = input;
        this.dependencyGraph = dependencyGraph;
        this.installedNodes = installedNodes;
    }

    protected boolean isInstalled(String nodeName) {
        return installedNodes.containsKey(nodeName);
    }

    protected GraphNode getNodeByName(String name) {
        if (dependencyGraph.containsKey(name))
            return dependencyGraph.get(name);
        else {
            GraphNode node = new GraphNode(name);
            dependencyGraph.put(name, node);
            return node;
        }
    }
    
    public abstract void perform();
}
