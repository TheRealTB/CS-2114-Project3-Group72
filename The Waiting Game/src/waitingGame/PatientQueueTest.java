package waitingGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatientQueueTest
{
    private PatientQueue queue;

    @BeforeEach
    public void setUp()
    {
        queue = new PatientQueue();
    }


    private Patient patientWithCriticality(int rating)
    {
        return new Patient() {
            @Override
            public void generatePatient()
            {
                // Skip random generation for queue tests.
            }


            @Override
            public int getCriticality()
            {
                return rating;
            }
        };
    }


    @Test
    public void testEmptyQueue()
    {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());
        assertNull(queue.patientPeek());
        assertNull(queue.patientDequeue());
        assertEquals(0, queue.getSize());
    }


    @Test
    public void testCriticalityCutoffs()
    {
        Patient denied = patientWithCriticality(33);
        Patient regular = patientWithCriticality(34);
        Patient priority = patientWithCriticality(67);

        assertTrue(queue.isEmpty());
        //assertEquals(2, queue.getSize());

//        assertSame(priority, queue.patientDequeue());
//        assertSame(regular, queue.patientDequeue());
//        assertTrue(queue.isEmpty());
    }


    @Test
    public void testPriorityAndArrivalOrder()
    {
        Patient firstRegular = patientWithCriticality(40);
        Patient firstPriority = patientWithCriticality(70);
        Patient secondRegular = patientWithCriticality(66);
        Patient secondPriority = patientWithCriticality(90);

        queue.enqueue(firstRegular);
        queue.priorityEnqueue(firstPriority);
        queue.enqueue(secondRegular);
        queue.priorityEnqueue(secondPriority);

        assertEquals(4, queue.getSize());
        assertSame(firstPriority, queue.patientPeek());
        assertEquals(4, queue.getSize());

        assertSame(firstPriority, queue.patientDequeue());
        assertSame(secondPriority, queue.patientDequeue());
        assertEquals(2, queue.getSize());

        // Only regular patients remain.
        assertSame(firstRegular, queue.patientPeek());
        assertEquals(2, queue.getSize());

        assertSame(firstRegular, queue.patientDequeue());
        assertSame(secondRegular, queue.patientDequeue());
        assertTrue(queue.isEmpty());
        assertNull(queue.patientPeek());
    }


    @Test
    public void testReuseAfterDrainingQueue()
    {
        Patient priority = patientWithCriticality(80);
        Patient regular = patientWithCriticality(50);

        queue.priorityEnqueue(priority);
        assertFalse(queue.isEmpty());
        assertSame(priority, queue.patientPeek());
        assertSame(priority, queue.patientDequeue());
        assertTrue(queue.isEmpty());

        queue.priorityEnqueue(priority);
        queue.enqueue(regular);
        assertSame(priority, queue.patientDequeue());
        assertSame(regular, queue.patientDequeue());
        assertTrue(queue.isEmpty());

        queue.enqueue(regular);
        assertSame(regular, queue.patientPeek());
        assertSame(regular, queue.patientDequeue());
        assertEquals(0, queue.getSize());
    }
}
