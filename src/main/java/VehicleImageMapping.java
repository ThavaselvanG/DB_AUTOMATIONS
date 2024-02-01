import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class VehicleImageMapping {
    private static String pathName="C:\\Users\\z042349\\Downloads\\";
    private static String fileName="j12_24_img";
    private static final String CSV_FILE_PATH =  pathName+fileName+".csv";
    private static final String OUTPUT_FILE_PATH =  pathName+fileName+".txt";
    private static final String INSERT_QUERY = " INSERT INTO public.vehicle_image_mapping(\n" +
            "\tid, active, color_code, color_name, model_code, picture_url, vehicle_type) VALUES";
    private static final String TAG = "vehicle_image_mapping- ";

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
                String id = csvRecord.get(0);
                String active = csvRecord.get(1);
                String color_code = csvRecord.get(2);
                String color_name = csvRecord.get(3);
                if (color_name.equals(""))
                    color_name=null;
                String model_code = csvRecord.get(4);
                String picture_url = csvRecord.get(5);
                String vehicle_type = csvRecord.get(6);
                if (vehicle_type.equals(""))
                    vehicle_type=null;
                stringBuilder.append("(").append(id).append(",'").append(active).append("','").append(color_code).append("','")
                        .append(color_name).append("','").append(model_code).append("','"). append(picture_url).append("','")
                        .append(vehicle_type).append("'),\n");
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
