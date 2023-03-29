package miu.cs544.badgemembershipsystem.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {
    public Error error;
    public ErrorResponse(String message) {
        this.error = new Error(message);
    }
}
