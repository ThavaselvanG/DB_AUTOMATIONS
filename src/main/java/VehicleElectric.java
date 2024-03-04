import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class VehicleElectric {
    private static String pathName="C:\\Users\\z042349\\Downloads\\";
    private static String fileName="consumption";
    private static final String CSV_FILE_PATH =  pathName+fileName+".csv";
    private static final String OUTPUT_FILE_PATH =  pathName+fileName+".txt";
    private static final String INSERT_QUERY = "INSERT INTO public.electric_consumption(\n" +
            "\tid, consumption, slope, speed)\n" +
            "\tVALUES";
    private static final String TAG = "consumption- ";

    //must add details mapping  and action mapping


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
                String modelCode = csvRecord.get(1);
                String serviceId = csvRecord.get(2);
                String serviceName = csvRecord.get(3);
                String serviceType = csvRecord.get(4);

                stringBuilder.append("(").append(id).append(",'").append("','").append(serviceName).append("','")
                        .append(serviceType).append("','").append(serviceType).append("'),\n");
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
