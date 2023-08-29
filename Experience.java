//take user input for adding or editing details
//School, degree, field, description
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
public class Experience {
    public void TakeInputExp() {
    List<Positions> pos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter Title:");
    String title = scanner.nextLine();
    if (title.trim().isEmpty()) {
        System.out.println("Error: Title is not specified");
        return;
    }
    System.out.println("Enter Employement type (Full Time/ Freelance/ Temporary):");
    String employmentType = scanner.nextLine();
    if (employmentType.trim().isEmpty()) {
        System.out.println("Error: Employment type is not specified");
        return;
    }
    System.out.println("Enter company:");
    String company = scanner.nextLine();
    if (company.trim().isEmpty()) {
        System.out.println("Error: Company is not specified");
        return;
    }
    System.out.println("Enter location:");
    String location = scanner.nextLine();
    if (location.trim().isEmpty()) {
        System.out.println("Error: Location is not specified");
        return;
    }
    System.out.println("Enter start date in the format (Year-month-day):");
    String startDate = scanner.nextLine();
    int startYear= ExtractYear(startDate);
    if (startDate.trim().isEmpty()) {
        System.out.println("Error: Start date is not specified");
        return;
    }
    System.out.println("Choose if the position is current/past:");
    String status = scanner.nextLine();
    System.out.println("Enter End date/expected year in the format (Year-month-day):");
    String endDate = scanner.nextLine();
    if(status.equalsIgnoreCase("past")) {
    int endYear= ExtractYear(endDate);
    if (endDate.trim().isEmpty()) {
        System.out.println("Error: End date is not specified");
        return;
    }
    else if(endYear< startYear) {
        System.out.println("Error: Incorrect end date.");   
        return;
    }
    }
    pos.add(new Positions(title, employmentType, company, location, startDate, endDate));
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
            ExpWriteCSV(filePath, pos);
        }
        
         if(outputFormat.equalsIgnoreCase("json")) {
            ExpSaveToJSON(filePath, pos);
        }
        else {
            System.out.println("Invalid format");
        }
    scanner.close();
    
    //Experience.AddExperience(fileName, title, employmentType, company, location, startDate, endDate);
  
    }
    
    public static void ExpSaveToJSON(String fileName, List<Positions> pos) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            File jsonFile = new File("Experience_section.json");
            objectMapper.writeValue(jsonFile, pos);
            System.out.println("Output saved to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }


    public static void ExpWriteCSV(String filePath, List<Positions> pos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("title" + ","+ "employmentType" + "," + "company" + ","+ "location"+ ","+ "startDate"+ ","+ "endDate");
            bw.newLine();            
            for (Positions entry : pos) {
                bw.write(entry.getTitle()+"," + entry.getEmploymentType()+","+entry.getCompany()+","+entry.getLocation()+","+entry.getStartDate()+","+entry.getEndDate());
                bw.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("error ");
        }
    }
    public static int ExtractYear(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        return date.getYear();
    }
    
}
class Positions {
    private String title;
    private String employmentType;
    private String company;
    private String location;
    private String startDate;
    private String endDate;

    public Positions(String title, String employmentType, String company, String location, String startDate, String endDate) {
        this.title = title;
        this.employmentType = employmentType;
        this.company = company;
        this.location=location;
        this.startDate= startDate;
        this.endDate=endDate;
    }
    public String getTitle() {
        return title;
    }

    public String getEmploymentType() {
        return employmentType;
    }
    public String getCompany() {
        return company;
    }
    public String getLocation() {
        return location;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }

}