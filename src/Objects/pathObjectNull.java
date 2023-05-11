package Objects;

public class pathObjectNull implements pathObjectIF {
    private int id;
    private String path;

    /**
     * Consturctor for path object
     * @param id path number in the order it was uploaded
     * @param path path name on local device
     */
    public pathObjectNull(int id, String path){

        this.id = 0;
        this.path = null;
    }

    //setter and getter methods
    public void setID(int id){
        this.id = 0;
    }

    public int getID(){
        return 0;
    }

    public void setpath(String path){
        this.path = null;
    }

    public String getPath(){
        return null;
    }
}
