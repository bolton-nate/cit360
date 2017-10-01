/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template FileModel, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author natebolton
 */
public class FileModel {
    public String getData() throws FileNotFoundException, IOException {
        
        if(!(new File("log.txt").isFile())) {
            // Create -- Make sure FileModel exists -- the FileModel before continuing
            Files.createFile(Paths.get("log.txt"));
        }
        
        String data;
        // We will be using a try-with-resource block
        try (BufferedReader reader = new BufferedReader(
                new FileReader("log.txt"))) {
            // Access the data from the FileModel
            // Create a new StringBuilder
            StringBuilder string = new StringBuilder();
            
            // Read line-by-line
            String line = reader.readLine();
            //string.append("<html>");
            // While there comes a new line, execute this
            while(line != null) {
                // Add these lines to the String builder
                string.append(line);
                //string.append("<br />");
                // Read the next line
                line = reader.readLine();
            }
            //string.append("</html>");
            data = string.toString();
        } catch (Exception er) {
            // Since there was an error, you probably want to notify the user
            // For that error. So return the error.
            data = er.getMessage();
        }
        // Return the string read from the FileModel
        return data;
    }
    
    public boolean writeData(String data) throws IOException, FileNotFoundException
    {
        // Save the data to the FileModel
        try (BufferedWriter writer = new BufferedWriter(
                                        new FileWriter("log.txt"))) {
            // Write the data to the FileModel
            writer.write(data);
            // Return indicating the data was written
            return true;
        } catch (Exception er) {
            return false;
        }
    }
}
