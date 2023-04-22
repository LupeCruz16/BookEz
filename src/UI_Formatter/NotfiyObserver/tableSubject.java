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
        //create a copy of the observers list
        ArrayList<notifObserver> observersCopy = (ArrayList<notifObserver>) observers.clone();

        //notify all observers in the copy
        for (notifObserver o : observersCopy) {
            o.update();
        }

        //remove all observers from the original list
        observers.removeAll(observersCopy);
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
