package space.commandf1.fastbuilder.api.exception;

/**
 * The exception that will throw if api not enabled
 * @since 1.0-SNAPSHOT
 * @author commandf1
 * */
public class APINotEnableException extends RuntimeException {
    public APINotEnableException() {
        super("API not enabled, Please check the config!");
    }
}
