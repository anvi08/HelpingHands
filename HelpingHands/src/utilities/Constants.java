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
     public static String profileRoleBank = "bankDept";
     //public static String[] empType = new String[]{"Admin", "Employee"};

     
     //public 
     
    public static Map<String, String> ngoOrgAdminList = Map.of(
        "health", "abcd",
        "disaster", "abcd",
        "education","abcd"
    );
    
    public static Map<String, String> bankDonorCountryList = Map.of(
        "USA", "Bank of America",
        "Canada", "National bank of Canada"
    );
    
    public static ArrayList<String>  donorCountries = (ArrayList<String>) countryMap().get("Donor Countries");
    public static ArrayList<String>  receivingCountries = (ArrayList<String>) countryMap().get("Receiving Countries");
    
    public static Map<String, List<String>> countryMap(){
        Map<String, List<String>> allCountries = new HashMap<String, List<String>>();
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
    
    public static String[] empType = new String[] {"ADMIN","EMPLOYEE"};
    
    
    
//    public static Map<String, List<String>> allCountries = new HashMap<>();  
//    ArrayList<String> donorCountries = new ArrayList<>();
//    donorCountries.add
}
