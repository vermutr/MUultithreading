import model.Campaign;
import service.Actions;
import service.DoParseAndActions;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Actions actions = new Actions();

        if (args.length == 0) {
            System.out.println("Error- please type a string");
        } else {
            String myPath = args[0];  //"C:\\Users\\Admin\\Desktop\\example.xlsx";
            String extension = actions.getFileExtension(myPath);
            switch (extension) {
                case ".xlsx":
                    new DoParseAndActions().excelFile(myPath);
                    break;
                case ".csv":
                    new DoParseAndActions().csvFile(myPath);
                    break;
            }
        }
    }

}
