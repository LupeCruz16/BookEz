package Objects;

public class loginObject {
    private String message;
    private Boolean success;

    public loginObject(Boolean success, String message){
        this.message = message;
        this.success = success;
    }

    public loginObject(Boolean success){
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
