package itu.jca.eval.test.coworking.api.builder;
import itu.jca.eval.test.coworking.api.results.ApiResponse;
import itu.jca.eval.test.coworking.enums.ResponseStatus;

public class ApiResponseBuilder {
    public static ApiResponse success(String message , Object data){
        if (message == null) {
            message = "success";
        }
        return new ApiResponse(ResponseStatus.SUCCESS,message,data,null);
    }

    public static ApiResponse error(ResponseStatus status,String message,Object errors){
        return new ApiResponse(status, message,null, errors);
    }
    public static ApiResponse error(ResponseStatus responseStatus,Exception exception){
        return error(responseStatus, exception.getMessage(), exception);
    }

    public static ApiResponse error400(String message,Object errors){
        return error(ResponseStatus.ERROR, message, errors);
    }
    public static ApiResponse error400(Exception error){
        return error(ResponseStatus.ERROR,error);
    }
    
    public static ApiResponse error500(String message,Object errors){
        return error(ResponseStatus.SERVER_ERROR, message, errors);
    }
    public static ApiResponse error500(Exception exception){
        return error(ResponseStatus.SERVER_ERROR,exception);
    }
}
