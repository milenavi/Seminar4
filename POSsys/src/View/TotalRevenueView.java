package View;

import Model.TotalRevenueObserver;

/**
 * A display that shows the total amount paid for items 
 * since the program started.
 * This class shall never call the controller or any other class, 
 * but instead be updated using the Observer pattern: TotalRevenueObserver.
 */
public class TotalRevenueView implements TotalRevenueObserver 
{
	
	public void printTotalRevenue(double totalRev) 
	{
		display(totalRev);
		
	}
	
	private void display(double totalRev) 
	{
		System.out.println("The total revenue is: " + totalRev);
	}

}
