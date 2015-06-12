package depedency;

import java.util.List;

public abstract class Command {
    private List<String> input;
    
    public static enum COMMANDS {
        DEPEND, INSTALL, REMOVE, LIST, END 
    };
    
    public static Command getCommandByName(String name, List<String> input) throws Exception {
        switch (COMMANDS.valueOf(name)) {
        case DEPEND:
            return new CmdDepend(input);
        case INSTALL:
            return new CmdInstall(input);
        case REMOVE:
            return new CmdRemove(input);
        case LIST:
            return new CmdList(input);
        case END:
            return new CmdEnd(input);
        default:
            throw new Exception("Command not defined: " + name);
        }
    }
    
    public Command(List<String> input) {
        this.input = input;
    }
    
    public abstract void perform();
}
