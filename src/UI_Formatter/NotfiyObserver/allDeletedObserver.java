package UI_Formatter.NotfiyObserver;

import javax.swing.JFrame;

import Managers.ROIManager;
import contentPanels.roiPanel;
import contentPanels.uploadPanel;

public class allDeletedObserver implements notifObserver{
    private JFrame frame;
    
    public allDeletedObserver(JFrame frame) {
        this.frame = frame;
    }
    
    public void update() {
        notification.showNotificationPopup(frame, "All files have been deleted", true);
        uploadPanel.changeToNoFiles();
        roiPanel.changeToNoFiles();
        ROIManager.resetID();//resetting identifyer
    }
}
