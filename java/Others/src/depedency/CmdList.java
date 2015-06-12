package depedency;

import java.util.List;

public class CmdList extends Command {

    public CmdList(List<String> input) {
        super(input);
    }

    @Override
    public void perform() {
        System.out.println(COMMANDS.LIST.toString());
        for (GraphNode node : this.installedNodes.values()) {
            System.out.println("\t" + node.getName());
        }
    }

}
