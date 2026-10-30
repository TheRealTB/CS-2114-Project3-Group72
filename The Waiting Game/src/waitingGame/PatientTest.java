package waitingGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PatientTest {
    private Patient tp;
    
    @BeforeEach
    public void setUp() throws Exception {
        tp = new Patient();
        
    }
    @Test
    public void testArrayInitialization() {
        for(String[] arr:tp.getData()) {
            for(String s:arr) {
                assertNotNull(s);
            }
        }
    }
    @Test
    public void testGeneratePatient() {
        //This method realistically can also test all other getters
    }
    
    @Test
    public void testToString() {
        String ts = tp.toString();
        assertTrue(ts.contains(tp.getFirstName())); assertTrue(ts.contains(tp.getLastName()));
        assertTrue(ts.contains(tp.getAge()+"")); assertTrue(ts.contains(tp.getOccupation()));
        assertTrue(ts.contains(tp.getSymptoms()));
    }
    
    //Generates patients until each case of classifyPatient is reached, then asserts that each have been reached
    @Test public void testClassifyPatient() {
        boolean PA = false, A = false, D = false;
        while(!PA || !A || !D) {
            tp.generatePatient();
            switch(tp.classifyPatient()) {
                case "Priority Admit": PA = true; break;
                case "Admit": A = true; break;
                case "Deny": D = true; break;
            }
        }
        assertTrue(PA); assertTrue(A); assertTrue(D);
    }
}
