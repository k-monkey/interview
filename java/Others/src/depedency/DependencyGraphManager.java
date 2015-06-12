package depedency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class DependencyGraphManager {
    //TODO: abstract these commands into Action class!
    public static enum COMMANDS {
        DEPEND, INSTALL, REMOVE, LIST, END 
    };
    
    //make this depGraph shared among threads. for scalability
    private static HashMap<String, GraphNode> dependencyGraph = 
            new HashMap<String, GraphNode>();
    
    private HashMap<String, GraphNode> installedNodes;
    
    public DependencyGraphManager() {
        installedNodes = new HashMap<String, GraphNode>();
    }
    
    public void readInputFile(String fileName) throws Exception {
        File file = new File(fileName);
        if (! file.exists()) {
            throw new Exception("file " + fileName + "does not exist!");
        }

        FileInputStream in = new FileInputStream(file);
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(in));
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                List<String> inputTokens = validateLine(line);
                if ( inputTokens == null ) 
                    throw new Exception("Invalid input line: " + line);
                if (inputTokens.size() == 0) 
                    continue; //skip empty line
                
                //TODO: We should abstract each command into its own class
                //e.g. DEPEND.java etc. and implement performe() method.
                //for limited time, this is not done.
                //a big if-else clause is ugly!
                if (COMMANDS.DEPEND.toString().equals(inputTokens.get(0))) {
                    doDepend(inputTokens);
                }
                else if (COMMANDS.REMOVE.toString().equals(inputTokens.get(0))) {
                    doRemove(inputTokens);
                }
                else if (COMMANDS.LIST.toString().equals(inputTokens.get(0))) {
                    doList(inputTokens);
                }
                else if (COMMANDS.INSTALL.toString().equals(inputTokens.get(0))) {
                    doInstall(inputTokens);
                }
                else if (COMMANDS.END.toString().equals(inputTokens.get(0))) {
                    System.out.println(COMMANDS.END.toString());
                    break;
                }
            }
        }
        finally {
            if ( reader != null)
                reader.close();
            
            if (in != null)
                in.close();
        }
        
    }

    private void doInstall(List<String> inputTokens) {
        GraphNode node = getNodeByName(inputTokens.get(1)); //get the primary node
        System.out.println(COMMANDS.INSTALL.toString() + " " + node.getName());
        recursivelyInstall(node);
    }
    
    private boolean isInstalled(String nodeName) {
        return this.installedNodes.containsKey(nodeName);
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

    private GraphNode getNodeByName(String name) {
        if (DependencyGraphManager.dependencyGraph.containsKey(name))
            return DependencyGraphManager.dependencyGraph.get(name);
        else {
            GraphNode node = new GraphNode(name);
            DependencyGraphManager.dependencyGraph.put(name, node);
            return node;
        }
    }
    
    private void doDepend(List<String> inputTokens) {
        System.out.print(COMMANDS.DEPEND.toString() + " ");
        
        //protect concurrent update to the dep graph
        //TODO: i try to put in simple synch() block to protect concurrent
        //access to the shared dependencyGraph object, and its nodes.
        //this surely needs be better revised. use Lock-free type like 
        //AtomicInteger maybe. 
        synchronized (DependencyGraphManager.dependencyGraph) {
            GraphNode node = getNodeByName(inputTokens.get(1)); //get the primary node
            System.out.print(node.getName() + " ");
            
            for (int i=2; i<inputTokens.size(); i++) {
                GraphNode dep = getNodeByName(inputTokens.get(i));
                node.addDependency(dep);
                dep.addDependent(node);
                System.out.print(dep.getName() + " ");
            }
        }
        System.out.println();
    }

    private void doRemove(List<String> inputTokens) {
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
    
    private void doList(List<String> inputTokens) {
        System.out.println(COMMANDS.LIST.toString());
        for (GraphNode node : this.installedNodes.values()) {
            System.out.println("\t" + node.getName());
        }
    }

    private List<String> validateLine(String line) {
        LinkedList<String> result = new LinkedList<String>();
        StringTokenizer st = new StringTokenizer(line.trim());
        
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        
        //TODO: supposed to do more validation here.
        return result;
    }
}
