package edu.kcc.java.order;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents an Order Record from the data store.
 *
 * @author Bob Trapp
 */
public class OrderRecord implements Comparable<OrderRecord> {

    /**
     * The lower boundary for vendor ID.
     */
    private static final int MIN_VENDOR_ID = 101;

    /**
     * A default value used with the no-argument constructor.
     */
    private static final String DEFAULT_ORDER_ID = "INVALID";

    /**
     * A default value used with the no-argument constructor.
     */
    private static final int DEFAULT_VENDOR_ID = 101;
    
 

    /**
     * A unique, required String that acts as the unique identifier for the
     * order record.
     */
    private String orderNumber;

    /**
     * The required date of the order. New orders default to the current day.
     * Order dates cannot be in the future.
     */
    private LocalDate orderDate;

    /**
     * The unique identifier for a vendor. This is an integer value greater than
     * 100.
     */
    private int vendorId;

   

    /**
     * The full constructor requires an argument for each attribute.
     *
     * @param orderNumber The order number
     * @param orderDate the date of the order
     * @param vendorId the unique identifier for a vendor
     */
    public OrderRecord(String orderNumber, LocalDate orderDate, int vendorId ) {
        validateOrderNumber(orderNumber);
        validateOrderDate(orderDate);
        validateVendorId(vendorId);      
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.vendorId = vendorId;
    }

    /**
     * This constructor uses the current date as the order date.
     *
     * @param orderNumber the order number
     * @param vendorId the unique identifier for the vendor
     */
    public OrderRecord(String orderNumber, int vendorId ) {
        this(orderNumber, LocalDate.now(), vendorId);
    }

    /**
     * The no-argument constructor uses default values.
     */
    public OrderRecord() {
        this(DEFAULT_ORDER_ID, DEFAULT_VENDOR_ID);
    }

    /**
     * This is the copy constructor.
     *
     * @param original the original OrderRecord to copy.
     */
    public OrderRecord(OrderRecord original) {
        this(
                original.getOrderNumber(),
                 original.getOrderDate(),
                 original.getVendorId()
        );
    }

    /**
     * An override of the toString() method
     *
     * @return
     */
    @Override
    public String toString() {
        return "orderNumber=" + orderNumber
                + ", orderDate=" + orderDate
                + ", vendorId=" + vendorId;
    }

    /**
     * Validates the rules for Order Number. The order number cannot be null or
     * blank.
     *
     * @param orderNumber the order number to validate
     */
    private void validateOrderNumber(String orderNumber) {
        if (null == orderNumber) {
            throw new IllegalArgumentException("Order Number is required.");
        }
        if (orderNumber.isBlank()) {
            throw new IllegalArgumentException("Order Number cannot be blank.");
        }
    }

    /**
     * Validates the supplied order date. The order date cannot be null or in
     * the future.
     *
     * @param orderDate the order date to validate
     */
    private void validateOrderDate(LocalDate orderDate) {
        if (null == orderDate) {
            throw new IllegalArgumentException("Order Date is required.");
        }
        if (orderDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Order Date cannot be in the future.");
        }
    }

    /**
     * Validates the supplied vendor ID. The vendor ID must be greater than 100.
     *
     * @param vendorId
     */
    private void validateVendorId(int vendorId) {
        if (vendorId < MIN_VENDOR_ID) {
            throw new IllegalArgumentException("Vendor ID cannot be below "
                    + MIN_VENDOR_ID + ".");
        }
    }

    /**
     * A unique, required String that acts as the unique identifier for the
     * order record.
     *
     * @return the orderNumber
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * A unique, required String that acts as the unique identifier for the
     * order record.
     *
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(String orderNumber) {
        validateOrderNumber(orderNumber);
        this.orderNumber = orderNumber;
    }

    /**
     * The required date of the order. New orders default to the current day.
     * Order dates cannot be in the future.
     *
     * @return the orderDate
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * The required date of the order. New orders default to the current day.
     * Order dates cannot be in the future.
     *
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(LocalDate orderDate) {
        validateOrderDate(orderDate);
        this.orderDate = orderDate;
    }

    /**
     * The unique identifier for a vendor. This is an integer value greater than
     * 100.
     *
     * @return the vendorId
     */
    public int getVendorId() {
        return vendorId;
    }

    /**
     * The unique identifier for a vendor. This is an integer value greater than
     * 100.
     *
     * @param vendorId the vendorId to set
     */
    public void setVendorId(int vendorId) {
        validateVendorId(vendorId);
        this.vendorId = vendorId;
    }

    /**
     * Specifies the natural sort order for OrderRecord objects. Sorts by the
     * orderNumber attribute.
     *
     * @param other the other order for comparing
     *
     * @return
     */
    @Override
    public int compareTo(OrderRecord other) {
        return this.orderNumber.compareTo(other.getOrderNumber());
    }

}
