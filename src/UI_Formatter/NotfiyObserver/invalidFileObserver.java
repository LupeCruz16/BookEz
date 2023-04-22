package UI_Formatter.NotfiyObserver;

import javax.swing.JFrame;

public class invalidFileObserver implements notifObserver{
    private JFrame frame;
    
    public invalidFileObserver(JFrame frame) {
        this.frame = frame;
    }
    
    public void update() {
        notification.showNotificationPopup(frame, "Invalid file format", false);
    }
    
}