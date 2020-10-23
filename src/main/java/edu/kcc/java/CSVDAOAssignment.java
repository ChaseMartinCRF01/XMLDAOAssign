package edu.kcc.java;

import edu.kcc.java.order.data.OrderRecordDAO;
import edu.kcc.java.order.data.OrderRecordDAOFactory;
import edu.kcc.java.order.tasks.AddOrderRecordHandler;
import edu.kcc.java.order.tasks.DeleteOrderRecordHandler;
import edu.kcc.java.order.tasks.FindOrderRecordHandler;
import edu.kcc.java.order.tasks.ShowAllOrderRecordsHandler;
import edu.kcc.java.order.tasks.UpdateOrderRecordHandler;
import edu.kcc.java.ui.UIUtility;

/**
 * The primary class for the CSV and DAO Assignment.  This is an example
 * solution for the homework assignment.
 *
 * @author Bob Trapp
 */
public class CSVDAOAssignment {


    /**
     * The main() method is the starting point for the program.  In this case,
     * the main() method creates some local variables, and then loops through a
     * menu until the user chooses to end the program.  All other menu options
     * demonstrate a CRUD function using the Data Access Object (DAO) design
     * pattern.
     *
     * @param args the command line arguments, not used in this program
     */
    public static void main(String[] args) {
        UIUtility.showMessage("Program starting...");

        // Define variables
        String menuTitle = "Main Menu";
        String[] menuOptions = {
            "1) Add an Order Record",
            "2) Find an Order Record",
            "3) Show All Order Records",
            "4) Update an Order Record",
            "5) Delete an Order Record",
            "6) Exit"
        };
        String prompt = "Your choice:";
        String errorMessage = "Invalid option.  Please try again.";
        String userChoice;
        OrderRecordDAO dao = OrderRecordDAOFactory.getInstance().getDAO();

        // Start the primary program logic
        boolean working = true;
        while (working) {
            userChoice = UIUtility.showMenuOptions(menuTitle,
                    prompt, menuOptions);
            switch (userChoice) {
                case "1":
                    new AddOrderRecordHandler().startTask(dao);
                    break;
                case "2":
                    new FindOrderRecordHandler().startTask(dao);
                    break;
                case "3":
                    new ShowAllOrderRecordsHandler().startTask(dao);
                    break;
                case "4":
                    new UpdateOrderRecordHandler().startTask(dao);
                    break;
                case "5":
                    new DeleteOrderRecordHandler().startTask(dao);
                    break;
                case "6":
                    working = false;
                    break;
                default:
                    UIUtility.showErrorMessage(errorMessage, true);
            }
        }
        UIUtility.showMessage("\nProgram complete.");
    }
}
