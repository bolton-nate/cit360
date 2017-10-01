/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import view.*;
import model.*;

/**
 *
 * @author natebolton
 */
public class MVCController {

    public void startApplication() {
        // View the application's GUI
        Rot13View view = new Rot13View();
        view.setVisible(true);
    }
    
    public String getMessage() {
        try {
            FileModel file = new FileModel();
            return file.getData();
        } catch (Exception er) {
            return "There was an error.";
        }
    }
    
    public boolean writeMessage(String message) {
        try {
            FileModel file = new FileModel();
            return file.writeData(message);
        } catch (Exception er) {
            return false;
        }
    }
}
