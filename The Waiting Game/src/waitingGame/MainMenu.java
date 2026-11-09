package project03cs2114;

// -------------------------------------------------------------------------
/**
 *  Controller of the game that communicates between view and model
 * 
 *  @author carol
 *  @version Nov 8, 2026
 */
public class MainMenu
{
     private ScoreManager scoreManager;
     private PatientQueue queue; 
     private ChoiceValidator validator;
     private Patient currentPatient;
     
     // ----------------------------------------------------------
    /**
     * Create a new MainMenu object which sets all the initial parameters.
     */
    public MainMenu() 
     {
        scoreManager = new ScoreManager();
        queue = new PatientQueue();
        validator = new ChoiceValidator();
        
        generatePatient();
         
     }
    
    private void startGame() {
        queue = new PatientQueue();
        scoreManager.resetScore();
        generatePatient();
    }
  

    public Patient generatePatient()
    {
       currentPatient = new Patient();
       return currentPatient;
        
    }
    
    
    public boolean addPatient(Patient patient, int priorityLevel) {
        if (!validator.validateChoice(patient, priorityLevel)) {
            return false;
        }
        
        return queue.patientEnqeue(patient);
    }
    
   public void updateScore(Patient patient, String choice) 
   {
       if (patient == null)
       {
           return;
       }

       int points = patient.validateScore(choice);

       if (points >= 0)
       {
           scoreManager.addPoints(points);
       }
       else
       {
           scoreManager.subtractPoints(
               Math.abs(points));
       }
   }
   
   public boolean validateChoice(Patient patient, int priorityLevel)
   {
       return validator.validateChoice( patient, priorityLevel);
   }
    
   public int getScore()
   {
       return scoreManager.getScore();
   }
    
   public PatientQueue getPatients()
   {
       return queue;
   }
   
   public void endGame()
   {
       System.out.println(
           "Final Score: "
           + scoreManager.getScore());
   }
    
}
