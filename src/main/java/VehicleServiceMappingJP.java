import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class VehicleServiceMappingJP {
    private static String pathName="C:\\Users\\z042349\\Downloads\\";
    private static String fileName="Book3";
    private static final String CSV_FILE_PATH =  pathName+fileName+".csv";
    private static final String OUTPUT_FILE_PATH =  pathName+fileName+".txt";
    private static final String INSERT_QUERY = "INSERT INTO public.vehicle_service_mapping(id, model_code, service_id, service_name, service_type, ivi_type) VALUES";
    private static final String TAG = "VehicleServiceMapping- ";

    //must add details mapping  and action mapping


    public static void main(String[] args) throws IOException {
        int count = 0;
         StringBuilder stringBuilder = new StringBuilder(INSERT_QUERY);
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {

                String id = csvRecord.get(0);
                String modelCode = csvRecord.get(1);
                String serviceId = csvRecord.get(2);
                String serviceName = csvRecord.get(3);
                String serviceType = csvRecord.get(4);
                String iviType = csvRecord.get(5);
                stringBuilder.append("(").
                        append(id).append(",'").
                        append(iviType).append("','").
                        append(modelCode).append("','").
                        append(serviceId).append("','").
                        append(serviceName).append("','").
                        append(serviceType).append("'),\n");
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
