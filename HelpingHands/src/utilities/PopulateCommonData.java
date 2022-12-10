/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Khalesi
 */
public class PopulateCommonData {
    
    public DefaultComboBoxModel<String> setEmpTypeDropDown() {
        Vector<String> empTypeVector = new Vector<String>(Arrays.asList(Constants.empType));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(empTypeVector);
        return model;
    }
}
