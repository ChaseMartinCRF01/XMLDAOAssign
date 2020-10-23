package edu.kcc.java.order.tasks;

import edu.kcc.java.order.OrderRecord;
import edu.kcc.java.order.data.OrderRecordDAO;
import edu.kcc.java.order.data.OrderRecordDataException;
import edu.kcc.java.ui.UIUtility;
import java.util.List;

/**
 *
 * @author Bob Trapp
 */
public class ShowAllOrderRecordsHandler implements TaskHandler {

    /**
     * Starts the logic for the task.  Requires a reference to the Data Access
     * Object.
     *
     * @param dao a data access object
     */
    @Override
    public void startTask(OrderRecordDAO dao) {
        UIUtility.showSectionTitle("Show All Order Records");

        try{
            List<OrderRecord> records = dao.getAllOrderRecords();
            if(records.isEmpty()){
                UIUtility.showMessage("There are no Order Records.");
            } else {
                UIUtility.showMessage("RECORDS:");
                for (OrderRecord record : records) {
                    UIUtility.showMessage("\t"
                            + "" + record);
                }
            }
        } catch (OrderRecordDataException ex) {
            UIUtility.showErrorMessage(ex.getMessage(), false);
        }

        UIUtility.showMessage("Find an Order complete.");
        UIUtility.pressEnterToContinue();
    }

}
