/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kcc.java.order;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for OrderRecord
 *
 * @author Bob Trapp
 */
public class OrderRecordTest {

    private static final String GOOD_ORDER_NUMBER = "ORDER_NUMBER";
    private static final LocalDate GOOD_ORDER_DATE = LocalDate.now();
    private static final int GOOD_VENDOR_ID = 101;

    public OrderRecordTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    /**
     * Test of toString method, of class OrderRecord.
     */
    @org.junit.jupiter.api.Test
    public void testToString() {
        System.out.println("toString");
        OrderRecord instance = new OrderRecord(
                GOOD_ORDER_NUMBER,GOOD_ORDER_DATE, GOOD_VENDOR_ID);
        String expResult = "orderNumber=" + GOOD_ORDER_NUMBER
                + ", orderDate=" + GOOD_ORDER_DATE
                + ", vendorId=" + GOOD_VENDOR_ID;;
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderNumber method, of class OrderRecord.
     */
    @org.junit.jupiter.api.Test
    public void testGetOrderNumber() {
        System.out.println("getOrderNumber");
        OrderRecord instance = new OrderRecord(
                GOOD_ORDER_NUMBER,GOOD_ORDER_DATE, GOOD_VENDOR_ID);
        String expResult = GOOD_ORDER_NUMBER;
        String result = instance.getOrderNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderNumber method, of class OrderRecord.
     */
    @org.junit.jupiter.api.Test
    public void testSetOrderNumber() {
        System.out.println("setOrderNumber");
        String orderNumber = "TEST";
        OrderRecord instance = new OrderRecord(
                GOOD_ORDER_NUMBER,GOOD_ORDER_DATE, GOOD_VENDOR_ID);
        instance.setOrderNumber(orderNumber);
        assertEquals(orderNumber, instance.getOrderNumber());
    }

    /**
     * Test of getOrderDate method, of class OrderRecord.
     */
    @org.junit.jupiter.api.Test
    public void testGetOrderDate() {
        System.out.println("getOrderDate");
        OrderRecord instance = new OrderRecord(
                GOOD_ORDER_NUMBER,GOOD_ORDER_DATE, GOOD_VENDOR_ID);
        LocalDate expResult = GOOD_ORDER_DATE;
        LocalDate result = instance.getOrderDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOrderDate method, of class OrderRecord.
     */
    @org.junit.jupiter.api.Test
    public void testSetOrderDate() {
        System.out.println("setOrderDate");
        LocalDate orderDate = LocalDate.now().minusDays(2);
        OrderRecord instance = new OrderRecord(
                GOOD_ORDER_NUMBER,GOOD_ORDER_DATE, GOOD_VENDOR_ID);
        instance.setOrderDate(orderDate);
        assertEquals(orderDate, instance.getOrderDate());
    }

    /**
     * Test of getVendorId method, of class OrderRecord.
     */
    @org.junit.jupiter.api.Test
    public void testGetVendorId() {
        System.out.println("getVendorId");
        OrderRecord instance = new OrderRecord(
                GOOD_ORDER_NUMBER,GOOD_ORDER_DATE, GOOD_VENDOR_ID);
        int expResult = GOOD_VENDOR_ID;
        int result = instance.getVendorId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVendorId method, of class OrderRecord.
     */
    @org.junit.jupiter.api.Test
    public void testSetVendorId() {
        System.out.println("setVendorId");
        int vendorId = GOOD_VENDOR_ID + 10;
        OrderRecord instance = new OrderRecord(
                GOOD_ORDER_NUMBER,GOOD_ORDER_DATE, GOOD_VENDOR_ID);
        instance.setVendorId(vendorId);
        assertEquals(vendorId, instance.getVendorId());
    }

    /**
     * Test of compareTo method, of class OrderRecord.
     */
    @org.junit.jupiter.api.Test
    public void testCompareTo() {
        System.out.println("compareTo");
        OrderRecord other = new OrderRecord(
                GOOD_ORDER_NUMBER,GOOD_ORDER_DATE, GOOD_VENDOR_ID);
        OrderRecord instance = new OrderRecord(
                GOOD_ORDER_NUMBER,GOOD_ORDER_DATE, GOOD_VENDOR_ID);
        int expResult = 0;
        int result = instance.compareTo(other);
        assertEquals(expResult, result);
    }

}
