package service.impl;

import service.Codec;

public class ProduceParsing {
    public static void csvFile(String myStr, Codec codec){
        ParserCsvCampaign parserCsvCampaign = (ParserCsvCampaign) codec;
        parserCsvCampaign.convertFileFromCsv(myStr);
    }

    public static void xlsxFile(String myStr, Codec codec){
        ParserExcelCampaign parserExcelCampaign = (ParserExcelCampaign) codec;
        parserExcelCampaign.convertFileFromExcel(myStr);
    }

}
