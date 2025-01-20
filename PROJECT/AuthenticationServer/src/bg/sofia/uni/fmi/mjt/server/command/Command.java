package bg.sofia.uni.fmi.mjt.server.command;

import bg.sofia.uni.fmi.mjt.server.exceptions.command.IncorrectCommandException;

public interface Command {
    String execute() throws IncorrectCommandException;

    String getName();

    String getData();
}
