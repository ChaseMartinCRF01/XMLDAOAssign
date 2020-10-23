package edu.kcc.java.order.tasks;

import edu.kcc.java.order.OrderRecord;
import edu.kcc.java.order.data.OrderRecordDAO;
import edu.kcc.java.order.data.OrderRecordDataException;
import edu.kcc.java.ui.UIUtility;
import java.util.List;

/**
 * Provides common functionality needed by some of the handler classes.
 *
 * @author Bob Trapp
 */
public class SelectOrderRecordUtility {

    public static OrderRecord selectOrderRecord(OrderRecordDAO dao)
            throws OrderRecordDataException{
        OrderRecord record = null;

        // Get the records available
        List<OrderRecord> records = dao.getAllOrderRecords();

        // Build a menu from the records
        String prompt = "Please choose an Order Record:";
        String title = "Select an Order Record";
        String[] menuOptions = new String[records.size()];
        for (int i = 0; i < menuOptions.length; i++) {
            menuOptions[i] = (i + 1) + ") " + records.get(i);
        }

        // Show the menu and get the user response
        String userChoice =
                UIUtility.showMenuOptions(title, prompt, menuOptions);

        // Convert the response into a record
        try{
            int index = Integer.parseInt(userChoice) - 1;
            record = records.get(index);
        } catch(NumberFormatException nfe){
            throw new OrderRecordDataException(nfe);
        }

        return record;
    }


}
