package Integration;

import java.util.ArrayList;

import java.util.List;

import Model.Sale;
import Model.TotalRevenueObserver;

/**
 * Contains all the sale information inside the
 * accounting system (for accounting) in the database.
 */
public class AccountingSystem
{
    private List<Sale> logSystem = new ArrayList<>();
    private ArrayList<TotalRevenueObserver> observerList = new ArrayList<>(); 

    AccountingSystem() {
        
    }
    
    /**
     * Updates the accounting system.
     *
	 * Adds sale to the database.
	 * 
	 * @param sale a Sale instance
	 */
    public void updateAccountingSystem(Sale sale)
    {
    	logSystem.add(sale);
    	notifyObserver();
    }
    
	public void addObserver(TotalRevenueObserver obs) 
	{
		observerList.add(obs);
	}
	
	public void notifyObserver() 
	{
		for (TotalRevenueObserver obs : observerList) 
		{
			obs.printTotalRevenue(getTotalRevenue());
        }
	}
	
	private double getTotalRevenue()
	{
		int totalRev = 0;
		for (Sale saleLog : logSystem) 
		{
			totalRev += saleLog.getTotalPrice();
		}
		return totalRev;
	}
}