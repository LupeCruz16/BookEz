package Tables;

import javax.swing.table.AbstractTableModel;

import Objects.orderObject;

public class totalsTableWriter extends AbstractTableModel {

    private final String[] columnNames = {"", "Order Total", "Item Sold Price", "Charged Shipping", "Shipping Paid", "Taxes", "Profit"};
    private Double[] totals = {0.00, 0.00, 0.00, 0.00, 0.00, 0.00};

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) {
            return "Totals";
        } else {
            return totals[col - 1];
        }
    }

    /**
     * Adding an orders information to update the current totals 
     * @param order
     */
    public void addOrder(orderObject order) {
        totals[0] = Math.round((totals[0] + order.getTotal()) * 1000) / 1000.0;
        totals[1] = Math.round((totals[1] + order.getSoldPrice()) * 1000) / 1000.0;
        totals[2] = Math.round((totals[2] + order.getShipPaid()) * 1000) / 1000.0;
        totals[3] = Math.round((totals[3] + order.getShipCost()) * 1000) / 1000.0;
        totals[4] = Math.round((totals[4] + order.getTax()) * 1000) / 1000.0;
        totals[5] = Math.round((totals[5] + order.getProfit()) * 1000) / 1000.0;

        fireTableDataChanged();
    }

    /**
     * Removing an orders information to update the current totals 
     * @param order
     */
    public void removeOrder(orderObject order) {

        totals[0] = Math.round((totals[0] - order.getTotal()) * 1000) / 1000.0;
        totals[1] = Math.round((totals[1] - order.getShipCost()) * 1000) / 1000.0;
        totals[2] = Math.round((totals[2] - order.getShipPaid()) * 1000) / 1000.0;
        totals[3] = Math.round((totals[3] - order.getSoldPrice()) * 1000) / 1000.0;
        totals[4] = Math.round((totals[4] - order.getTax()) * 1000) / 1000.0;
        totals[5] = Math.round((totals[5] - order.getProfit()) * 1000) / 1000.0;
       
        fireTableDataChanged();
    }

    /**
     * Resetting the totals to 0.0 
     */
    public void clearTotals() {
        totals = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        fireTableDataChanged();
    }
}
