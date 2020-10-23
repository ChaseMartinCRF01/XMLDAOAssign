package edu.kcc.java.order.data;

import edu.kcc.java.order.data.csv.OrderRecordDAOCSV;

/**
 * A factory class for getting an implementation of the OrderRecordDAO
 * interface.
 *
 * @author Bob Trapp
 */
public class OrderRecordDAOFactory {

    /**
     * The private instance for the Singleton pattern.
     */
    private static OrderRecordDAOFactory instance;

    /**
     * The private constructor for the Singleton pattern.
     */
    private OrderRecordDAOFactory() {
    }

    /**
     * A method for retrieving the instance.
     *
     * @return the only instance of the factory
     */
    public static OrderRecordDAOFactory getInstance() {
        if (null == instance) {
            instance = new OrderRecordDAOFactory();
        }
        return instance;
    }

    /**
     * Returns the correct implementation of OrderRecordDAO.
     *
     * @return a concrete instance of OrderRecordDAO
     */
    public OrderRecordDAO getDAO() {
        //OrderRecordDAO dao = new OrderRecordDAOCSV();
        OrderRecordDAO dao = new RecordDAOXML();

        return dao;
    }

    //private static final String DAO_SOURCE = "XML";
    /*   private static final String DAO_SOURCE = "CSV";
    
    public static OrderRecordDAO getOrderRecordDAO(){
        OrderRecordDAO dao = null;
        switch(DAO_SOURCE){
            case "CSV":
                dao = new OrderRecordDAOCSV();
                break;
            case "XML":
                //dao = new RecordDAOXML();
                break;
            case "MYSQL":
                
                break;
            default:
        }
        return dao;
    }*/
}
