import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SVTColorMapping {


    private static final String CSV_FILE_PATH = "C:\\Users\\z042349\\Downloads\\svt color.csv";
    private static final String OUTPUT_FILE_PATH = "C:\\Users\\z042349\\Downloads\\svt_color.txt";
    private static final String INSERT_QUERY = "INSERT INTO public.model_color_mapping_multilanguage(id, languages, color_name, translation) VALUES";
    private static final String TAG = "SVTColorMapping- ";

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
                String languages = csvRecord.get(1);
                String color_name = csvRecord.get(2);
                String translation = csvRecord.get(3);
                 stringBuilder.append("(").append(id).append(",'").append(languages).append("','").append(color_name).append("','").append(translation).append("'),\n");
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
