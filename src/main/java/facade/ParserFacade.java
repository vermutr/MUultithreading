package facade;

import service.Codec;
import service.impl.ParserExcelCampaign;
import service.impl.ProduceParsing;

public class ParserFacade {
    public void ParseFile(String myStr){
        Codec codec = CodecFactory.extract(myStr);
        if(codec.getClass() == ParserExcelCampaign.class){
            ProduceParsing.xlsxFile(myStr,codec);
        }
        else {
            ProduceParsing.csvFile(myStr,codec);
        }


    }
}
