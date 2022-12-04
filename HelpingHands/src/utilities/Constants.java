/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;
//import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author abhis
 */
public final class Constants {
     public static String[] enterpriseList = new String[]{"", "NGO", "Bank"};
     public static String profileRoleJustice = "justiceDept";

     
     //public 
     
    public static Map<String, String> ngoOrgAdminList = Map.of(
        "health", "abcd",
        "disaster", "abcd",
        "education","abcd"
    );
    
    public static ArrayList<String>  donorCountries = (ArrayList<String>) systemMap().get("Donor Countries");
    public static ArrayList<String>  receivingCountries = (ArrayList<String>) systemMap().get("Receiving Countries");
    public static ArrayList<String>  ngoOrganisations = (ArrayList<String>) systemMap().get("NGO Organisations");
    public static ArrayList<String>  receivingType = (ArrayList<String>) systemMap().get("Receiving Type");
    public static ArrayList<String>  donorType = (ArrayList<String>) systemMap().get("Donor Type");
    public static ArrayList<String> userType = (ArrayList<String>) systemMap().get("User Type");

    
    public static Map<String, List<String>> systemMap(){
        Map<String, List<String>> allMaps = new HashMap<String, List<String>>();
        
        ArrayList<String> donorCountries = new ArrayList<>();
        donorCountries.add(new String("USA"));
        donorCountries.add(new String("Canada")); 
        
        ArrayList<String> receivingCountries = new ArrayList<>();
        receivingCountries.add(new String("India"));
        receivingCountries.add(new String("Uganda"));
        receivingCountries.add(new String("Kenya"));

        ArrayList<String> ngoOrg = new ArrayList<>();
        ngoOrg.add("Healthcare");
        ngoOrg.add("Natural Disasters");
        ngoOrg.add("Education");

        ArrayList<String> receivingType = new ArrayList<>();
        receivingType.add("Individual");
        receivingType.add("Community");

        ArrayList<String> donorType = new ArrayList<>();
        donorType.add("Individual");
        donorType.add("Organisation");

        ArrayList<String> userType = new ArrayList<>();
        userType.add("Donor");
        userType.add("Receiver");
        
        allMaps.put("Donor Countries", donorCountries);
        allMaps.put("Receiving Countries", receivingCountries);        
        allMaps.put("NGO Organisations", ngoOrg);
        allMaps.put("Receiving Type", receivingType);
        allMaps.put("Donor Type", donorType);
        allMaps.put("User Type", userType);
        
        return allMaps;
    }
    
    public static String[] empType = new String[] {"ADMIN","EMPLOYEE"};
    
    
    
//    public static Map<String, List<String>> allCountries = new HashMap<>();  
//    ArrayList<String> donorCountries = new ArrayList<>();
//    donorCountries.add
}
