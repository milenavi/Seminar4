package Controller;

/**
 * Thrown when a specified item id was not found in the inventory catalog.
 */
public class ItemIdNotFoundInInventoryException extends Exception 
{
	private int itemID;

    /**
     * Creates a new instance of the exception with information that the item could not be found.
     * @param itemID The id of the item searched for given as <code>Int</code>.
     */
    public ItemIdNotFoundInInventoryException(int itemID)
    {
        super("Item with ID " + itemID + " was not found in inventory catalog. A valid item ID must be recorded.");
        this.itemID = itemID;
    }

    public int getID()
    {
        return this.itemID;
    }
}
