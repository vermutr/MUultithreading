package service.impl;

import service.Codec;

import java.util.Objects;


public class CodecFactory {
    public static Codec extract(String fileName){
        String extension =Actions.getType(fileName);
        if(Objects.equals(extension, ".xlsx")){
            return new ParserExcelCampaign();
        }
        else{
            return new ParserCsvCampaign();
        }
    }
}
