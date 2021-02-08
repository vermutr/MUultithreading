package service.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import model.Campaign;
import service.Codec;
import service.impl.Actions;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ParserCsvCampaign implements Codec{

    private final Actions actions=new Actions();
    private final List<Campaign> myList = new ArrayList<>();

    public void convertFileFromCsv(String file){
        try {

            Reader reader = new FileReader(file);
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreQuotations(true)
                    .build();
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(0)
                    .withCSVParser(parser)
                    .build();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                Campaign campaign=new Campaign();
                myList.add(Actions.assignCampaignCsv(line,campaign));
                if(myList.size()==10){
                    actions.threadHandling(myList);
                    myList.clear();
                }
            }
            reader.close();
            csvReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
