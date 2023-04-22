package UI_Formatter.NotfiyObserver;

import javax.swing.JFrame;

public class clearedObserver implements notifObserver{
    private JFrame frame;
    
    public clearedObserver(JFrame frame) {
        this.frame = frame;
    }
    
    public void update() {
        notification.showNotificationPopup(frame, "Successfully cleared files", true);
    }
}
