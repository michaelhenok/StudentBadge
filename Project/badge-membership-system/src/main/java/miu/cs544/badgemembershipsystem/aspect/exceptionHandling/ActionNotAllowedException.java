package miu.cs544.badgemembershipsystem.aspect.exceptionHandling;

public class ActionNotAllowedException extends RuntimeException {
    public ActionNotAllowedException(String message) {
        super(message);
    }

}
