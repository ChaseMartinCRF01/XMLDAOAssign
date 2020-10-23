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
public class DeleteOrderRecordHandler implements TaskHandler {

    /**
     * Starts the logic for the task.  Requires a reference to the Data Access
     * Object.
     *
     * @param dao a data access object
     */
    @Override
    public void startTask(OrderRecordDAO dao) {
        UIUtility.showSectionTitle("Delete Order Record");
        OrderRecord orderRecord;
        try{
            orderRecord = SelectOrderRecordUtility.selectOrderRecord(dao);

            UIUtility.showMessage("Deleting the order record...");
            dao.deleteOrderRecord(orderRecord);
            UIUtility.showMessage("Order Record deleted");
        } catch (OrderRecordDataException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), false);
        }

        UIUtility.showMessage("Delete an Order complete.");
        UIUtility.pressEnterToContinue();
    }

}
