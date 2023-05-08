package Tables;

import java.awt.Dimension;
import javax.swing.JTable;

public class totalsTable {
    private static totalsTableWriter t = new totalsTableWriter();//creating an instance of table writer

    /**
     * Creating the table and storing it within a JTable to be returned and used within the panel display
     * @return JTable object
     */
    public JTable getTable() {
        JTable table = new JTable(t);

        table.setPreferredScrollableViewportSize(new Dimension(940, 20));//setting table size
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//enabling automatic resizing of columns

        return table;
    }

    /**
     * Returning the table writer instance used for the abstract table
     * @return returning table writer
     */
    public static totalsTableWriter returnWriter() {
        return t;
    }
}