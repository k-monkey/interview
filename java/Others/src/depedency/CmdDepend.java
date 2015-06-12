package depedency;

import java.util.List;

public class CmdDepend extends Command {

    public CmdDepend(List<String> input) {
        super(input);
    }

    @Override
    public void perform() {
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

}
