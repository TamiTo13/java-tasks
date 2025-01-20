package bg.sofia.uni.fmi.mjt.server.executor.parser;

import bg.sofia.uni.fmi.mjt.server.command.Command;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.AuthenticatedLoginCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.LogoutCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.ResetPasswordCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.UpdateUserCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand.AddAdminUserCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand.DeleteUserCommand;
import bg.sofia.uni.fmi.mjt.server.command.authenticated.admincommand.RemoveAdminCommand;
import bg.sofia.uni.fmi.mjt.server.command.unauthenticated.LoginCommand;
import bg.sofia.uni.fmi.mjt.server.command.unauthenticated.RegisterCommand;
import bg.sofia.uni.fmi.mjt.server.exceptions.WrongSyntaxException;

import java.util.Map;
import java.util.function.Predicate;

public abstract class CommandParser {

    public static final int ADD_ADMIN_COMMAND = 1;
    public static final int DELETE_USER_COMMAND = 2;
    public static final int REMOVE_ADMIN_COMMAND = 3;
    public static final int AUTHENTICATED_LOGIN_COMMAND = 4;
    public static final int LOGOUT_COMMAND = 5;
    public static final int RESET_PASSWORD_COMMAND = 6;
    public static final int UPDATE_USER_COMMAND = 7;
    public static final int LOGIN_COMMAND = 8;
    public static final int REGISTER_COMMAND = 9;

    private static final Map<Integer, Predicate<String>> COMMAND_CHECK = Map.of(
            ADD_ADMIN_COMMAND, AddAdminUserCommand::checkFormat,
            DELETE_USER_COMMAND, DeleteUserCommand::checkFormat,
            REMOVE_ADMIN_COMMAND, RemoveAdminCommand::checkFormat,
            AUTHENTICATED_LOGIN_COMMAND, AuthenticatedLoginCommand::checkFormat,
            LOGOUT_COMMAND, LogoutCommand::checkFormat,
            RESET_PASSWORD_COMMAND, ResetPasswordCommand::checkFormat,
            UPDATE_USER_COMMAND, UpdateUserCommand::checkFormat,
            LOGIN_COMMAND, LoginCommand::checkFormat,
            REGISTER_COMMAND, RegisterCommand::checkFormat
            // Add more predicates for other commands if needed
    );

    public static Command parseCommand(String line) throws WrongSyntaxException {

        for (Map.Entry<Integer, Predicate<String>> entry :
                COMMAND_CHECK.entrySet()) {

            if (entry.getValue().test(line)) {
                return Distributor.passLine(line, entry.getKey());
            }
        }
        throw new WrongSyntaxException("This is not a valid command");
    }
}
