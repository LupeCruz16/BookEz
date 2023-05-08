package Objects;

public class LoginObject {
    private String message;
    private Boolean success;

    public LoginObject(Boolean success, String message){
        this.message = message;
        this.success = success;
    }

    public void loginObject(Boolean success){
        this.message = "";
        this.success = success;
    }

    public String getMessage(){
        return this.message;
    }

    public boolean isSuccess(){
        return this.success;
    }
}
    
