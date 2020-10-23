package edu.kcc.java.order.data;

import edu.kcc.java.order.OrderRecord;
import java.util.List;

/**
 * A Data Access Object (DAO) interface for working with OrderRecord data.
 *
 * @author Bob Trapp
 */
public interface OrderRecordDAO {

    /**
     * Create a new record in the data store.
     *
     * @param orderRecord
     * @throws OrderRecordDataException
     */
    void createOrderRecord(OrderRecord orderRecord)
            throws OrderRecordDataException;

    /**
     * Returns the Order Record associated with the supplied order number, or
     * null if there is no such record.
     *
     * @param orderNumber the order number of the desired order record
     * @return the order record or null if no match
     * @throws OrderRecordDataException
     */
    OrderRecord getOrderRecord(String orderNumber)
            throws OrderRecordDataException;

    /**
     * Returns a list of all available order records.
     *
     * @return List of OrderRecord objects in the data store
     * @throws OrderRecordDataException
     */
    List<OrderRecord> getAllOrderRecords() throws OrderRecordDataException;

    /**
     * Updates an existing Order Record in the data store.  The original must
     * be found or the method will throw an Exception. The updated OrderRecord
     * contains the changes.  The order number cannot be changed through this
     * method.
     *
     * @param original the order record to be changed
     * @param updated the new data for the record
     * @throws OrderRecordDataException
     */
    void updateOrderRecord(OrderRecord original, OrderRecord updated)
            throws OrderRecordDataException;


    /**
     * Removes the supplied record from the data store.
     *
     * @param orderRecord the record to remove
     * @throws OrderRecordDataException
     */
    void deleteOrderRecord(OrderRecord orderRecord)
            throws OrderRecordDataException;



}
