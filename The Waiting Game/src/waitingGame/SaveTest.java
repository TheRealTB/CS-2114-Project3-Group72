package waitingGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaveTest {
    private Patient tp;
    private PatientQueue pq;
    private Patient pq1;
    private Patient pq2;
    private Patient pq3;
    private Patient pq4;
    private Patient pq5;
    
    @BeforeEach
    public void setUp() throws Exception {
        tp = new Patient();
        pq1 = new Patient();
        pq2 = new Patient();
        pq3 = new Patient();
        pq4 = new Patient();
        pq5 = new Patient();
        pq = new PatientQueue();
        pq.enqueue(pq1);
        pq.enqueue(pq2);
        pq.enqueue(pq3);
        pq.enqueue(pq4);
        pq.enqueue(pq5);
        
    }
    @Test
    public void testSave() {
        Save.save(pq, tp, 10, 1000);
    }
    //Tests overridden toString, generatePatient, and all getters except getCriticality
    @Test
    public void testLoad() {
        Object[] oA = Save.load();
        for(Object o:oA) {
            try{
                System.out.println(o.toString());
            }catch(NumberFormatException e) {
                System.out.println(o);
            }
        }
    } 
}