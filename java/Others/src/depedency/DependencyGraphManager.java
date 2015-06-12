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
                List<String> inputTokens = validateLine(line);
                if ( inputTokens == null ) 
                    throw new Exception("Invalid input line: " + line);
                if (inputTokens.size() == 0) 
                    continue; //skip empty line
                
                Command cmd = Command.getCommandByName(inputTokens.get(0), 
                        inputTokens.subList(1, inputTokens.size()-1));
                cmd.perform();
            }
        }
        finally {
            if ( reader != null)
                reader.close();
            
            if (in != null)
                in.close();
        }
        
    }

    private boolean isInstalled(String nodeName) {
        return this.installedNodes.containsKey(nodeName);
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
