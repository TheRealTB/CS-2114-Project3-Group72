package waitingGame;

//-------------------------------------------------------------------------
/**
* Controller of the game that communicates between view and model
* 
* @author carol
* @version Nov 8, 2026
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


 public void startGame()
 {
     queue = new PatientQueue();
     scoreManager.resetScore();
     generatePatient();
 }


 public Patient generatePatient()
 {
     currentPatient = new Patient();
     return currentPatient;

 }


 public void addPatient(Patient patient, int priorityLevel)
 {
//     if (!validator.validateChoice(patient, priorityLevel))
//     {
//         return false;
//     }

     queue.enqueue(patient);
 }


 public void updateScore(Patient patient, String choice)
 {
     System.out.println("entered updateScore");
     if (patient == null)
     {
         System.out.println("Patient is null!!");
         return;
     }

     int points = patient.validateScore(choice);

     if (points >= 0)
     {
         scoreManager.addPoints(points);
     }
     else
     {
         scoreManager.subtractPoints(Math.abs(points));
     }
 }


 public boolean validateChoice(Patient patient, int priorityLevel)
 {
     return validator.validateChoice(patient, priorityLevel);
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
     System.out.println("Final Score: " + scoreManager.getScore());
 }
 
 public Patient treatNextPatient()
 {
     return queue.patientDequeue();
 }
 public Patient getCurrentPatient()
 {
     return currentPatient;
 }

}