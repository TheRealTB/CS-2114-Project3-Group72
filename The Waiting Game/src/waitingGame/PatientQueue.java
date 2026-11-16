package waitingGame;
import java.util.ArrayList;
import java.util.List;

public class PatientQueue
{
    // ~ Fields ................................................................
    private Node<Patient> firstNode;// Anything below the 33 mark wouldn't be
                                    // put in the queue in the first place, so
                                    // anything after the firstNode to the 67
                                    // bucket is someone with a range of 34 to
                                    // 66, anything above the 67 dummy pointer
                                    // is someone who is critical
    private Node<Patient> lastNode;
    private Node<Patient> dummyNode67Bucket;
    private int size;

    // ~ Constructors ..........................................................
    public PatientQueue()
    {
        firstNode = null;
        lastNode = null;
        dummyNode67Bucket = new Node<Patient>(null);
        size = 0;
    }


    public Patient patientDequeue()// removes from end
    {
        if (size == 0)
        {
            return null;
        }
        size--;

        if (lastNode == null)
        {
            Patient toReturn = dummyNode67Bucket.previous.data;
            if (dummyNode67Bucket.previous == firstNode)
            {
                firstNode = null;
                dummyNode67Bucket.previous = null;
            }
            else
            {
                Node<Patient> newPrev = dummyNode67Bucket.previous.previous;
                newPrev.next = dummyNode67Bucket;
                dummyNode67Bucket.previous = newPrev;

            }
            return toReturn;
        }

        Patient toReturn2 = lastNode.data;
        if (lastNode.previous == dummyNode67Bucket)
        {
            lastNode = null;
            dummyNode67Bucket.next = null;
        }
        else
        {
            Node<Patient> newLast = lastNode.previous;
            newLast.next = null;
            lastNode.previous = null;
            lastNode = newLast;

        }
        return toReturn2;
    }

    public boolean enqueue(Patient toAdd) {
        Node<Patient> newPatient = new Node<Patient>(toAdd);
            if (firstNode == null){
                firstNode = newPatient;
                newPatient.next = dummyNode67Bucket;
                newPatient.previous = null;
                dummyNode67Bucket.previous = firstNode;

            }else            {
                Node<Patient> previousFirst = firstNode;
                newPatient.next = firstNode;
                previousFirst.previous = newPatient;
                firstNode = newPatient;
            }
        size++;
        return true;
    }
    
    public boolean patientEnqueue(Patient toAdd)
    {
        int patientCategorization = toAdd.getCriticality();

        if (patientCategorization > 33)
        {
            Node<Patient> newPatient = new Node<Patient>(toAdd);
            if (patientCategorization < 67)
            {
                if (firstNode == null)
                {
                    firstNode = newPatient;
                    newPatient.next = dummyNode67Bucket;
                    newPatient.previous = null;
                    dummyNode67Bucket.previous = firstNode;

                }
                else
                {
                    Node<Patient> previousFirst = firstNode;
                    newPatient.next = firstNode;
                    previousFirst.previous = newPatient;
                    firstNode = newPatient;
                }

            }
            else
            {
                if (lastNode == null)
                {
                    lastNode = newPatient;
                    newPatient.previous = dummyNode67Bucket;
                    dummyNode67Bucket.next = lastNode;
                }
                else
                {
                    Node<Patient> storeNext = dummyNode67Bucket.next;
                    dummyNode67Bucket.next = newPatient;
                    newPatient.previous = dummyNode67Bucket;
                    newPatient.next = storeNext;
                    storeNext.previous = newPatient;
                }
            }

            size++;
            return true;
        }
        return false;

    }


    public Patient patientPeek()
    {
        if (lastNode != null)
        {
            return lastNode.data; 
        }

        if (firstNode == null)
        {
            return null; 
        }

        return dummyNode67Bucket.previous.data; 
    }


    public boolean isEmpty()
    {
        return size == 0;
    }


    public int getSize()
    {
        return size;
    }
    
    public List<Patient> getPatientsInServiceOrder(){
        List<Patient> patientList = new ArrayList<>();
        
        Node<Patient> current = lastNode;
        while (current != null && current != dummyNode67Bucket)
        {
            patientList.add(current.data);
            current = current.previous;
        }
        current = dummyNode67Bucket.previous;
        while (current != null)
        {
            patientList.add(current.data);
            current = current.previous;
        }

        return patientList;
    }
    // ~Public Methods ........................................................

}