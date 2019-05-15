package View;

import java.io.IOException;

import Controller.Controller;
import Controller.DatabaseFailureException;
import Controller.ItemIdNotFoundInInventoryException;
import Model.ItemDTO;
import Model.SaleDirectoryComposite;
import Model.Amount;
import Model.CustomerDTO;

/**
 * This program has no view, instead, this class is a placeholder
 * for the entire view.
 */
public class View
{
    private Controller contr;
    private ErrorMessage errorMsg;
    private LogHandler logger;
    
    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     *
     */
    public View(Controller contr) throws IOException
    {    	
        this.contr = contr;
        this.errorMsg = new ErrorMessage();
        contr.addTotalRevenueObserver(new TotalRevenueView());
        this.logger = new LogHandler();
    }
    
    /**
     * Starts a new sale.
     */
    public void runFakeSale()
    {
        System.out.println("New sale is started.");
        contr.startNewSale();

        contr.addSaleObserver(new SaleItemList());
        int itemID = 1004021;
        recordItem(itemID);
        
        ItemDTO item1 = new ItemDTO(1004021, 2, 54.99); 
        SaleDirectoryComposite composite = new SaleDirectoryComposite(); 
        composite.addItem(item1);
              
        SaleDirectoryComposite directory = new SaleDirectoryComposite(); 
        directory.addItem(composite); 
        System.out.println("Total price with no discount is: ");
        directory.showTotalPrice();
        System.out.println("\n");
        directory.showTotalDiscountPrice();
        System.out.println("\n");        
        
        CustomerDTO customerID = new CustomerDTO(190302);
        contr.recordItemWithDiscount(customerID);
        
        ItemDTO productNoTwo = new ItemDTO(itemID, 2, 54.99);
		System.out.println("\nTwo items with the same ID " + itemID + " is increased: ");
        contr.increaseAmountSoldItem(productNoTwo);
        
		System.out.println("\nThe sale is completed.");
		contr.endSale();
		
		System.out.println("\nThe 100kr payment has been initiated.\n");
		contr.initiatePayment(new Amount(100));
    }
    
    private void recordItem(int itemID) 
    {
        try 
        {
            int quantity = 2;
            double price = 54.99;
            ItemDTO item = new ItemDTO(itemID, quantity, price);;
            System.out.println("");
            System.out.println("Result for item with ID " + itemID +  ": ");
            contr.recordItem(item);
            System.out.println("");
        } 
        catch (ItemIdNotFoundInInventoryException ex)
        {
        	fixException("Item with ID " + ex.getID() + " was not found in inventory catalog.", ex);
        }
        catch (DatabaseFailureException ex) 
        {
        	fixException("Failed to connect to database, please try again later.", ex);
		} 
    }
    
    private void fixException(String msg, Exception ex)
    {
    	errorMsg.showErrorMsg(msg);
    	logger.logException(ex);
    }
}
