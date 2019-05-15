package Model;

import Model.Discount;

/**
 * Contains information about one particular item.
 */
public final class ItemDTO implements SaleStrategy
{
    private final int id;
    private int quantity;
    private final double price;
    private double totalDiscountPrice;
    
    /**
     * Creates a new instance representing a particular item.
     *
     * @param id The identification number of the item.
     *
     * @param amount The quantity of items.
     *
     * @param price A price of an item.
     */
    public ItemDTO(int id, int quantity, double price)
    {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }
    
    /**
     * Checks if the recorded item has the same matching features as
     * the item information that exists inside the POS system.
     *
     * @param recorded Contains record criteria.
     *
     * @return <code>true</code> if this object has
     *         the same features as <code>recorded</code>,
     *         <code>false</code> if it has not.
     */
    public boolean matches(ItemDTO recorded)
    {
        if (recorded.getID() != 0 && recorded.getID() != id)
        {
            return false;
        }
        if (recorded.getQuantity() != 0 && recorded.getQuantity() != quantity)
        {
            return false;
        }
        if (recorded.getPrice() != 0 && recorded.getPrice() != price)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Makes the <code>int</code>, <code>int</code> and
     * <code>double</code> into a <code>String</code> object.
     *
     * @return <code>int</code>, <code>int</code>
     * and <code>double</code> as a <code>String</code>.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("- Identification number: " + id);
        builder.append("\n- Quantity: " + quantity);
        builder.append("\n- Price: " + price + "kr");
        return builder.toString();
    }
    
    /**
     * Get the id of an item
     *
     * @return the id of an item
     */
    public int getID()
    {
        return id;
    }
    
    /**
     * Get the quantity of items
     *
     * @return the quantity of items
     */
    public int getQuantity()
    {
        return quantity;
    }
    
    /**
     * Get the price of an item
     *
     * @return the price of an item
     */
    public double getPrice()
    {
        return price;
    }
    
    @Override
    public void showTotalPrice() 
    {
    	System.out.println(price*quantity + "kr");
    }
    
    public double getTotal() 
    {
    	return price * quantity;
    }
    
    @Override
    public double discountCalculation(Discount discount) 
    {
    	int total = 1;
    	this.totalDiscountPrice = getTotal();
        if(totalDiscountPrice <= 49.99) 
        {
        	//System.out.println("Total price with 10% discount is: ");
        	totalDiscountPrice = totalDiscountPrice * (total - discount.tenPercentDiscount());
        }
                
        else if(totalDiscountPrice <= 149.99) 
        {
        	//System.out.println("Total price with 20% discount is: ");
        	totalDiscountPrice = totalDiscountPrice * (total - discount.twentyPercentDiscount());
        }
                
        else if(totalDiscountPrice >= 149.99) 
        {
        	//System.out.println("Total price with 30% discount is: ");
        	totalDiscountPrice = totalDiscountPrice * (total - discount.thirtyPercentDiscount());
        }
        return totalDiscountPrice;
    }
    
    @Override
    public void showTotalDiscountPrice() 
    {
    	int total = 1;
    	this.totalDiscountPrice = getTotal();
        if(totalDiscountPrice <= 49.99) 
        {
        	totalDiscountPrice = totalDiscountPrice * (total - 0.10);
        	System.out.println("10% discounted price via Composite: \n" + roundTotalPrice(totalDiscountPrice) + "kr");
        }
                
        else if(totalDiscountPrice <= 149.99) 
        {
        	totalDiscountPrice = totalDiscountPrice * (total - 0.20);
        	System.out.println("20% discounted price via Composite: \n" + roundTotalPrice(totalDiscountPrice) + "kr");
        }
                
        else if(totalDiscountPrice >= 149.99) 
        {
        	totalDiscountPrice = totalDiscountPrice * (total - 0.30);
        	System.out.println("30% discounted price via Composite: \n" + roundTotalPrice(totalDiscountPrice) + "kr");
        }
    }
    
    private double roundTotalPrice(double totalPrice) 
    {
    	return Math.round(totalPrice);
    }
    
    /**
    *
    * @param quantity
    */
   public void addQuantity(int quantity)
   {
       this.quantity =+ quantity;
   }
}

