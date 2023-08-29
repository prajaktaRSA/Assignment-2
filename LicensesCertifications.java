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
public class LicensesCertifications {
    public void TakeInputLC() {
        List<LicenseCert> lc = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter Name of the License/Certifictions:");
    String name = scanner.nextLine();
    if (name.trim().isEmpty()) {
        System.out.println("Error: Name not specified");
        return;
    }
    System.out.println("Enter issuing Organization name:");
    String issuingOrganization = scanner.nextLine();
    if (issuingOrganization.trim().isEmpty()) {
        System.out.println("Error: Issuing organization not specified");
        return;
    }
    System.out.println("Enter Issue Date:");
    String issueDate = scanner.nextLine();
    if (issueDate.trim().isEmpty()) {
        System.out.println("Error: Issuing date is not specified");
        return;
    }
    System.out.println("Enter Credential URL:");
    String credentialURL = scanner.nextLine();
   

    lc.add(new LicenseCert(name, issuingOrganization, issueDate,credentialURL));
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
            LCWriteCSVFile(filePath, lc);
        }
        
         if(outputFormat.equalsIgnoreCase("json")) {
            LCSaveToJSON(filePath, lc);
        }
        else {
            System.out.println("Invalid format");
        }


    scanner.close();
    }

    public static void LCWriteCSVFile(String filePath, List<LicenseCert> lc) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(" Name" + "," + "issuingOrganization" + "," + "issueDate" + "," +"credentialURL");
            bw.newLine();            
            for (LicenseCert entry : lc) {
                bw.write(entry.getName()+"," + entry.getIssuingOrganization()+","+entry.getIssueDate() +","+entry.getCredentialURL());
                bw.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("error ");
        }
    }
    public static void LCSaveToJSON(String fileName, List<LicenseCert> lc) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            File jsonFile = new File("LicensesCertifications_section.json");
            objectMapper.writeValue(jsonFile, lc);
            System.out.println("Output saved to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
/* 
    static void addLC(String filepath, String name, String issuingOrganization, String issueDate, String credentialURL) {
        try (FileWriter writer = new FileWriter(filepath)) {
            //Write the header row
                writer.append("name, issuingOrganization, issueDate, expirationDate, credentialURL\n");
                writer.append(name).append(",").append(issuingOrganization).append(issueDate).append(credentialURL).append("\n");
                writer.flush();
                System.out.println("Csv file saved");
        }
        catch (IOException e) {
            System.out.println("An error occured");
            
        }
    }
    */
    
}

class LicenseCert {
    private String name;
    private String issuingOrganization;
    private String issueDate;
    private String credentialURL;
  

    public LicenseCert(String name, String issuingOrganization, String issueDate, String credentialURL) {
        this.name = name;
        this.issuingOrganization = issuingOrganization;
        this.issueDate = issueDate;
        this.credentialURL=credentialURL;
       
    }
    public String getName() {
        return name;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }
    public String getIssueDate() {
        return issueDate;
    }
    public String getCredentialURL() {
        return credentialURL;
    }
   

}