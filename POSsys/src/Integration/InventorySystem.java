package Integration;

import java.util.ArrayList;
import java.util.List;

import Controller.DatabaseFailureException;
import Model.ItemDTO;

/**
 * Contains all the sale information inside
 * the inventory system in the database.
 */
public class InventorySystem 
{
    private List<ItemDTO> itemList = new ArrayList<>();

    InventorySystem() 
    {
    	addItems();
    }
    
    /**
     * Updates the inventory system.
     */
    public void updateInventorySystem() {
        
    }
    
    private void addItems()
    {
    	itemList.add(new ItemDTO(1004021, 2, 54.99));
    	itemList.add(new ItemDTO(2004202, 1, 39.99));
    	itemList.add(new ItemDTO(3004204, 8, 149.99));
    }
    
    /**
	 * Checks if an item with the searched item is in the database
	 * @param itemToSearch The item that is being searched 
	 * @return  a valid item with vali identification number.
	 * An item of <code >itemDTO</code> 
	 * If the item ID is invalid then the method throws an exception. 
     * @throws DatabaseFailureException The item ID is invalid and therefore cannot connect to the database.
	 */
	public ItemDTO searchItem(ItemDTO itemToSearch) throws DatabaseFailureException  
	{
		ItemDTO validItem = null;
		
		if(itemToSearch.getID() == 0) 
		{
			throw new DatabaseFailureException("Database failure has occured.");
		}
					
		for (ItemDTO item : itemList) 
		{
			if (item.getID() == itemToSearch.getID()) 
			{
				validItem = item;
			}
		}
		
		return validItem;
	}
}