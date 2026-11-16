package waitingGame;

import java.util.*;
import java.io.*;

public class Save {
    private static final int items = 4;
    public static Object[] load() {
        Object[] data = new Object[items];
        try(Scanner s = new Scanner(new File("SaveFile"))) {
            int i = 0;
            int ql = 0;
            while(s.hasNext()) {
                String in = s.nextLine();
                switch(i) {
                    case 0:
                        //length of queue (not saved into array)
                        ql = Integer.parseInt(in);
                        i++;
                        break;
                    case 1:
                        //queue
                        PatientQueue q = new PatientQueue();
                        for(int j = 0; j < ql; j++) {
                            String[] pArray = new String[7];
                            for(int l = 0; l < 7; l++) {
                                System.out.println(pArray[l] = in.substring(in.indexOf(" ") + 1));
                                if(l < 6) {in = s.nextLine();}
                            }
                            Patient p = new Patient();
                            p.setPatient(pArray);
                            q.enqueue(p);
                            if(j < ql-1) {in = s.nextLine();}
                        }
                        data[0] = q;
                        i++;
                        break;
                    case 2:
                        //incoming patient
                        String[] pArray = new String[7];
                        for(int l = 0; l < 7; l++) {
                            System.out.println(pArray[l] = in.substring(in.indexOf(" ") + 1));
                            if(l < 6) {in = s.nextLine();}
                        }
                        Patient p = new Patient();
                        p.setPatient(pArray);
                        data[1] = p;
                        i++;
                        break;
                    case 3:
                        //score
                        data[2] = Integer.parseInt(in);
                        i++;
                        break;
                    case 4:
                        //time
                        data[3] = Integer.parseInt(in);
                        i++;
                        break;
                }
            }
        }catch(FileNotFoundException e) {
            //
        }
        return data;
    }
    
    public static void save(PatientQueue q, Patient p, int s, int t) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("SaveFile"))){
           bw.write(q.getSize() + "\n");
           List<Patient> pl = q.getPatientsInServiceOrder();
           for(Patient pat:pl) {
               bw.write(pat.toString() + "\n");
           }
           bw.write(p.toString() + "\n");
           bw.write(s + "\n");
           bw.write(t + "\n");
        }catch(IOException e) {
            
        }
    }
}
