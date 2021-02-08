import facade.ParserFacade;
import service.impl.ParserCsvCampaign;
import service.impl.ParserExcelCampaign;
import service.impl.Actions;

public class Main {
    public static void main(String[] args) {

        Actions actions = new Actions();
//
//        if (args.length == 0) {
//            System.out.println("Error- please type a string");
//        } else {
            String myPath = "C:\\Users\\Admin\\Desktop\\example.xlsx"; //args[0];
            ParserFacade parserFacade=new ParserFacade();
            parserFacade.ParseFile(myPath);
//            String extension = actions.getFileExtension(myPath);
//            switch (extension) {
//                case ".xlsx":
//                    new ParserExcelCampaign().convertFileFromExcel(myPath);
//                    break;
//                case ".csv":
//                    new ParserCsvCampaign().convertFileFromCsv(myPath);
//                    break;
//            }
        }
    }



