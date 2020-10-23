package edu.kcc.java.order.tasks;

import edu.kcc.java.order.OrderRecord;
import edu.kcc.java.order.data.OrderRecordDAO;
import edu.kcc.java.order.data.OrderRecordDataException;
import edu.kcc.java.ui.UIUtility;
import java.time.LocalDate;

/**
 *
 * @author Bob Trapp
 */
public class AddOrderRecordHandler implements TaskHandler {

    /**
     * Starts the logic for the task. Requires a reference to the Data Access
     * Object.
     *
     * @param dao a data access object
     */
    @Override
    public void startTask(OrderRecordDAO dao) {
        UIUtility.showSectionTitle("Add an Order Record");
        // Use the default constructor.
        OrderRecord record = new OrderRecord();
        // Establish some variables
        String orderNumber;
        LocalDate orderDate;
        int vendorId;

        try {
            // Get the order number
            orderNumber = UIUtility.getUserString("Enter the Order Number:");
            record.setOrderNumber(orderNumber);
            // Get the order date
            orderDate = UIUtility.getUserDate("Enter the Order Date:");
            record.setOrderDate(orderDate);
            // Get the vendor ID
            vendorId = UIUtility.getUserInt("Enter the Vendor ID:"
                    , "That is not a valid whole number.");
            record.setVendorId(vendorId);

            dao.createOrderRecord(record);
        } catch (IllegalArgumentException | OrderRecordDataException iae) {
            UIUtility.showErrorMessage(iae.getMessage(), false);
        }
        UIUtility.showMessage("Add an Order Record complete.");
        UIUtility.pressEnterToContinue();
    }

}
