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
    //Tests overridden toString, generatePatient, and all getters except getCriticality
    @Test
    public void testToString() {
        String ts = tp.toString();
        assertTrue(ts.contains(tp.getFirstName())); assertTrue(ts.contains(tp.getLastName()));
        assertTrue(ts.contains(tp.getAge()+"")); assertTrue(ts.contains(tp.getOccupation()));
        assertTrue(ts.contains(tp.getSymptoms()));
    }
    
    //Ensures illness gets initialized
    @Test
    public void testGetIllness() {
        assertNotNull(tp.getIllness());
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
    
  //Generates patients until each case of validateScore is reached, then asserts that each have been reached
    @Test
    public void testValidateScore() {
        String[] possible = {"Priority Admit", "Admit", "Deny"};
        for(String p:possible) {
            int flags = 0;
            while(flags < 3) {
                tp.generatePatient();
                System.out.println(tp.validateScore(p));
                switch(tp.validateScore(p)) {
                    case 300: flags ++; break;
                    case 100: flags++; break;
                    case -100: flags ++; break;
                    case -200: flags ++; break;
                }
            }
            assertTrue(flags == 3);
        }
    }   
}