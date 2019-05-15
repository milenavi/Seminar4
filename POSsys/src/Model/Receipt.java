package Model;

import Model.Sale;
import java.time.LocalDateTime;

/**
 * The receipt of a sale
 */
public class Receipt
{
    private final Sale sale;
    
    /**
     * Creates a new instance.
     *
     * @param sale The sale proved by this receipt.
     */
    public Receipt(Sale sale)
    {
        this.sale = sale;
    }
    
    /**
     * Creates a well-formatted string with the entire content
     * of the receipt.
     *
     * @return The well-formatted receipt string.
     */
    public String createReceiptString()
    {
        StringBuilder builder = new StringBuilder();
        appendLine(builder, "Item Sale Receipt");
        endSection(builder);
        
        LocalDateTime saleTime = LocalDateTime.now();
        builder.append("Sale time: ");
        appendLine(builder, saleTime.toString());
        endSection(builder);
        
        builder.append("\nThe Sold Item: \n");
        appendLine(builder, sale.getItemForSale().toString());
        //builder.append("\n\nAmount of items sold: \n");
        //appendLine(builder, sale.toStringIncreasedQuantity());
        builder.append("\n\nTotal price: \n");
        appendLine(builder, sale.toStringTotalPrice());
        //builder.append("Change after payment: \n");
        //appendLine(builder, sale.toStringChange());
        endSection(builder);
		 
		return builder.toString();
    }
    
    private void appendLine(StringBuilder builder, String line)
    {
        builder.append(line);
        builder.append("\n");
    }
    
    private void endSection(StringBuilder builder)
    {
        builder.append("\n");
    }
}