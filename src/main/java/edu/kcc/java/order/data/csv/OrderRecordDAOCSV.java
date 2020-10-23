package edu.kcc.java.order.data.csv;

import edu.kcc.java.order.OrderRecord;
import edu.kcc.java.order.data.OrderRecordDAO;
import edu.kcc.java.order.data.OrderRecordDataException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bob Trapp
 */
public class OrderRecordDAOCSV implements OrderRecordDAO {

    /**
     * The name of the file containing the records.
     */
    private static final String FILE_NAME = "order_records.csv";

    /**
     * The ArrayList containing all of the records read from the CSV file.
     */
    private ArrayList<OrderRecord> recordList;

    /**
     * The method that reads from the CSV file and stores the date into the
     * ArrayList.
     *
     * @throws OrderRecordDataException
     */
    private void readFile() throws OrderRecordDataException {
        ArrayList<OrderRecord> fileList = new ArrayList<>();
        String line;
        String[] fields;
        String orderNumber;
        String readDate;
        LocalDate orderDate;
        String readVendorId;
        int vendorId;
        int lineCount = 0;
        try(Scanner in = new Scanner(new File(FILE_NAME))){
            while(in.hasNextLine()){
                lineCount++;
                line = in.nextLine();
                fields = line.split(",");
                orderNumber = fields[0];
                readDate = fields[1];
                readVendorId = fields[2];
                orderDate = LocalDate.parse(readDate);
                vendorId = Integer.parseInt(readVendorId);
                fileList.add(new OrderRecord(orderNumber, orderDate, vendorId));
            }
        } catch (FileNotFoundException ex) {
            throw new OrderRecordDataException(ex);
        }
        recordList = fileList;
    }

    /**
     * Saves the data from the ArrayList into the CSV file.
     *
     * @throws OrderRecordDataException
     */
    private void saveToFile() throws OrderRecordDataException {
        try(PrintWriter writer = new PrintWriter(new File(FILE_NAME))){
            String line;
            for (OrderRecord orderRecord : recordList) {
                line = orderRecord.getOrderNumber() + ","
                        + orderRecord.getOrderDate().toString() + ","
                        + orderRecord.getVendorId();
                writer.println(line);
            }
        } catch (FileNotFoundException ex) {
            throw new OrderRecordDataException(ex);
        }
    }

    /**
     * Checks to see whether the file has been read.  If it has not, then it
     * calls the readFile() method.
     *
     * @throws OrderRecordDataException
     */
    private void verifyData() throws OrderRecordDataException{
        if(null == recordList){
            readFile();
        }
    }

    /**
     * Create a new record in the data store.
     *
     * @param orderRecord
     * @throws OrderRecordDataException
     */
    @Override
    public void createOrderRecord(OrderRecord orderRecord)
            throws OrderRecordDataException {
        verifyData();
        OrderRecord existingRecord
                = getOrderRecord(orderRecord.getOrderNumber());
        if(null != existingRecord){
            throw new OrderRecordDataException(
                    "There is already an order with that order number.");
        }
        recordList.add(orderRecord);
        saveToFile();
    }

    /**
     * Returns the Order Record associated with the supplied order number, or
     * null if there is no such record.
     *
     * @param orderNumber the order number of the desired order record
     * @return the order record or null if no match
     * @throws OrderRecordDataException
     */
    @Override
    public OrderRecord getOrderRecord(String orderNumber)
            throws OrderRecordDataException {
        verifyData();
        OrderRecord record = null;
        for (OrderRecord orderRecord : recordList) {
            if(orderRecord.getOrderNumber().equals(orderNumber)){
                // There is a match.  Use copy constructor
                record = new OrderRecord(orderRecord);
                break;
            }
        }
        return record;
    }

    /**
     * Returns a list of all available order records.
     *
     * @return List of OrderRecord objects in the data store
     * @throws OrderRecordDataException
     */
    @Override
    public List<OrderRecord> getAllOrderRecords()
            throws OrderRecordDataException {
        verifyData();
        List<OrderRecord> list = new ArrayList<>();
        for (OrderRecord orderRecord : recordList) {
            list.add(new OrderRecord(orderRecord));
        }
        return list;
    }

    /**
     * Updates an existing Order Record in the data store. The original must be
     * found or the method will throw an Exception. The updated OrderRecord
     * contains the changes. The order number cannot be changed through this
     * method.
     *
     * @param original the order record to be changed
     * @param updated the new data for the record
     * @throws OrderRecordDataException
     */
    @Override
    public void updateOrderRecord(OrderRecord original, OrderRecord updated)
            throws OrderRecordDataException {
        verifyData();
        OrderRecord existingRecord = getOrderRecord(original.getOrderNumber());
        if(null == existingRecord){
            throw new OrderRecordDataException(
                    "The original record does not exist.");
        } else {
            if(!existingRecord.getOrderDate().equals(original.getOrderDate())){
                throw new OrderRecordDataException(
                    "The original record order date does not match the"
                            + " data store.");
            }
            if(existingRecord.getVendorId() != original.getVendorId()){
                throw new OrderRecordDataException(
                    "The original record vendor ID does not match the"
                            + " data store.");
            }
            // All tests have passed, so do the update
            // Remember, "existingRecord" is a copy, not the one from the list
            for (OrderRecord orderRecord : recordList) {
                if(orderRecord.getOrderNumber()
                        .equals(original.getOrderNumber())){
                    orderRecord.setOrderDate(updated.getOrderDate());
                    orderRecord.setVendorId(updated.getVendorId());
                    saveToFile();
                    break;
                }
            }
        }
    }

    /**
     * Removes the supplied record from the data store.
     *
     * @param orderRecord the record to remove
     * @throws OrderRecordDataException
     */
    @Override
    public void deleteOrderRecord(OrderRecord orderRecord)
            throws OrderRecordDataException {
        verifyData();
        for (OrderRecord orderRecord1 : recordList) {
            if(orderRecord1.getOrderNumber()
                    .equals(orderRecord.getOrderNumber())){
                recordList.remove(orderRecord1);
                break;
            }
        }
    }

}
