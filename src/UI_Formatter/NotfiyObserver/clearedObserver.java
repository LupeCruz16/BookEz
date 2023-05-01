package UI_Formatter.NotfiyObserver;

import javax.swing.JFrame;

import Managers.ROIManager;
import contentPanels.roiPanel;
import contentPanels.uploadPanel;

public class clearedObserver implements notifObserver{
    private JFrame frame;
    
    public clearedObserver(JFrame frame) {
        this.frame = frame;
    }
    
    public void update() {
        uploadPanel.getTable().clear();
        notification.showNotificationPopup(frame, "Successfully cleared files", true);
        uploadPanel.changeToNoFiles();
        roiPanel.changeToNoFiles();
        ROIManager.resetID();//resetting identifyer
    }
}
