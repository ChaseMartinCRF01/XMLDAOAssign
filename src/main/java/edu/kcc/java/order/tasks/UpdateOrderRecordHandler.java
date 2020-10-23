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
public class UpdateOrderRecordHandler implements TaskHandler {

    /**
     * Starts the logic for the task.  Requires a reference to the Data Access
     * Object.
     *
     * @param dao a data access object
     */
    @Override
    public void startTask(OrderRecordDAO dao) {
        UIUtility.showSectionTitle("Update Order Record");
        OrderRecord original;
        OrderRecord updated;
        try{
            original = SelectOrderRecordUtility.selectOrderRecord(dao);
            updated = new OrderRecord(original);

            // Show the Order Number, but it cannot be updated
            UIUtility.showMessage("Updating for Order Number "
                    + original.getOrderNumber());

            // Show and update the order date
            UIUtility.showMessage("Current Order Date: "
                    + original.getOrderDate());
            LocalDate orderDate
                    = UIUtility.getUserDate("New Order Date:");
            updated.setOrderDate(orderDate);

            // Show and update the Vendor ID
            UIUtility.showMessage("Current Vendor ID: "
                    + original.getVendorId());
            int vendorId = UIUtility.getUserInt(
                    "New Vendor ID:", "That is not a whole number.");
            updated.setVendorId(vendorId);

            // Save the changes to the data store
            UIUtility.showMessage("Saving changes...");
            dao.updateOrderRecord(original, updated);
            UIUtility.showMessage("Order Record has been saved.");
        } catch (OrderRecordDataException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), false);
        }

        UIUtility.showMessage("Update an Order complete.");
        UIUtility.pressEnterToContinue();
    }

}
