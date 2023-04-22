package UI_Formatter.NotfiyObserver;

/*
 * Defines the methods that the observer can use to register themsevles to revieve updates
 * and for the sobject to notify the observers 
 */
public interface subject {
    public void registerObserver(notifObserver o);
    public void removeObserver(notifObserver o);
    public void notifyObservers();
}
