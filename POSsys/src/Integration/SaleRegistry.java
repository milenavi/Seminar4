package Integration;

import Model.Sale;

/**
 * Contains all calls to the data store with performed sales.
 */
public class SaleRegistry
{
    private Sale sale;
    private boolean completeSale = false;
    
    /**
     * Saves the specified sale permanently.
     *
     * @param sale The sale that will be saved.
     */
    public void saveSale(Sale sale)
    {
        this.sale = sale;
    }
    
    /**
     * Completes the specified sale permanently.
     *
     * @param sale The sale that will be completed.
     */
    public void setSaleComplete(Sale sale)
                throws IllegalArgumentException
    {
        if (sale == null)
            throw new IllegalArgumentException("A sale is not underway.");
        
        completeSale = true;
    }
    
    /**
     * Completes the specified sale permanently.
     *
     * @return completeSale The completed sale.
     */
    public boolean completeSale()
    {
        return completeSale;
    }
}
