package waitingGame;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests normal, boundary, and invalid inputs for ChoiceValidator.
 *
 * @author Essan
 * @version Nov 7, 2026
 */
public class ChoiceValidatorTest
{

    private ChoiceValidator validator;

    @BeforeEach
    public void setUp()
    {
        validator = new ChoiceValidator();
    }


    // Creates a patient with a known criticality for testing.
    // Patient currently has no constructor or setter for criticality.
    // This helper can be replaced once Patient provides a test-friendly
    // constructor.
    private Patient patientWithCriticality(int criticality)
        throws Exception
    {
        Patient patient = new Patient();
        Field field = Patient.class.getDeclaredField("criticality");
        field.setAccessible(true);
        field.setInt(patient, criticality);
        return patient;
    }


    @Test
    public void testConstructor()
    {
        assertNotNull(validator);
    }


    @Test
    public void testIsValidPriorityNormalValues()
    {
        assertTrue(validator.isValidPriority(25));
        assertTrue(validator.isValidPriority(50));
        assertTrue(validator.isValidPriority(75));
    }


    @Test
    public void testIsValidPriorityBoundaries()
    {
        assertTrue(validator.isValidPriority(1));
        assertTrue(validator.isValidPriority(100));
    }


    @Test
    public void testIsValidPriorityOutsideRange()
    {
        assertFalse(validator.isValidPriority(0));
        assertFalse(validator.isValidPriority(-1));
        assertFalse(validator.isValidPriority(101));
        assertFalse(validator.isValidPriority(-100));
        assertFalse(validator.isValidPriority(1000));
    }


    @Test
    public void testIsValidPriorityExtremeIntegers()
    {
        assertFalse(validator.isValidPriority(Integer.MIN_VALUE));
        assertFalse(validator.isValidPriority(Integer.MAX_VALUE));
    }


    @Test
    public void testIsValidPatientNull()
    {
        assertFalse(validator.isValidPatient(null));
    }


    @Test
    public void testIsValidPatientNormalValues()
        throws Exception
    {
        assertTrue(validator.isValidPatient(patientWithCriticality(20)));
        assertTrue(validator.isValidPatient(patientWithCriticality(50)));
        assertTrue(validator.isValidPatient(patientWithCriticality(80)));
    }


    @Test
    public void testIsValidPatientBoundaryValues()
        throws Exception
    {
        assertTrue(validator.isValidPatient(patientWithCriticality(1)));
        assertTrue(validator.isValidPatient(patientWithCriticality(100)));
    }


    @Test
    public void testIsValidPatientInvalidValues()
        throws Exception
    {
        assertFalse(validator.isValidPatient(patientWithCriticality(0)));
        assertFalse(validator.isValidPatient(patientWithCriticality(-1)));
        assertFalse(validator.isValidPatient(patientWithCriticality(101)));
    }


    @Test
    public void testIsValidPatientExtremeValues()
        throws Exception
    {
        assertFalse(
            validator
                .isValidPatient(patientWithCriticality(Integer.MIN_VALUE)));
        assertFalse(
            validator
                .isValidPatient(patientWithCriticality(Integer.MAX_VALUE)));
    }


    @Test
    public void testValidateChoiceCorrectNormalValues()
        throws Exception
    {
        assertTrue(validator.validateChoice(patientWithCriticality(25), 25));
        assertTrue(validator.validateChoice(patientWithCriticality(50), 50));
        assertTrue(validator.validateChoice(patientWithCriticality(75), 75));
    }


    @Test
    public void testValidateChoiceCorrectBoundaries()
        throws Exception
    {
        assertTrue(validator.validateChoice(patientWithCriticality(1), 1));
        assertTrue(validator.validateChoice(patientWithCriticality(100), 100));
    }


    @Test
    public void testValidateChoiceIncorrectValidPriorities()
        throws Exception
    {
        Patient patient = patientWithCriticality(50);
        assertFalse(validator.validateChoice(patient, 49));
        assertFalse(validator.validateChoice(patient, 51));
        assertFalse(validator.validateChoice(patient, 1));
        assertFalse(validator.validateChoice(patient, 100));
    }


    @Test
    public void testValidateChoiceInvalidPriorities()
        throws Exception
    {
        Patient patient = patientWithCriticality(50);
        assertFalse(validator.validateChoice(patient, 0));
        assertFalse(validator.validateChoice(patient, -1));
        assertFalse(validator.validateChoice(patient, 101));
        assertFalse(validator.validateChoice(patient, Integer.MIN_VALUE));
        assertFalse(validator.validateChoice(patient, Integer.MAX_VALUE));
    }


    @Test
    public void testValidateChoiceInvalidPatientCriticality()
        throws Exception
    {
        assertFalse(validator.validateChoice(patientWithCriticality(0), 1));
        assertFalse(validator.validateChoice(patientWithCriticality(101), 100));
        assertFalse(validator.validateChoice(patientWithCriticality(-1), -1));
        assertFalse(validator.validateChoice(patientWithCriticality(101), 101));
    }


    @Test
    public void testValidateChoiceNullPatient()
    {
        assertFalse(validator.validateChoice(null, 50));
        assertFalse(validator.validateChoice(null, 0));
        assertFalse(validator.validateChoice(null, Integer.MAX_VALUE));
    }


    @Test
    public void testValidationDoesNotChangePatientCriticality()
        throws Exception
    {
        Patient patient = patientWithCriticality(65);
        assertFalse(validator.validateChoice(patient, 64));
        assertEquals(65, patient.getCriticality());
        assertTrue(validator.validateChoice(patient, 65));
        assertEquals(65, patient.getCriticality());
    }
}
