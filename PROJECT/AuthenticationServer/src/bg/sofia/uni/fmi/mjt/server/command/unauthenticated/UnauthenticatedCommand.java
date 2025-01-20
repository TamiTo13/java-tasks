package bg.sofia.uni.fmi.mjt.server.command.unauthenticated;

import bg.sofia.uni.fmi.mjt.server.command.Command;

public abstract class UnauthenticatedCommand implements Command {

    protected String username;
    protected String password;

    protected UnauthenticatedCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
