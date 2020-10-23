package edu.kcc.java.order.tasks;

import edu.kcc.java.order.OrderRecord;
import edu.kcc.java.order.data.OrderRecordDAO;
import edu.kcc.java.order.data.OrderRecordDataException;
import edu.kcc.java.ui.UIUtility;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bob Trapp
 */
public class FindOrderRecordHandler implements TaskHandler  {

    /**
     * Starts the logic for the task.  Requires a reference to the Data Access
     * Object.
     *
     * @param dao a data access object
     */
    @Override
    public void startTask(OrderRecordDAO dao) {
        UIUtility.showSectionTitle("Find an Order Record");

        String orderNumber =
                UIUtility.getUserString("Please enter the order number:");
        OrderRecord order = null;
        try{
            if(!orderNumber.isBlank()){
                order = dao.getOrderRecord(orderNumber);
            }
            if(null == order){
                UIUtility.showMessage("No matching order found");
            } else {
                UIUtility.showMessage("Found Order: " + order);
            }
        } catch (OrderRecordDataException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), false);
        }

        UIUtility.showMessage("Find an Order complete.");
        UIUtility.pressEnterToContinue();
    }

}
