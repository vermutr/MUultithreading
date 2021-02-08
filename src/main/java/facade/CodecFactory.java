package facade;

import service.Codec;
import service.impl.Actions;
import service.impl.ParserCsvCampaign;
import service.impl.ParserExcelCampaign;

import java.util.Objects;


public class CodecFactory {
    public static Codec extract(String fileName){
        String extension = Actions.getType(fileName);
        if(Objects.equals(extension, ".xlsx")){
            return new ParserExcelCampaign();
        }
        else{
            return new ParserCsvCampaign();
        }
    }
}
