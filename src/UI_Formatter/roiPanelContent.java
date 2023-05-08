package UI_Formatter;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

import Tables.roiTable;
import Tables.tableModification;
import Tables.totalsTable;
import Tables.roiHeaderRenderer;
import contentPanels.roiPanel;
import contentPanels.uploadPanel;
import Controller.controller;
import Tables.pdfExporter;
import Managers.NotifObserverManager;

public class roiPanelContent extends JPanel{

    private roiTable roi = new roiTable();
    private totalsTable totals  = new totalsTable();

    public roiPanelContent(){

        setLayout(new BorderLayout());
        setBackground(colorPalette.background);

        //topPanel panel houses the roi table options 
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        topPanel.setBackground(colorPalette.background);
        topPanel.setPreferredSize(new Dimension(800, 250));

        topPanel.add(optionsPanel(), BorderLayout.CENTER);

        //bottomPanel panel houses the tables
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(25, 20, 10, 20));

        bottomPanel.setBackground(colorPalette.background);
        bottomPanel.setPreferredSize(new Dimension(900, 525));

        //creating roiTables space
        JTable roiTable = roi.getTable();//obtaining the abstract table
        roiTable.getTableHeader().setDefaultRenderer(new roiHeaderRenderer());
        tableModification.roiColumnResizing(roiTable);
        tableModification.cellBackGroundColor(roiTable);
        JScrollPane roiTableScroll = new JScrollPane(roiTable);
        roiTableScroll.setBorder(tableModification.getTableBorder());
        roiTableScroll.setPreferredSize(new Dimension(900, 440));

        //creating roiTables space
        JTable totalsTable = totals.getTable();
        totalsTable.getTableHeader().setDefaultRenderer(new roiHeaderRenderer());
        //tableModification.roiColumnResizing(totalsTable);
        tableModification.cellBackGroundColor(totalsTable);
        JScrollPane totalsTableScroll = new JScrollPane(totalsTable);
        totalsTableScroll.setBorder(tableModification.getTableBorder());
        totalsTableScroll.setPreferredSize(new Dimension(900, 55));

        //adding elements into the bottomPanel
        bottomPanel.add(roiTableScroll, BorderLayout.NORTH);
        bottomPanel.add(totalsTableScroll, BorderLayout.SOUTH);

        //adding panels into the main panel
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel optionsPanel(){
        JPanel export = new JPanel(new FlowLayout(FlowLayout.LEFT));
        export.setBackground(colorPalette.background);
        export.setPreferredSize(new Dimension(250, 50));
        JLabel exportIcon = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-export-32.png"));
        JLabel exportText= new JLabel("Export ROI Table");
        exportText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        exportText.setForeground(colorPalette.light);
        export.add(exportIcon);
        export.add(exportText);
        //adding mouse listeners to the jlabels 
        exportIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    pdfExporter.createPDF(roi.getTable(), totals.getTable());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        exportText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    pdfExporter.createPDF(roi.getTable(), totals.getTable());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JPanel delete = new JPanel(new FlowLayout(FlowLayout.LEFT));
        delete.setBackground(colorPalette.background);
        delete.setPreferredSize(new Dimension(250, 50));
        JLabel deleteIcon = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-clear-symbol-32.png"));
        JLabel deleteText= new JLabel("Delete Selected Files");
        deleteText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        deleteText.setForeground(colorPalette.light);
        delete.add(deleteIcon);
        delete.add(deleteText);
        deleteIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roiPanel.getTable().deleteRows();
                checkFiles();
            }
        });
        deleteText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roiPanel.getTable().deleteRows();
                checkFiles();
            }
        });

        JPanel clear = new JPanel(new FlowLayout(FlowLayout.LEFT));
        clear.setBackground(colorPalette.background);
        clear.setPreferredSize(new Dimension(250, 50));
        JLabel clearIcon = new JLabel(new ImageIcon("src/UI_Formatter/Icons/icons8-empty-trash-32 (1).png"));
        JLabel clearText= new JLabel("Clear All Files");
        clearText.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        clearText.setForeground(colorPalette.light);
        clear.add(clearIcon);
        clear.add(clearText);

        clearIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NotifObserverManager.clearedFiles(controller.getFrame()); 
            }
        });
        clearText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NotifObserverManager.clearedFiles(controller.getFrame());
            }
        });

        //adding the elements into the topPanel
        JPanel options = new JPanel();
        options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
        options.setBackground(colorPalette.background);
        options.add(export);
        options.add(clear);
        options.add(delete);

        return options;
    }

    /**
     * Function is only used with the delete option. Checks if all files were deleted and acts accordingly to give user feedback
     */
    private void checkFiles(){
        //checking if all files were deleted
        if (uploadPanel.getTable().empty()){
            NotifObserverManager.allFilesDeleted(controller.getFrame());
        }
    }
}
