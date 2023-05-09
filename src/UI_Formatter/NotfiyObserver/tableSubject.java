package UI_Formatter.NotfiyObserver;
import java.util.ArrayList;

/**
 * Implements concrete subject class
 * Class maintains a list of observers and notifies them when there is a change 
 */
public class tableSubject implements subject{
    private ArrayList<notifObserver> observers;

    public tableSubject() {
        observers = new ArrayList<notifObserver>();
    }

    public void registerObserver(notifObserver o) {
        observers.add(o);
    }

    public void removeObserver(notifObserver o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        //creating a seperate list to remove the observers after they have been called 
        ArrayList<notifObserver> observersToRemove = new ArrayList<>();

        for (notifObserver o : observers) {
            o.update();
            observersToRemove.add(o);
        }

        observers.removeAll(observersToRemove);
    }

    public void allFilesDeleted() {
        notifyObservers();
    }

    public void jTableCleared() {
        notifyObservers();
    }

    public void invalidFileUploaded(){
        notifyObservers();
    }
}
