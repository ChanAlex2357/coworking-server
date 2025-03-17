package itu.jca.eval.test.coworking.api.results;

import java.util.List;

import itu.jca.eval.test.coworking.enums.ResponseStatus;
import lombok.Data;

@Data
public class ApiResponse {
    int status;
    String message;
    Object data;
    Object errors;

    public ApiResponse(ResponseStatus status,String message , Object data,Object errors){
        this(status.getStatus(),message,data,errors);
    }
    public ApiResponse(int status,String message , Object data,Object errors){
        setStatus(status);
        setMessage(message);
        setData(data);
        setErrors(errors);
    }
}
