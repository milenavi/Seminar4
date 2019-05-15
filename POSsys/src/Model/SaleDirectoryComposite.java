package Model;

import java.util.ArrayList;
import java.util.List;

public class SaleDirectoryComposite implements SaleStrategy
{
	private List<SaleStrategy> itemList = new ArrayList<SaleStrategy>(); 
    
    @Override
    public double discountCalculation(Discount discount)  
    { 
    	double totalPrice = 0;
        for(SaleStrategy item : itemList) 
        { 
        	totalPrice = item.discountCalculation(discount);
        }
        
		return totalPrice; 
    } 

    @Override
    public void showTotalPrice() 
    {
    	for(SaleStrategy item : itemList) 
        { 
    		item.showTotalPrice(); 
        }
    }
    
    @Override
    public void showTotalDiscountPrice() 
    {
    	for(SaleStrategy item : itemList) 
        { 
    		item.showTotalDiscountPrice(); 
        }
    }
       
    public void addItem(SaleStrategy item) 
    { 
        itemList.add(item); 
    } 
       
    public void removeItem(SaleStrategy item) 
    { 
    	itemList.remove(item); 
    }
}
