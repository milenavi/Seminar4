package Controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Integration.Printer;
import Integration.RegistryCreator;
import Integration.SystemCreator;
import Model.ItemDTO;

class ItemIdNotFoundInInventoryExceptionTest 
{
    private Controller contr;
    private RegistryCreator regCreator;
    private SystemCreator sysCreator;
    
    @BeforeEach
    void setUp() 
    {
    	Printer printer = new Printer();
	    regCreator = new RegistryCreator();
	    sysCreator = new SystemCreator();
        contr = new Controller(regCreator, sysCreator, printer);
    }

    @AfterEach
    void tearDown() 
    {
    	contr = null;
    }

    /**
     * Testing that the message is the correct one with a given input and that the correct item id is passed into exception.
     * @throws ItemIdNotFoundInInventoryException If an item Id is not found inside the inventory catalog.
     * @throws DatabaseFailureException If the ItemID is invalid.
     */
    @Test
    public void testItemIdNotFoundInInventoryException() throws ItemIdNotFoundInInventoryException, DatabaseFailureException {
        contr.startNewSale();
        
        try {
        	ItemDTO item = new ItemDTO(1004021, 2, 54.99);
        	contr.recordItem(item);
        } catch(ItemIdNotFoundInInventoryException ex)
        {
            assertEquals(1004021, ex.getID());
            assertEquals("Item with id 1004021 was not found in inventory catalog. A valid id needs to be added to catalog.", ex.getMessage());
        }
    }

}
