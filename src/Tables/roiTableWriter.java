package Tables;

import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.table.AbstractTableModel;
import Objects.orderObject;

public class roiTableWriter extends AbstractTableModel{
    
    private final String[] columnNames = {"Order Number", "Order Total", "Item Sold Price", 
    "Charged Shipping", "Shipping Paid", "Taxes", "Profit", "Check Box"};

    private Object[][]data = {};
    private totalsTableWriter t = totalsTable.returnWriter();

    @Override
    public int getColumnCount(){
        return columnNames.length;
    }

    @Override
    public int getRowCount(){
        return data.length; 
    }

    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        if(col == 0){
            return data[row][col];
        } else {
            if (data[row][col] instanceof Double) {
                double value = (double) data[row][col];
                return new DecimalFormat("#0.00").format(value);
            } else {
                return data[row][col];
            }
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col){
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    /**
     * Adding a row to the table from an orders collected information
     * @param order Order whos information is to be added
     */
    public void addRow(orderObject order) {
        int rowCount = getRowCount();//obtaining current row count
        Object[][] newData = new Object[data.length + 1][];//adding new data
        System.arraycopy(data, 0, newData, 0, rowCount);

        //defining the new data to be added
        newData[rowCount] = new Object[]{order.getOrderNum(), order.getTotal(), order.getSoldPrice(),
                                        order.getShipPaid(), order.getShipCost(), order.getTax(), 
                                        order.getProfit(), false};
        data = newData;//setting data to new data

        fireTableDataChanged();//updating table
    }

    /**
     * Deletes all selected rows from the check box column
     */
    public void deleteSelectedRows(){

        pathTableWriter p = pathTable.returnWriter();//creating a path table writer variable 

        for(int i = getRowCount() - 1; i >= 0; i--){//itterates through all rows in table
            Object checked = getValueAt(i, finalTableValues.roiCheckCol);//obtains boolean value from check box column
            if(checked instanceof Boolean){//if checked for deletion
                boolean delete = (Boolean) checked;
                if(delete){
                    deleteRow(i);//delete the row
                    p.deleteRow(i);//deleting the row from the path table
                    
                }
            }
        }

    }

    /**
     * Will delete a row from the table 
     * @param rowInd Row to be deleted
     */
    public void deleteRow(int rowInd){
        int newRows = data.length - 1;//intialize a new row counter
        Object[][] newData = new Object[newRows][];//create new data
        int newRowInd = 0;//set start to 0

        for(int i = 0; i < data.length; i++){//iterate through current rows
            if(i != rowInd){//if not the one to be deleted
                newData[newRowInd++] = data[i];//add into new data
            } else {
                //remove the values of the row from the totals
                t.removeOrder(getRowOrderObject(i));
            }
        }

        data = newData;//set as data
        fireTableDataChanged();//update table
    }

    /**
     * Will use the row to be deleted to create a new order object and return it to update the totalsTableWriter
     * @param row key to rest of information
     * @return order object
     */
    public orderObject getRowOrderObject(int row){
        // get the values of the row to be deleted
        String orderNum = (String) getValueAt(row, 0);
        Double total = Double.valueOf(getValueAt(row, 1).toString());
        Double soldPrice = Double.valueOf(getValueAt(row, 2).toString());
        Double shipPaid = Double.valueOf(getValueAt(row, 4).toString());
        Double shipCost = Double.valueOf(getValueAt(row, 3).toString());
        Double tax = Double.valueOf(getValueAt(row, 5).toString());
        Double profit = Double.valueOf(getValueAt(row, 6).toString());
    
        // create an order object from the values
        orderObject order = new orderObject(orderNum, total, soldPrice, shipPaid, shipCost, tax, profit);
                
        return order;
    }

    /**
     * Will sort the table based on the entered in colum and will be sorted in ascending or descending order based on the boolean
     * @param columnIndex column to be sorted
     * @param ascending method of sort 
     */
    public void sortTable(int columnIndex, boolean ascending) {

        int rowCount = data.length;//obtaining current amount of rows 
        Integer[] sortIndices = new Integer[rowCount];//creating an array to store values of said column
        int sortInd = 0;//finding out the length of all rows minus the final one 

        for (int i = 0; i < rowCount; i++) {//looping through the rows 
            sortIndices[sortInd++] = i;//add it to the array
        }

        //sorting the array of row values 
        Arrays.sort(sortIndices, (a, b) -> {
            Object valueA = data[a][columnIndex];
            Object valueB = data[b][columnIndex];
            //checking if the values are doubles and compare them if they are
            if (valueA instanceof Double && valueB instanceof Double) {
                if(ascending){//sorting in ascending order
                    return Double.compare((Double) valueA, (Double) valueB);
                } else {//sorting in descending order
                    return ((Double) valueB).compareTo((Double) valueA);
                }
            } else {//returning 0 if the values are not doubles
                return 0;
            }
        });

        //making object with amount of rows to insert sorted array
        Object[][] sortedData = new Object[rowCount][];
        int sortedInd = 0;//initalizing a sorted index 

        //itterating through all rows
        for (int i = 0; i < rowCount; i++) {
            sortedData[sortedInd++] = data[sortIndices[i]];
    
        }

        data = sortedData;//set data to sorted data 
        fireTableDataChanged();//update table 
    }

    /**
     * Clearing all the table data 
     */
    public void clearData(){
        data = new Object[0][0];//clears all table data 
        fireTableDataChanged();//update table 
    }

}