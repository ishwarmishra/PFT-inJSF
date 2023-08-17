package personalfinancetrackerinweb.api;


public class RestResponse {

    private String success;
    private int statusCode;
    private String message;
    private Object result;

    public RestResponse(String success, int statusCode, String message, Object result) {
        this.success=success;
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
        
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    
}