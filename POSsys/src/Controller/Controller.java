package Controller;

import Integration.ItemRegistry;
import Integration.Printer;
import Integration.RegistryCreator;
import Integration.SaleRegistry;
import Integration.DiscountRegistry;
import Integration.SystemCreator;
import Integration.InventorySystem;
import Integration.AccountingSystem;
import Model.CashRegister;
import Model.Amount;
import Model.Sale;
import Model.SaleObserver;
import Model.TotalRevenueObserver;
import Model.Receipt;
import Model.ItemDTO;
import Model.CustomerDTO;
import Model.Discount;

/**
 * This is the application’s only controller class. All calls to the
 * model pass through here.
 */
public class Controller
{
    private Sale sale;
    private final DiscountRegistry discountRegistry;
    private final SaleRegistry saleRegistry;
    private final Printer printer;
    private final AccountingSystem accountingSystem;
    private final InventorySystem inventorySystem;
    private Discount discount;
    
    /**
     * Creates a new instance.
     *
     * @param regCreator Used to get all classes that handle database calls.
     *
     * @param sysCreator Used to get all classes that handle external system calls.
     *
     * @param printer    Interface to printer.
     */
    public Controller(RegistryCreator regCreator, SystemCreator sysCreator, Printer printer)
    {
        regCreator.getItemRegistry();
        this.saleRegistry = regCreator.getSaleRegistry();
        this.discountRegistry = regCreator.getDiscountRegistry();
        this.printer = printer;
        new CashRegister();
        this.accountingSystem = sysCreator.getAccountingSystem();
        this.inventorySystem = sysCreator.getInventorySystem();
    }
    
    /**
     *  Initiates a new sale.
     */
    public void startNewSale()
    {
        this.sale = new Sale();
    }
    
    /**
     * Get the information of the recorded item..
     *
     * @param item The item where the customer is buying and the cashier is recording.
	 * @throws ItemIdNotFoundInInventoryException If an item ID doesn't exist in the inventory catalog after search.
     * @throws DatabaseFailureException if a database failure has occured when a search is made for a particular, item ID
     * @return The recorded item.
     */
    public ItemDTO recordItem(ItemDTO item)
    	throws ItemIdNotFoundInInventoryException, DatabaseFailureException
    {
    	ItemDTO validItem = inventorySystem.searchItem(item);
    	int itemID = item.getID();
    	if (validItem == null) 
    	{
    		throw new ItemIdNotFoundInInventoryException(itemID);
    	}
    	
    	return sale.recordItem(validItem);
    }
    
    /**
     * Increases amount of sold items. After calling this method, the amount of items
     * will increase. This method also
     * permanently saves information about the current sale.
     *
     * @param soldItem The sold item that will be increased.
     */
    public void increaseAmountSoldItem(ItemDTO soldItem)
    {
        sale.setIncreasedAmountOfSoldItem(soldItem);
        saleRegistry.saveSale(sale);
    }
    
    /**
     * Records an item with requested discount from the customer.
     * After calling this method, the item
     * will be discounted. This method also
     * permanently saves information about the current discount.
     *
     * @param customer The specified customer that is buying an item with requested discount.
     * @throws java.lang.ArgumentException
     */
    public void recordItemWithDiscount(CustomerDTO customer) 
    {
    	sale.discountCalculation(discountRegistry.getDiscount(customer));
        discountRegistry.saveDiscount(discount);
    }
    
    /**
     * Ends a long started new sale. After calling this method, the sale
     * will be ended by the cashier. This method also
     * updates the external systems such as <code>inventorySystem</code> and <code>accountingSystem</code>.
     */
    public void endSale()
    {
        saleRegistry.setSaleComplete(sale);
        accountingSystem.updateAccountingSystem(sale);
        inventorySystem.updateInventorySystem();
    }
    
    /**
     * Initiates a payment with the paid amount.
     * Also, the external systems will be updated.
     * This method handles sale item payment and at the end it will calculate change and
     * print the receipt.
     *
     * @param paidAmount The amount of cash paid by the customer.
     */
    public void initiatePayment(Amount paidAmount)
    {
    	Receipt receipt = sale.getReceipt();
        printer.printReceipt(receipt);
        
        sale.initiatePayment(paidAmount);
    }
    
    public void addTotalRevenueObserver(TotalRevenueObserver obs) 
    {
    	accountingSystem.addObserver(obs);
	}
    
    public void addSaleObserver(SaleObserver obs)
    {
        sale.addObserver(obs);
    }
    
}