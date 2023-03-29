package miu.cs544.badgemembershipsystem.aspect.exceptionHandling;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message){
        super(message);
    }
}
