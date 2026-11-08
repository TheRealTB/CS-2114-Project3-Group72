package waitingGame;

/**
 * Validates a player's chosen priority level for a patient. Does not modify the
 * patient, queue, or score.
 *
 * @author Essan
 * @version Nov 7, 2026
 */
public class ChoiceValidator
{
    public ChoiceValidator()
    {
    }


    // Checks whether a selected priority is in the allowed range
    public boolean isValidPriority(int priorityLevel)
    {
        return priorityLevel >= 1 && priorityLevel <= 100;
    }


    // Checks whether a patient exists and has a valid criticality
    public boolean isValidPatient(Patient patient)
    {
        return patient != null && isValidPriority(patient.getCriticality());
    }

    // Checks whether the selected priority matches the patient's actual criticality
    public boolean validateChoice(Patient patient, int priorityLevel)
    {
        return isValidPatient(patient) && isValidPriority(priorityLevel)
            && patient.getCriticality() == priorityLevel;
    }
}
