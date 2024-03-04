import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CountryCode {
    private static String pathName="C:\\Users\\z042349\\Downloads\\";
    private static String fileName="Book3";
    private static final String CSV_FILE_PATH =  pathName+fileName+".csv";
    private static final String OUTPUT_FILE_PATH =  pathName+fileName+".txt";
    private static final String INSERT_QUERY = "INSERT INTO public.country_code(id, code, name, country_code) VALUES";
    private static final String TAG = "VehicleServiceMapping- ";

    //must add details mapping  and action mapping


    public static void main(String[] args) throws IOException {
        int count = 0;
         StringBuilder stringBuilder = new StringBuilder(INSERT_QUERY);
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {

                String id = csvRecord.get(0);
                String code = csvRecord.get(1);
                String name = csvRecord.get(2);
                String country_code = csvRecord.get(3);

                stringBuilder.append("(").append(id).append(",'").append(code).append("','").append(name).append("','").append(country_code).append("'),\n");
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
