package Model;

import Model.Sale;
import Model.ItemDTO;
import java.util.ArrayList;
import java.util.List;
import Model.Discount;
import Model.Receipt;

/**
 * Represents one particular sale transaction, where one
 * particular item is purchased by one particular customer.
 */
public class Sale
{
    private ItemDTO item;
    private double totalPrice = 0;
    private double totalChange;
    private int increasedQuantity;
    private List<SaleObserver> saleObservers = new ArrayList<>();
    private List<SaleStrategy> itemList = new ArrayList<>();
    private List<ItemDTO> anotherItemList = new ArrayList<>();
    
    /**
     * Creates a new instance, representing a sale made by
     * the cashier in order to set the item for sale.
     */
    public Sale()
    {
    	
    }
    
    /**
    *
    * @param itemToRecord
    * @return itemToRecord
    */
    public ItemDTO recordItem(ItemDTO itemToRecord) 
    {
    	itemList.add(itemToRecord);
    	anotherItemList.add(itemToRecord);
        //getTotal();
    	calculateTotalPrice(itemToRecord);
    	notifyObservers(itemToRecord.toString());
        return itemToRecord;
    }
    
   /**
    *
    * @param recordedItem
    * @return this.totalPrice
    */
    public void calculateTotalPrice(ItemDTO recordedItem)
    {
    	this.item = recordedItem;
    	this.totalPrice = recordedItem.getPrice() * recordedItem.getQuantity();
        //System.out.println("Total price with no discount: " + totalPrice + "kr");
    } 
    
    /**
     * 
     * @param discount
     * @return this.totalPrice
     */
    public double discountCalculation(Discount discount) 
    {
    	for (SaleStrategy str : itemList)
        {
    		System.out.println("Total discounted price via Controller: ");
            this.totalPrice = str.discountCalculation(discount);
            System.out.println(roundTotalPrice(totalPrice) + "kr");
        }
        return this.totalPrice;
    }
    
    private double roundTotalPrice(double totalPrice) 
    {
    	return Math.round(totalPrice);
    }
    
    /**
     * Sets increased amount of a sold item. After calling this method,
     * the sold item will be incremented in the POS system.
     *
     * @param recordedItem The item that is recorded in the POS system.
     */
    public void setIncreasedAmountOfSoldItem(ItemDTO recordedItem)
    {
    	for(ItemDTO item : anotherItemList) 
    	{
    		int quantity1 = item.getQuantity();
    		int quantity2 = recordedItem.getQuantity();
    		int firstRecordedItemID = item.getID();
        	int otherRecordedItemID = recordedItem.getID();
    		if(firstRecordedItemID == otherRecordedItemID)
        	{
        		quantity1 = quantity1 + quantity2;
            	System.out.println(quantity1 + "st");
            }
    	}
    }
    
    public int getIncreasedQuantity() 
    {
    	return this.increasedQuantity;
    }
    
    public String toStringIncreasedQuantity() 
    {
    	return this.increasedQuantity + "st";
    }
    
    /**
     * This sale item is paid using the specified payment.
     *
     * @param payment The payment used to pay this sale item.
     */
    public void initiatePayment(Amount paidAmt) 
    {
    	double paidAmount = paidAmt.getAmount();
    	if (paidAmount > totalPrice) 
    	{
        	double change = paidAmt.minus(totalPrice);
        	this.totalChange = roundChange(change);
        	System.out.println("Change after payment: \n" + totalChange + "kr");
    	}
    }
    
    private double roundChange(double change) 
    {
    	return Math.round(change);
    }
    
    public double getChange() 
    {
    	return this.totalChange;
    }
    
    public String toStringChange() 
    {
    	return Math.round(this.totalChange) + "kr";
    }
    
    public String toStringTotalPrice() 
    {
    	return Math.round(this.totalPrice) + "kr";
    }
    
    public ItemDTO getItemForSale() 
    {
    	return this.item;
    }
      
    /**
     * Returns a receipt for the current sale item.
     * @return receipt for the purchased item.
     */
    public Receipt getReceipt()
    {
        return new Receipt(this);
    }
    
    public double getTotalPrice() 
    {
    	return this.totalPrice;
    }
    
    /**
     * Returns the total price of all items registered so far.
     * Calculates total price of the sale by iterating the "leaf objects" in <code>itemList</code> in the current composite
     * <code>SaleComposite</code> instance.
     *
     * @return totalPrice as <code>Double</code>.
     */
    /*public double getTotal()
    {
        for (SaleComposite cmp : itemList)
        {
            this.totalPrice = this.totalPrice + cmp.getTotal();
            //System.out.println("HELLO: " + totalPrice);
        }
        return this.totalPrice;
    }*/
    
    /**
     * Adds an observer of the items that are being sold.
     * @param obs Observer of type <code>SaleObserver</code>.
     */
    public void addObserver(SaleObserver obs)
    {
        this.saleObservers.add(obs);
    }

    /**
     * Notifies the observers about the item that is currently being added to sale observers.
     * @param item The <code>String</code> of the item that is being added.
     */
    private void notifyObservers(String item)
    {
        for(SaleObserver obs : this.saleObservers) 
        {
            obs.addItem(item);
        }
    }
}