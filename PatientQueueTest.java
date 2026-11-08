package project03cs2114;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        return new Patient()
        {
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
        assertNull(queue.patientDeqeue());
        assertEquals(0, queue.getSize());
    }

    @Test
    public void testCriticalityCutoffs()
    {
        Patient denied = patientWithCriticality(33);
        Patient regular = patientWithCriticality(34);
        Patient priority = patientWithCriticality(67);

        assertFalse(queue.patientEnqeue(denied));
        assertTrue(queue.isEmpty());

        assertTrue(queue.patientEnqeue(regular));
        assertTrue(queue.patientEnqeue(priority));
        assertEquals(2, queue.getSize());

        assertSame(priority, queue.patientDeqeue());
        assertSame(regular, queue.patientDeqeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPriorityAndArrivalOrder()
    {
        Patient firstRegular = patientWithCriticality(40);
        Patient firstPriority = patientWithCriticality(70);
        Patient secondRegular = patientWithCriticality(66);
        Patient secondPriority = patientWithCriticality(90);

        queue.patientEnqeue(firstRegular);
        queue.patientEnqeue(firstPriority);
        queue.patientEnqeue(secondRegular);
        queue.patientEnqeue(secondPriority);

        assertEquals(4, queue.getSize());
        assertSame(firstPriority, queue.patientPeek());
        assertEquals(4, queue.getSize());

        assertSame(firstPriority, queue.patientDeqeue());
        assertSame(secondPriority, queue.patientDeqeue());
        assertEquals(2, queue.getSize());

        // Only regular patients remain.
        assertSame(firstRegular, queue.patientPeek());
        assertEquals(2, queue.getSize());

        assertSame(firstRegular, queue.patientDeqeue());
        assertSame(secondRegular, queue.patientDeqeue());
        assertTrue(queue.isEmpty());
        assertNull(queue.patientPeek());
    }

    @Test
    public void testReuseAfterDrainingQueue()
    {
        Patient priority = patientWithCriticality(80);
        Patient regular = patientWithCriticality(50);

        queue.patientEnqeue(priority);
        assertFalse(queue.isEmpty());
        assertSame(priority, queue.patientPeek());
        assertSame(priority, queue.patientDeqeue());
        assertTrue(queue.isEmpty());

        queue.patientEnqeue(priority);
        queue.patientEnqeue(regular);
        assertSame(priority, queue.patientDeqeue());
        assertSame(regular, queue.patientDeqeue());
        assertTrue(queue.isEmpty());

        queue.patientEnqeue(regular);
        assertSame(regular, queue.patientPeek());
        assertSame(regular, queue.patientDeqeue());
        assertEquals(0, queue.getSize());
    }
}