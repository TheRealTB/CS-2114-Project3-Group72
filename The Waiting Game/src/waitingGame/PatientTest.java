package waitingGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("javadoc")
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
}
