//take user input for adding or editing details
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
public class Education {

    public void TakeInputEducation() {
        AddEducation();
    }

    public static void AddEducation() {
        Scanner sc1 = new Scanner(System.in);
        List<EducationDetails> educations = new ArrayList<>();
        
        System.out.println("Enter School name:");
    String schoolName = sc1.nextLine();
    if (schoolName.trim().isEmpty()) {
        System.out.println("Error: schoolName not specified");
        return;
    }
    System.out.println("Enter degree name:");
    String degree = sc1.nextLine();
    if (degree.trim().isEmpty()) {
        System.out.println("Error: Degree not specified");
        return;
    }
    System.out.println("Enter field:");
    String field = sc1.nextLine();
    if (field.trim().isEmpty()) {
        System.out.println("Error: Field not specified");
        return;
    }

    educations.add(new EducationDetails(schoolName, degree, field));
    System.out.println("Enter output format (CSV or JSON):");
        String outputFormat = sc1.nextLine();
        if (outputFormat.trim().isEmpty()) {
            System.out.println("Error: Output format not specified");
            return;
            }
        System.out.println("Enter file path:");
        String filePath = sc1.nextLine();
        
        if (outputFormat.equalsIgnoreCase("csv")) {
            //call method to save to csv
            writeCSVFile(filePath, educations);
        }
        
         if(outputFormat.equalsIgnoreCase("json")) {
            EducationSaveToJSON(filePath, educations);
        }
        else {
            System.out.println("Invalid format");
        }
    sc1.close();
    }

    public static void writeCSVFile(String filePath, List<EducationDetails> educations) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("School Name" + "," + "degree" + "," + "field");
            bw.newLine();            
            for (EducationDetails entry : educations) {
                bw.write(entry.getSchool()+"," + entry.getDegree()+","+entry.getField());
                bw.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("error ");
        }
    }
    public static void EducationSaveToJSON(String fileName, List<EducationDetails> educations) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            File jsonFile = new File("Education_section.json");
            objectMapper.writeValue(jsonFile, educations);
            System.out.println("Output saved to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}


class EducationDetails {
    private String schoolName;
    private String degree;
    private String field;

    public EducationDetails(String schoolName, String degree, String field) {
        this.schoolName = schoolName;
        this.degree = degree;
        this.field = field;
    }
    public String getSchool() {
        return schoolName;
    }

    public String getDegree() {
        return degree;
    }
    public String getField() {
        return field;
    }
    public void setSchool(String schoolName) {
         this.schoolName=schoolName;
    }

    public void setDegree(String degree) {
        this.degree=degree;
   }
   public void setField(String field) {
    this.field=field;
}




}