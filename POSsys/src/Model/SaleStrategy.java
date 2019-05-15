package Model;

/**
 * Implements the algorithm of the discount calculation
 * and other types of calculations.
 * 
 * @author milenavilcinskaite
 *
 */
public interface SaleStrategy 
{
	/**
     * Returns the total price of the item.
     */
    void showTotalPrice();
    
    /**
     * Shows the total discounted price of the item.
     */
    void showTotalDiscountPrice();
    
    /**
     * Calculates the total discounted price of the item.
     */
    double discountCalculation(Discount discount);
}
