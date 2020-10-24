package edu.kcc.java.order.data;

import edu.kcc.java.order.OrderRecord;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Chase
 */
public class RecordDAOXML implements OrderRecordDAO {

    private static final String FILE_NAME = "order_records.xml";
    private static ArrayList<OrderRecord> recordList;

    private void readFromFile() throws OrderRecordDataException {
        try ( InputStream inputStream = new FileInputStream(FILE_NAME)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(inputStream);

            NodeList recordNodeList = document.getElementsByTagName("record");
            recordList = new ArrayList<>();
            for (int i = 0; i < recordNodeList.getLength(); i++) {
                Node currentRecordNode = recordNodeList.item(i);
                recordList.add(buildRecordFromNode(currentRecordNode));
            }
        } catch (Exception ex) {
            throw new OrderRecordDataException(ex);
        }
    }

    public static OrderRecord buildRecordFromNode(Node recordNode) {
        OrderRecord newRecord = new OrderRecord();
        
         NamedNodeMap carAttributeMap = recordNode.getAttributes();
         Attr attr = (Attr)carAttributeMap.getNamedItem("order-number");
         newRecord.setOrderNumber(attr.getValue());
         NodeList recordDataNodeList = recordNode.getChildNodes();
        
        for (int i = 0; i < recordDataNodeList.getLength(); i++) {
            Node dataNode = recordDataNodeList.item(i);
             if(dataNode instanceof Element) {
            Element dataElement = (Element)dataNode;
            switch(dataElement.getTagName()) {
                case "orderDate":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate orderDateValue = LocalDate.parse(dataElement.getTextContent(), formatter);
                    newRecord.setOrderDate(orderDateValue);
                    break;
                case "vendorId":
                    int vendorIdValue = Integer.parseInt(dataElement.getTextContent());
                    newRecord.setVendorId(vendorIdValue);
                    break;
                default:
                    break;
          }     
        }
      }
         return newRecord;
    }

    private void saveToFile() throws OrderRecordDataException {
        try(FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
              DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            
              DocumentBuilder builder = factory.newDocumentBuilder();
              Document document = builder.newDocument();              
              Element rootElement = document.createElement("recordList");
              document.appendChild(rootElement);  
              
              
              
            for (OrderRecord currentRecord : recordList) {
                DocumentFragment recordFragment = buildRecordFragment(document, currentRecord);
                rootElement.appendChild(recordFragment);
            }
              
              DOMSource source = new DOMSource(document);
            
              TransformerFactory transformerFactory = TransformerFactory.newInstance();
              Transformer transformer = transformerFactory.newTransformer();      
              transformer.transform(source, new StreamResult(fos));
              
      } catch(Exception ex) {
        throw new OrderRecordDataException(ex);
      }
    }
    
    private static DocumentFragment buildRecordFragment(Document document, OrderRecord record) {
    DocumentFragment recordFragment = document.createDocumentFragment();
        
    Element recordElement = document.createElement("record");
    recordElement.setAttribute("order-number", record.getOrderNumber());
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    Element orderDateElement = document.createElement("orderDate");          
    orderDateElement.setTextContent(record.getOrderDate().format(formatter));
    recordElement.appendChild(orderDateElement);
          
    Element vendorIdElement = document.createElement("vendorId");
    vendorIdElement.setTextContent(Integer.toString(record.getVendorId()));
    recordElement.appendChild(vendorIdElement);
        
    recordFragment.appendChild(recordElement);
    return recordFragment;
}

    private void verifyData() throws OrderRecordDataException {
        if (null == recordList) {
            readFromFile();
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
        if (null != existingRecord) {
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
            if (orderRecord.getOrderNumber().equals(orderNumber)) {
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
        if (null == existingRecord) {
            throw new OrderRecordDataException(
                    "The original record does not exist.");
        } else {
            if (!existingRecord.getOrderDate().equals(original.getOrderDate())) {
                throw new OrderRecordDataException(
                        "The original record order date does not match the"
                        + " data store.");
            }
            if (existingRecord.getVendorId() != original.getVendorId()) {
                throw new OrderRecordDataException(
                        "The original record vendor ID does not match the"
                        + " data store.");
            }
            // All tests have passed, so do the update
            // Remember, "existingRecord" is a copy, not the one from the list
            for (OrderRecord orderRecord : recordList) {
                if (orderRecord.getOrderNumber()
                        .equals(original.getOrderNumber())) {
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
            if (orderRecord1.getOrderNumber()
                    .equals(orderRecord.getOrderNumber())) {
                recordList.remove(orderRecord1);
                break;
            }
        }
        saveToFile();
    }
}
