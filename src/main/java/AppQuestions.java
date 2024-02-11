import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AppQuestions {
    private static String pathName="C:\\Users\\z042349\\Downloads\\";
    private static String fileName="app_questions";
    private static final String CSV_FILE_PATH =  pathName+fileName+".csv";
    private static final String OUTPUT_FILE_PATH =  pathName+fileName+".txt";
    private static final String INSERT_QUERY = "INSERT INTO public.app_questions(id, created_at, updated_at, active, question, brand, language, region) VALUES ";
    private static final String TAG = "VehicleServiceMapping- ";

    //must add details mapping  and action mapping


    public static void main(String[] args) throws IOException {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder(INSERT_QUERY);
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
               /* if (count == 0) {
                    count++;
                    //its taking header title also
                    continue;
                }*/
                //question, brand, language, region
                String id = csvRecord.get(0);
                String created_at = csvRecord.get(1);
                String updated_at = csvRecord.get(2);
                String active = csvRecord.get(3);
                String question = csvRecord.get(4);
                String brand = csvRecord.get(5);
                String language = csvRecord.get(6);
                String region = csvRecord.get(7);
                stringBuilder.append("(").append(id).append(",'").append(created_at).append("','").append(updated_at).append("','")
                        .append(active).append("','").append(question).append("','").append(brand).append("','")
                        .append(language).append("','").append(region).append("'),\n");
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
