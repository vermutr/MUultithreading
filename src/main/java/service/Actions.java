package service;

import model.Campaign;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Actions{

    public void howManyLabelsAreThereInCollectionAndCount(List<Campaign> myList){
        Map<String, Long> uniqueLabels=myList
                .stream()
                .collect(Collectors.groupingBy(Campaign::getLabel, Collectors.counting()));
        uniqueLabels.forEach((k,v)-> System.out.println(k + "->" + v));

        System.out.println(uniqueLabels.size());
    }

    public void notRemoved(List<Campaign> myList){
//         myList.stream()
//                .filter(campaign -> !Objects.equals(campaign.getCampaignStatus(), "removed") && !campaign.getGroupStatus().equals("removed"))
//                .forEach(System.out::println);
//        System.out.println(myList.size());
        List<Campaign> campaignList=myList.stream()
                .filter(campaign -> !Objects.equals(campaign.getCampaignStatus(), "removed") && !campaign.getGroupStatus().equals("removed"))
                .collect(Collectors.toList());
        campaignList.forEach(System.out::println);
        System.out.println(campaignList.size());
    }

    public List<Campaign> statusDisabled(List<Campaign> myList){
        return myList.stream()
                .filter(campaign -> Objects.equals(campaign.getTypeIdStatus(), "disabled"))
                .collect(Collectors.toList());
    }


    public void writeToXSLX(List<Campaign> campaignList) throws IOException {

        Path p = Paths.get("C:\\Users\\Admin\\Desktop\\data-file12.xlsx");
        String fileName = p.toString();

        if (!Files.exists(p)) {
            Files.createFile(p);
            Cell cell;
            Row row;

            try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(new File(fileName)))) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("StatusDisabled");
                row = sheet.createRow(0);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Campaign ID");
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Campaign ID status");
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Ad group ID");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Ad group state");
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Ad ID");
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Ad type");
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Labels");
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("Ad state");
                workbook.write(fos);
                workbook.close();
            }
        }
        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(new File(fileName)))) {

            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (Campaign campaign:campaignList){
                Row rows = sheet.createRow(rowCount);
                setCampaignForXslx(campaign, rows);
                rowCount++;
            }

            try (BufferedOutputStream fio = new BufferedOutputStream(new FileOutputStream(fileName))) {
                workbook.write(fio);
            }
        }
    }

    public static void setCampaignForXslx(Campaign campaign, Row rows){
        Cell cell;
        cell = rows.createCell(0, CellType.STRING);
        cell.setCellValue(campaign.getCampaignID());
        cell = rows.createCell(1, CellType.STRING);
        cell.setCellValue(campaign.getCampaignStatus());
        cell = rows.createCell(2, CellType.STRING);
        cell.setCellValue(campaign.getGroupID());
        cell = rows.createCell(3, CellType.STRING);
        cell.setCellValue(campaign.getGroupStatus());
        cell = rows.createCell(4, CellType.STRING);
        cell.setCellValue(campaign.getId());
        cell = rows.createCell(5, CellType.STRING);
        cell.setCellValue(campaign.getStateStatus());
        cell = rows.createCell(6, CellType.STRING);
        cell.setCellValue(campaign.getLabel());
        cell = rows.createCell(7, CellType.STRING);
        cell.setCellValue(campaign.getTypeIdStatus());
    }

    public static Campaign assignCampaign(Row row, Campaign campaign){
        campaign.setCampaignID(row.getCell(0).toString());
        campaign.setCampaignStatus(row.getCell(1).toString());
        campaign.setGroupID(row.getCell(2).toString());
        campaign.setGroupStatus(row.getCell(3).toString());
        campaign.setId(row.getCell(4).toString());
        campaign.setStateStatus(row.getCell(5).toString());
        campaign.setLabel(row.getCell(6).toString());
        campaign.setTypeIdStatus(row.getCell(7).toString());
        return campaign;
    }
    public static Campaign assignCampaignCsv(String[] listOfWords, Campaign campaign){
        campaign.setCampaignID(listOfWords[0]);
        campaign.setCampaignStatus(listOfWords[1]);
        campaign.setGroupID(listOfWords[2]);
        campaign.setGroupStatus(listOfWords[3]);
        campaign.setId(listOfWords[4]);
        campaign.setStateStatus(listOfWords[5]);
        campaign.setLabel(listOfWords[6]);
        campaign.setTypeIdStatus(listOfWords[7]);
        return campaign;
    }

    public String getFileExtension(String myStr) {
        int index = myStr.lastIndexOf('.');
        return index == -1? null : myStr.substring(index);
    }

    public void threadHandling(List<Campaign> objectList){
        ExecutorService executor = Executors.newWorkStealingPool();
        Consumer<List<Campaign>> consumer=data->{

            Future<?> future=executor.submit(()->{
            howManyLabelsAreThereInCollectionAndCount(data);
            notRemoved(data);
            });
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        };
        consumer.accept(objectList);
        executor.shutdown();
    }

}
