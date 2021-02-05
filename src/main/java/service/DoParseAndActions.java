package service;

import parserFile.ParserCsvCampaign;
import parserFile.ParserExcelCampaign;

public class DoParseAndActions {

    private final ParserExcelCampaign parserExcelCampaign=new ParserExcelCampaign();
    private final ParserCsvCampaign parserCsvCampaign=new ParserCsvCampaign();

    public void excelFile(String myPath){
        parserExcelCampaign.convertFileToObject(myPath);
    }
    public void csvFile(String myPath){
        parserCsvCampaign.convertFileToCsv(myPath);
    }

}
