import model.Campaign;
import service.Actions;
import service.DoParseAndActions;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Actions actions = new Actions();

//        if (args.length == 0) {
//            System.out.println("Error- please type a string");
//        } else {
            String myPath = "C:\\Users\\Admin\\Desktop\\example.xlsx"; //args[0];
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
    //}

}
