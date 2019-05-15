package Integration;

import Model.Amount;
import Model.ItemDTO;
import Model.Sale;
import Model.Discount;
import Model.CustomerDTO;

/**
 * Contains all calls to the data store with performed discounts.
 */
public class DiscountRegistry
{
    private ItemDTO item;
	private double totalPriceAfterDiscount = 0;
	private Discount discount;
    private double totalPrice = 0;
	
	/**
     * Saves the specified discount permanently.
     *
     * @param discount The discount that will be saved.
     */
	public void saveDiscount(Discount discount)
    {
        this.discount = discount;
    }
    
    /**
     * Gives the price of an sale item with a requested discount calculation.
     *
     * @param customer The specified customer that is buying an item with requested discount.
     *
     * @return The price after the calculation of sales item price with requested discount.
     * @throws ArgumentException
     */
    /*public void getPriceAfterDiscount(CustomerDTO customer)
    		//throws IllegalArgumentException
    {
        /*if (customer.getID() < 0)
            throw new IllegalArgumentException("Given customer ID not found." + " Customer ID: " + customer.getID());
            */
        
        // Testing price calculation and calling calculatePriceWithDiscount method.
      /*   calculatePriceWithDiscount(this.item);
    }*/
    
    /**
    *
    * @param customer
    * 
    * @return discount New discount is returned
    */
   public Discount getDiscount(CustomerDTO customer)
		   throws IllegalArgumentException
   {
	   if (customer.getID() < 0)
       throw new IllegalArgumentException("Given customer ID not found." + " Customer ID: " + customer.getID());
       
       return discount = new Discount(customer);
   }
   
}