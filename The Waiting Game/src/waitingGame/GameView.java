package project03cs2114;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GameView
{
    //~ Fields ................................................................
    private GameView game;
    private JFrame window;
    private JList<Patient> patientList;
    private DefaultListModel<Patient> patientListModel;
    private JTextArea patientDetails;
    private JLabel scoreLabel;
    private JLabel messageLabel;
    private JButton treatNextButton;
    private JButton newGameButton;
    

    //~ Constructors ..........................................................
    public GameView(MainMenu game) {
        buildLayout();
        
    }
   
    
    
    //~Public  Methods ........................................................
    public void buildLayout() {
        window = new JFrame("Patient triage game");
        patientListModel = new DefaultListModel<>();
        patientList = new JList<>(patientListModel);
        patientDetails = new JTextArea("Select A Patient To See Their Details");
        scoreLabel = new JLabel("Score = 0");
        treatNextButton = new JButton("Click To Move On To the Next Patient");
        newGameButton = new JButton("Click to start a new game");
        
        JPanel mainPanel = new JPanel(new GridLayout(6,1));
        mainPanel.add(new JScrollPane(patientList));
        mainPanel.add(new JScrollPane(patientDetails));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(scoreLabel);
        bottomPanel.add(messageLabel);
        bottomPanel.add(treatNextButton);
        bottomPanel.add(newGameButton);
        
        window.add(mainPanel, BorderLayout.CENTER);
        window.add(bottomPanel, BorderLayout.SOUTH);
        window.pack();
        
    }
   
   public void showWindow() {
       window.setVisible(true);
   }
   
   public void displayPatientDetails(Patient patient) {//LEFT OFF HERE!!! 
       String patientName = patient.getFirstName();
       int patientAge = patient.getAge();
       String patientOccupation = patient.getOccupation();
   }
   
}
