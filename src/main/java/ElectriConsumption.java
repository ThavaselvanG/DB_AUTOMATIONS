import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ElectriConsumption {
    private static String pathName = "C:\\Users\\z042349\\Downloads\\";
    private static String fileName = "testttt";
    private static final String CSV_FILE_PATH = pathName + fileName + ".csv";
    private static final String OUTPUT_FILE_PATH = pathName + fileName + ".txt";
    private static final String INSERT_QUERY = " INSERT INTO public.electric_consumption_consumption(id, speed_id, speed, slope, consumption) VALUES ";
    private static final String TAG = "VehicleServiceMapping- ";

    //must add details mapping  and action mapping

    static int idPrevoius = 4331;
    static int speedId = 71;

    public static void main(String[] args) throws IOException {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder(INSERT_QUERY);
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            int firstPos = 1;
            int secondPos = 2;
            int thirdPos = 3;
            int startPos = 1;
            speedId++;
      /*   for (int i = startPos; i < 20; i++) {
             //System.out.println(i);
              firstPos = firstPos + 4;
             secondPos = secondPos + 4;
             thirdPos = thirdPos + 4;
*/
             System.out.println(firstPos);
             System.out.println(secondPos);
             System.out.println(thirdPos);
             for (CSVRecord csvRecord : csvParser) {
                 String speed = csvRecord.get(0);
                 String slope = csvRecord.get(1);
                 String consumption = csvRecord.get(2);
                 //if (consumption != null && !consumption.isEmpty()) {
                 idPrevoius++;
                 int id = idPrevoius;
                 stringBuilder.append("(").append(id).append(",'").append(speedId).append("','")
                         .append(speed).append("','")
                         .append(slope).append("','").append(consumption).append("'),\n");
                 //}

            // }

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
                speedId++;
                System.out.println(TAG + "File created successfully");
            } catch (IOException e) {
                System.out.println(TAG + "An error occurred.");
                e.printStackTrace();
            }
        }


    }
}
