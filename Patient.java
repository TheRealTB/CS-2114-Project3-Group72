// -------------------------------------------------------------------------
/**
 *  An emergency room patient who is waiting in line
 * 
 *  @author Tyler
 *  @version Oct 26, 2026
 */
package waitingGame;

import java.util.*;
import java.io.*;

//TODO: Make more interesting/unique illnesses & symptoms + multi-symptom
//TODON'T Autoformat code pls
@SuppressWarnings("javadoc")
public class Patient {
    private String firstName, lastName, occupation, illness, symptoms;
    private int age, criticality;
    private String[][] data;
    
    
    
    public Patient() {
        try (BufferedReader reader = new BufferedReader
            (new FileReader("Patient Generator"))) {
            //Basic CSV traversal with values saved to 2D array
            data = new String[100][7];
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                int col = 0;
                for (String value : values) {
                    data[row][col] = value.trim();
                    col++;
                }
                //System.out.println(Arrays.toString(data[r]));
                row++;
            }
            reader.close();
        //Exception catching and default array assignment
        }catch(FileNotFoundException e) {
            System.out.println("Can not find patient data file"
                + "\nPatient variables are defaulted\n\n");
            this.firstName = "Tyler"; this.lastName = "Berry";
            this.age = 18; this.occupation = "Student";
            this.illness = "No Illness"; this.symptoms = "Aysymptomatic";
            this.criticality = 1;
        }catch(IOException e) {
            System.out.println("Unhadnled IO Exception"
                + "\nPatient variables are defaulted\n\n");
            this.firstName = "Tyler"; this.lastName = "Berry";
            this.age = 18; this.occupation = "Student";
            this.illness = "No Illness"; this.symptoms = "Aysymptomatic";
            this.criticality = 1;
        }
        this.generatePatient();
    }
    
    public void generatePatient() {
        //generates random 12 digit string and initializes variables with seed
        String randSeed = Math.random() + "";
        randSeed = randSeed.substring(2, 14);
        //System.out.printf("\n\nRandom Seed %s", randSeed);
        for(int col = 0; col < data[0].length-2; col++) {
            int row = Integer.parseInt(randSeed.substring(col, col+2));
            /*System.out.printf("\n\nRow: %d, col: %d, Data: %s", 
            row, col, data[row][col]);
            */
            switch(col){
                case 0: this.firstName = data[row][col];
                    break;
                case 1: this.lastName = data[row][col];
                    break;
                case 2: this.age = Integer.parseInt(data[row][col]);
                    break;
                case 3: this.occupation = data[row][col];
                    break;
                case 4: this.illness = data[row][col];
                    this.symptoms = data[row][data[row].length-2];
                    this.criticality = Integer.parseInt
                        (data[row][data[row].length-1]);
                    break;
                default:
                    System.out.printf("Statement has hit default case\n\n");
            }
        }
        System.out.printf("Name: %s %s, Age: %d,\nOccupation: %s,"
            + " Illness: %s,\nSymptoms: %s, Criticality: %d\n\n",
            firstName, lastName, age, occupation, 
            illness, symptoms, criticality);
    }
    
    public String[][] getData(){
        return data;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getName() {
        return firstName + " " + lastName;
    }
    public int getAge() {
        return age;
    }
    public String getOccupation() {
        return occupation;
    }
    public String getIllness() {
        return illness;
    }
    public String getSymptoms() {
        return symptoms;
    }
    public int getCriticality() {
        return criticality;
    }
}
