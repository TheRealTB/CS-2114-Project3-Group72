package waitingGame;

/**
 * Simple driver used to demonstrate that the model works
 * without the GUI.
 *
 * @author Caroline
 * @version Nov 2026
 */
public class Driver
{
    public static void main(String[] args)
    {
        MainMenu game = new MainMenu();

        System.out.println("=== WAITING GAME MODEL TEST ===");

        
        Patient patient = game.generatePatient();
        
        
        System.out.println("\nGenerated Patient:");
        System.out.println(patient);
        
        System.out.println(
            "Criticality: " + patient.getCriticality());
        System.out.println(
            "Classification: "
            + patient.classifyPatient()); 


        // Simulate player selecting the patient's actual criticality
        int selectedPriority = patient.getCriticality();

        boolean added =
            game.addPatient(patient, selectedPriority);

        System.out.println(
            "\nPatient added to queue: " + added);

        System.out.println(
            "Queue size: "
            + game.getPatients().getSize());

        // Show next patient waiting
        Patient nextPatient =
            game.getPatients().patientPeek();

        System.out.println("\nNext Patient:");
        System.out.println(nextPatient);

        // Update score
        game.updateScore(patient, "Priority Admit");

        System.out.println(
            "\nCurrent Score: "
            + game.getScore());

        // Remove patient
        Patient treated =
            game.getPatients().patientDeqeue();

        System.out.println(
            "\nRemoved Patient:");
        System.out.println(treated);

        System.out.println(
            "\nQueue Empty: "
            + game.getPatients().isEmpty());

        game.endGame();
    }
}
