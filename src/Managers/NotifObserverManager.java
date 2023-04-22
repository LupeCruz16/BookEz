package Managers;

import UI_Formatter.NotfiyObserver.tableSubject;
import UI_Formatter.NotfiyObserver.allDeletedObserver;
import UI_Formatter.NotfiyObserver.clearedObserver;
import UI_Formatter.NotfiyObserver.invalidFileObserver;

import javax.swing.JFrame;

public class NotifObserverManager {
    static tableSubject subject = new tableSubject();

    public static void allFilesDeleted(JFrame frame){
        allDeletedObserver observer = new allDeletedObserver(frame);
        subject.registerObserver(observer);

        subject.allFilesDeleted();
    }

    public static void clearedFiles(JFrame frame){
        clearedObserver observer = new clearedObserver(frame);
        subject.registerObserver(observer);

        subject.jTableCleared();
    }

    public static void invalidFiles(JFrame frame){
        invalidFileObserver observer = new invalidFileObserver(frame);
        subject.registerObserver(observer);

        subject.invalidFileUploaded();
    }
}
