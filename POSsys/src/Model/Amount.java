package Model;

/**
 * Represents an amount of cash or items
 */
public class Amount
{
    private double amount;

    
    /**
     * Creates an instance, representing the specified amount.
     *
     * @param amount The amount represented by the newly created instance.
     */
    public Amount(double amount)
    {
        this.amount = amount;
    }

    /**
     * Get the value of amount.
     *
     * @return The value of amount.
     */
    public double getAmount()
    {
        return amount;
    }
    
    /**
     * Sets the instance amount to become a string.
     *
     * @return amount as a string.
     */
    @Override
    public String toString()
    {
        return Integer.toString((int) amount);
    }
    
    /**
     * Subtracts the specified amount instance
     *
     * @param another The second specified amount
     *
     * @return The difference of the first amount and the second specified amount
     */
    public double minus(double another)
    {
        return this.amount - another;
    }
   
    /**
     * Adds the specified amount instance
     *
     * @param another The second specified amount
     *
     * @return The sum of the first amount and the second specified amount
     */
    public double plus(double another)
    {
        return this.amount + another;
    }
    
    
    public void addAmount(double amount) 
    {
		this.amount += amount;
	}
}
