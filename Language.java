//take user input for adding or editing details
//School, degree, field, description
//School, degree, field, description
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONObject;
public class Language {
    public void TakeInputLang() {
        
        List<Lang> lang = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Language:");
        String language = scanner.nextLine();
        System.out.println("Enter Proficiency out of the following:");
        System.out.println("Elementary:");
        System.out.println("Limited Working:");
        System.out.println("Professional working:");
        System.out.println("Full Professional:");
        System.out.println("Native or Bilingual:");
        String proficiency = scanner.nextLine();
      
        lang.add(new Lang(language,proficiency));
    System.out.println("Enter output format (CSV or JSON):");
        String outputFormat = scanner.nextLine();
        if (outputFormat.trim().isEmpty()) {
            System.out.println("Error: Output format not specified");
            return;
            }
        System.out.println("Enter file path:");
        String filePath = scanner.nextLine();
        
        if (outputFormat.equalsIgnoreCase("csv")) {
            //call method to save to csv
            LangWriteCSVFile(filePath, lang);
        }
        
         if(outputFormat.equalsIgnoreCase("json")) {
            LCSaveToJSON(filePath, lang);
        }
        else {
            System.out.println("Invalid format");
        }


    scanner.close();
        
    }


    public static void LangWriteCSVFile(String filePath, List<Lang> lang) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(" Language" + "," + "Proficiency");
            bw.newLine();            
            for (Lang entry : lang) {
                bw.write(entry.getlanguage()+"," + entry.getProficiency());
                bw.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("error ");
        }
    }
    public static void LCSaveToJSON(String fileName, List<Lang> lang) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            File jsonFile = new File("Language_section.json");
            objectMapper.writeValue(jsonFile, lang);
            System.out.println("Output saved to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    /* 
    static void addLang(String filepath, String language, String proficiency) {
        try (FileWriter writer = new FileWriter(filepath)) {
            //Write the header row
                writer.append("Language, Proficiency,\n");
                writer.append(language).append(",").append(proficiency).append("\n");
                writer.flush();
                System.out.println("Csv file saved");
        }
        catch (IOException e) {
            System.out.println("An error occured");
            
        }
    }
    */
    
}
class Lang {
    private String language;
    private String proficiency;

    public Lang(String language, String proficiency) {
        this.language = language;
        this.proficiency = proficiency;
       
    }
    public String getlanguage() {
        return language;
    }

    public String getProficiency() {
        return proficiency;
    }

}