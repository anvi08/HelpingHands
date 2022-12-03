/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;
import java.util.HashMap;
import java.util.*;
/**
 *
 * @author abhis
 */
public final class Constants {
     public static String[] enterpriseList = new String[]{"", "NGO"};
     //HashMap<String, String> ngoOrgAdminList = new HashMap<String, String>("HealthCare","abcd");
     
     //public 
     
    public static Map<String, String> ngoOrgAdminList = Map.of(
        "health", "abcd",
        "disaster", "abcd",
        "education","abcd"
    );
    
    public static ArrayList<String>  donorCountries = (ArrayList<String>) countryMap().get("Donor Countries");
    public static ArrayList<String>  receivingCountries = (ArrayList<String>) countryMap().get("Receiving Countries");
    
    public static Map<String, List<String>> countryMap(){
        Map<String, List<String>> allCountries = new HashMap<>();
        ArrayList<String> donorCountries = new ArrayList<>();
        donorCountries.add(new String("USA"));
        donorCountries.add(new String("Canada")); 
        
        ArrayList<String> receivingCountries = new ArrayList<>();
        receivingCountries.add(new String("India"));
        receivingCountries.add(new String("Uganda"));
        receivingCountries.add(new String("Ukrane"));
        
        allCountries.put("Donor Countries", donorCountries);
        allCountries.put("Receiving Countries", receivingCountries);        

        return allCountries;
    }
    
    
    
//    public static Map<String, List<String>> allCountries = new HashMap<>();  
//    ArrayList<String> donorCountries = new ArrayList<>();
//    donorCountries.add
}
