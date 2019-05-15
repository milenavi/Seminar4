package Model;

import Model.Amount;
import Model.Sale;

/**
 * Represents one specific payment for one specific sale item. The
 * sale item is payed with cash.
 */
public class CashPayment
{
    private Amount paidAmt;
    private double totalPrice;
    
    /**
     * Creates a new instance. The customer handed over the
     * specified amount.
     *
     * @param paidAmt The amount of cash that was handed over by the customer.
     *
     */
    public CashPayment(Amount paidAmt)
    {
        this.paidAmt = paidAmt;
    }
    
    /**
     * Calculates the total price of the specified sale that the item is set to.
     *
     * @param paidSale The sale that the item is set to for which the customer is paying.
     *
     */
    public double calculateTotalPrice(Sale paidSale)
    {
        return totalPrice = paidSale.getTotalPrice();
    }
    
    /**
     * @return The total price of the item that is set to sale and that was paid by the customer.
     */
    public double getTotalPrice()
    {
        return totalPrice;
    }
    
    /**
     * @return The amount of change the customer shall have.
     */
    public double calculateChange()
    {
        return paidAmt.minus(totalPrice);
    }
}
