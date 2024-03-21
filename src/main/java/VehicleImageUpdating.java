import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class VehicleImageUpdating {
    private static String pathName="C:\\Users\\z042349\\Downloads\\";
    private static String fileName="f16_2";
    private static final String CSV_FILE_PATH =  pathName+fileName+".csv";
    private static final String OUTPUT_FILE_PATH =  pathName+fileName+".txt";
    private static final String INSERT_QUERY = " UPDATE public.vehicle_image_mapping ";
  //  private static final String WHERE_CAUSE = " WHERE color_code ";

    private static final String TAG = "vehicle_image_mapping- ";
//color_code	color_name	model_code	vehicle_type	picture url  excel header format
    public static void main(String[] args) throws IOException {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder(INSERT_QUERY);
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
                 if (count == 0) {
                    count++;
                    //its taking header title also
                    continue;
                }
                String colorCode = csvRecord.get(0);
                String colorName = csvRecord.get(1);
                String modelCode = csvRecord.get(2);
                String vehicleType = csvRecord.get(3);
                String pictureUrl = csvRecord.get(4);
                stringBuilder.append("SET picture='"+pictureUrl+"'");
                //stringBuilder.append("WHERE color_code,color_name,model_code,vehicleType IN('"+colorCode+colorName+modelCode+vehicleType)";
                stringBuilder.append(" WHERE color_code='"+colorCode+"' and color_name='"+colorName+"' and model_code='"+
                        modelCode+ "' and vehicle_type='"+vehicleType+"'),\n");

            }

            try {
                File myObj = new File(OUTPUT_FILE_PATH);
                if (myObj.createNewFile()) {
                    System.out.println(TAG + "File created: " + myObj.getName());
                } else {
                    System.out.println(TAG + "File already exists.");
                }
                FileWriter myWriter = new FileWriter(myObj);
                myWriter.write(String.valueOf(stringBuilder));
                myWriter.close();

                System.out.println(TAG + "File created successfully");
            } catch (IOException e) {
                System.out.println(TAG + "An error occurred.");
                e.printStackTrace();
            }
        }


    }
}
