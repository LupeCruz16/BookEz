package UI_Formatter.NotfiyObserver;

import javax.swing.JFrame;

public class allDeletedObserver implements notifObserver{
    private JFrame frame;
    
    public allDeletedObserver(JFrame frame) {
        this.frame = frame;
    }
    
    public void update() {
        notification.showNotificationPopup(frame, "All files have been deleted", true);
    }
}
