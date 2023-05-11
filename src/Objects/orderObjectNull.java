package Objects;

public class orderObjectNull implements orderObjectIF {
    private String orderNum ;
    Double total, shipCost, soldPrice, shipPaid, tax, profit;

    /**
     * Constructor for order object
     * @param orderNum order number
     * @param total total sold price
     * @param shipCost shipping cost 
     * @param soldPrice item sold price 
     * @param shipPaid shipping paid by buyer
     * @param tax taxes paid 
     * @param profit profit made
     */
    public orderObjectNull(String orderNum, Double total, Double shipCost, Double soldPrice, Double shipPaid, Double tax, Double profit){
  
        this.orderNum = null;
        this.total = null;
        this.shipCost = null;
        this.soldPrice = null;
        this.shipPaid = null;
        this.tax = null;
        this.profit = null;
    }

    //setter and getter methods
    public void setOrderNum(String orderNum){
        this.orderNum = null;
    }

    public String getOrderNum(){
        return null;
    }

    public void setTotal(Double total){
        this.total = null;
    }

    public Double getTotal(){
        return null;
    }

    public void setShipCost(Double shipCost){
        this.shipCost = null;
    }

    public Double getShipCost(){
        return null;
    }

    public void setSoldPrice(Double soldPrice){
        this.soldPrice = null;
    }

    public Double getSoldPrice(){
        return null;
    }

    public void setShipPaid(Double shipPaid){
        this.shipPaid = null;
    }

    public Double getShipPaid(){
        return null;
    }

    public void setTax(Double tax){
        this.tax = null;
    }

    public Double getTax(){
        return null;
    }

    public void setProfit(Double profit){
        this.profit = null;
    }

    public Double getProfit(){
        return null;
    }
}
