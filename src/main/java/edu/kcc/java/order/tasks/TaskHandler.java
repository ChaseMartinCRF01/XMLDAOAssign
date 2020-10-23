package edu.kcc.java.order.tasks;

import edu.kcc.java.order.data.OrderRecordDAO;

/**
 * An interface to provide consistency to handling tasks.
 *
 * @author Bob Trapp
 */
public interface TaskHandler {

    /**
     * Starts the logic for the task.  Requires a reference to the Data Access
     * Object.
     *
     * @param dao a data access object
     */
    void startTask(OrderRecordDAO dao);

}
