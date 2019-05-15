package Controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Integration.Printer;
import Integration.RegistryCreator;
import Integration.SystemCreator;
import Model.Amount;
import Model.CustomerDTO;
import Model.Discount;
import Model.ItemDTO;

class ControllerTest 
{
	 private Controller instance;
	 private RegistryCreator regCreator;
	 private SystemCreator sysCreator;
	 private ByteArrayOutputStream printOut;
	 private PrintStream originalSysOut;
	
	 @BeforeEach
	 public void setUp() 
	 {
		 Printer printer = new Printer();
	     regCreator = new RegistryCreator();
	     sysCreator = new SystemCreator();
	     instance = new Controller(regCreator, sysCreator, printer);
	     
		 printOut = new ByteArrayOutputStream();
		 PrintStream inMemSysOut = new PrintStream (printOut);
		 originalSysOut = System.out;
		 System.setOut (inMemSysOut);

	 }
	 
	 @AfterEach
	 public void tearDown() 
	 {
		 instance = null;
	     regCreator = null;
	     sysCreator = null;
		 System.setOut(originalSysOut);
	     printOut = null;
	 }

	 @Test
	 public void testStartNewSale() 
	 {
		 System.out.println("startNewSale");
	     instance.startNewSale();
	 }
	 
	 /*@Test
	 public void testRecordItem() throws ItemIdNotFoundInInventoryException, DatabaseFailureException 
	 {
		 instance.startNewSale();
	     int itemID = 1004021;
		 int quantity = 2;
		 double price = 54.99;
		 ItemDTO product = new ItemDTO(itemID, quantity, price);
	     ItemDTO result = instance.recordItem(product);
	     String actualResult = result.toString();
	     String expectedResult = "Identification number: " + itemID + "Quantity: " + quantity + "Price: " + price + "kr";
	     assertEquals("ItemDTO from recordItem is not the same as ItemDTO with expected result.", expectedResult, actualResult);
	 }*/
	 
	 /*@Test
	 public void testIncreaseAmountSoldItem() throws ItemIdNotFoundInInventoryException, DatabaseFailureException 
	 {
		 instance.startNewSale();
		 int itemID = 1004021;
	     int quantity = 2;
	     double price = 54.99;
	     ItemDTO product = new ItemDTO(itemID, quantity, price);
	     ItemDTO soldItem = instance.recordItem(product);
	     instance.increaseAmountSoldItem(soldItem);
		 String result = printOut.toString();
	     String expRes = "item";
	     assertTrue(result.contains(expRes), "Wrong print out after calling increaseAmountSoldItem.");	     
	 }*/
	 
}
