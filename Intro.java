import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONObject;
public class Intro {
    public void TakeInputIntro() {
        
        List<PersonalInfo> pf = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String fname = scanner.nextLine();
        if (fname.trim().isEmpty()) {
            System.out.println("Error: First Name is not specified");
            return;
        }
        System.out.println("Enter Last Name:");
        String lname = scanner.nextLine();
        if (lname.trim().isEmpty()) {
            System.out.println("Error: Last Name is not specified");
            return;
        }
        System.out.println("Enter  Email:");
        String email = scanner.nextLine();
        if (email.trim().isEmpty()) {
            System.out.println("Error: Email is not specified");
            return;
        }
        pf.add(new PersonalInfo(fname,lname, email));
        System.out.println("Enter file Name:");
        String fileName = scanner.nextLine();
    System.out.println("Enter output format (CSV or JSON):");
        String outputFormat = scanner.nextLine();
        if (outputFormat.trim().isEmpty()) {
            System.out.println("Error: Output format not specified");
            return;
            }
        
        
        if (outputFormat.equalsIgnoreCase("csv")) {
            //call method to save to csv
            IntroWriteCSVFile(fileName, pf);
        }
        
         if(outputFormat.equalsIgnoreCase("json")) {
            IntroSaveToJSON(fileName, pf);
        }
        else {
            System.out.println("Invalid format");
        }


    scanner.close();
        
    }


    public static void IntroWriteCSVFile(String fileName, List<PersonalInfo> pf) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(" First Name" + "," + "Last Name" + "," +"email");
            bw.newLine();            
            for (PersonalInfo entry : pf) {
                bw.write(entry.getFname()+"," + entry.getLname() +"," +entry.getEmail());
                bw.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("error ");
        }
    }
    public static void IntroSaveToJSON(String fileName, List<PersonalInfo> pf) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            File jsonFile = new File("Intro_section.json");
            objectMapper.writeValue(jsonFile, pf);
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
class PersonalInfo {
    private String fname;
    private String lname;
    private String email;

    public PersonalInfo(String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
       
    }
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
    public String getEmail() {
        return email;
    }
}