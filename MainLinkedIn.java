import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


public class MainLinkedIn {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Choose Profile section ");
    System.out.println("1. Intro ");
   // System.out.println("2. About ");
    System.out.println("2. Education ");
    System.out.println("3. Experience ");
    System.out.println("4. License and Certifications ");
   // System.out.println("6. Volunteering ");
    //System.out.println("7. Courses ");
    System.out.println("5. Languages ");
    String option = scanner.nextLine();
    //scanner.close();
    if ("1".equalsIgnoreCase(option)) {
        Intro intro = new Intro();
        intro.TakeInputIntro();
        }
    /* 
    if ("2".equalsIgnoreCase(option)) {
        About about = new About();
        about.TakeInputAbout();
        }
        */
    if ("2".equalsIgnoreCase(option)) {
    Education education = new Education();
    education.TakeInputEducation();
    }

    else if("3".equalsIgnoreCase(option)) {
        Experience experience = new Experience();
        experience.TakeInputExp();
    }
    
    else if("4".equalsIgnoreCase(option)) {
        LicensesCertifications lc = new LicensesCertifications();
        lc.TakeInputLC();
    
    }
    /* 
    else if("6".equalsIgnoreCase(option)) {
        Volunteering vol = new Volunteering();
        vol.TakeInputVol();
    }
    else if("7".equalsIgnoreCase(option)) {
        Courses course = new Courses();
        course.TakeInputCourse();
    }
    */
    else if("5".equalsIgnoreCase(option)) {
        Language lang = new Language();
        lang.TakeInputLang();
    }
    else {
        System.out.println("No option chosen");
    }
    
}
}