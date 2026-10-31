// -------------------------------------------------------------------------
/**
 *  An emergency room patient who is waiting in line
 * 
 *  @author Tyler
 *  @version Oct 26, 2026
 */
package waitingGame;

//import java.util.*;
import java.io.*;

//TODO: Make more interesting/unique illnesses & symptoms + multi-symptom

public class Patient {
    private String firstName, lastName, occupation, illness, symptoms;
    private int age, criticality;
    private String[][] data;
    
    
    //Creates a new patient from Patient Generator using generatePatient
    public Patient() {
        try (BufferedReader reader = new BufferedReader
            (new FileReader("Patient Generator"))) {
            //Basic CSV traversal with values saved to 2D array
            //Set the # rows to the number of rows in Patient Generator
            data = new String[1000][7];
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                int col = 0;
                for (String value : values) {
                    data[row][col] = value;
                    col++;
                }
                //System.out.println(Arrays.toString(data[row]));
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
    
    //Patient classification by criticality
    //These criticality values are subject to change
    public String classifyPatient() {
        return (criticality>66) ? "Priority Admit" : (criticality>33) ? "Admit" : "Deny";
    }
    
    //Generates a score based on where the patient is placed in the queue and their criticality
    public int validateScore(String choice) {
        //correct choice rewards 300 points
        if(choice.equals(classifyPatient())) {
            return 300;
        //off-by-one case rewards 100 points for going one above 
        }else if((choice.equals("Admit") && classifyPatient().equals("Deny"))
            || (choice.equals("PriorityAdmit") && classifyPatient().equals("Admit"))){
            return 100;
        //off-by-one case docks 100 points for going one below
        }else if((choice.equals("Admit") && classifyPatient().equals("Priority Admit")) 
            || (choice.equals("Deny") && classifyPatient().equals("Admit"))) {
            return -100;
        //incorrect choice docks 200 points
        }else {
            return -200;
        }
    }
    
    //Patient info (chart style)
    @Override
    public String toString() {
        String s = "First Name: " + this.firstName + "\nLast Name: " + this.lastName
            + "\nAge: " + this.age + "Occupation: " + this.occupation + "\nSymptoms: " + this.symptoms;
        return s;
    }
    
    //Create and edit patients below this line
    
    /*generates random number as a string,
     *takes the substring of the string and 
     *initializes variables with seed
     *unique name first, last
     * age and occupation go together and illness, symptoms, and 
     * criticality go together
     */
    public void generatePatient() {
        
        String randSeed = Math.random() + "";
        System.out.println("\n\nRANDOM SEED: " + randSeed);
            randSeed = randSeed.substring(2, 10);
          //System.out.printf("\n\nRandom Seed %s", randSeed);
            for(int col = 0; col < data[0].length-2; col++) {
                int row = Integer.parseInt(randSeed.substring(col, col+2));
                System.out.printf("\n\nRow: %d, col: %d, Data: %s\n", 
                row, col, data[row][col]);
                switch(col){
                    case 0: this.firstName = data[row][col];
                        break;
                    case 1: this.lastName = data[row][col];
                        break;
                    case 2: this.age = Integer.parseInt(data[row][col]);
                        this.occupation = data[row][col];
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
        System.out.printf("Name: %s %s, Age: %d, Occupation: %s,"
            + " \nIllness: %s,Symptoms: %s, Criticality: %d\n\n",
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
}
