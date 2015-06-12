package depedency;

import java.util.Collection;
import java.util.HashMap;


public class GraphNode {
    private String name;
    private HashMap<String, GraphNode> dependencies; //this node depends on blah
    private HashMap<String, GraphNode> dependents;   //blah depends on this node
    
    public GraphNode(String nodeName) {
        this.name = nodeName;
        this.dependencies = new HashMap<String, GraphNode>();
        this.dependents = new HashMap<String, GraphNode>();
    }

    public String getName() {
        return name;
    }
    
    public Collection<GraphNode> getDependencies() {
        return dependencies.values();
    }
 
    public Collection<GraphNode> getDependents() {
        return dependents.values();
    }
    
    public synchronized void addDependency(GraphNode dep) {
        if ((!name.equals(dep.getName())) && 
                dependencies.containsKey(dep.getName())) {
                this.dependencies.put(dep.getName(), dep);
        }
    }
    
    public synchronized void addDependent(GraphNode dep) {
        if ((!name.equals(dep.getName())) && 
                dependents.containsKey(dep.getName())) {
                this.dependents.put(dep.getName(), dep);
        }
    }
}
