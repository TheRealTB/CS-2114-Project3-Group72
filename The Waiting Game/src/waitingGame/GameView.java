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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView
{
    // ~ Fields ................................................................
    private MainMenu game;
    private JFrame window;
    private JList<Patient> patientList;
    private DefaultListModel<Patient> patientListModel;
    private JTextArea patientDetails;
    private JLabel scoreLabel;
    private JLabel messageLabel;
    private JButton treatNextButton;
    private JButton newGameButton;
    private JButton denyPatientButton;
    private JButton admitPatientNButton;
    private JButton admitPatientPButton;

    // ~ Constructors ..........................................................
    public GameView(MainMenu game)
    {
        this.game = game;
        buildLayout();
        registerListeners();
        refreshView();
        displayPatientDetails(game.getCurrentPatient());
    }


    // ~Public Methods ........................................................
    public void buildLayout()
    {
        window = new JFrame("Patient triage game");
        patientListModel = new DefaultListModel<>();
        patientList = new JList<>(patientListModel);
        patientDetails = new JTextArea("Select A Patient To See Their Details");
        messageLabel = new JLabel("");
        messageLabel.setPreferredSize(new Dimension(180, 24));
        scoreLabel = new JLabel("Score = 0");
        treatNextButton = new JButton("Click To Move On To the Next Patient");
        newGameButton = new JButton("Click to start a new game");
        denyPatientButton = new JButton("Click to Deny Patient Admission");
        admitPatientNButton = new JButton("Click to admit non priority patient");
        admitPatientPButton = new JButton("Click to admit Priority Patient");

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(new JScrollPane(patientList));
        mainPanel.add(new JScrollPane(patientDetails));

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(scoreLabel);
        bottomPanel.add(messageLabel);
        bottomPanel.add(treatNextButton);
        bottomPanel.add(newGameButton);
        bottomPanel.add(denyPatientButton);
        bottomPanel.add(admitPatientNButton);
        bottomPanel.add(admitPatientPButton);

        window.add(mainPanel, BorderLayout.CENTER);
        window.add(bottomPanel, BorderLayout.SOUTH);
        window.pack();
    }


    public void showWindow()
    {
        window.setVisible(true);
    }


    public void displayPatientDetails(Patient patient)
    {
        if (patient == null)
        {
            patientDetails.setText("Select a patient to see their details.");
            return;
        }
        String patientName = patient.getFirstName();
        int patientAge = patient.getAge();
        String patientOccupation = patient.getOccupation();
        String patientIllness = patient.getIllness();
        String patientSymptoms = patient.getSymptoms();
        int patientCriticality = patient.getCriticality();
        patientDetails.setText(
            "Name: " + patientName + " " + patient.getLastName() + "\n"
                + "Age: " + patientAge + "\n" + "Occupation: "
                + patientOccupation + "\n" + "Symptoms: " + patientSymptoms
                + "\n" + "Probable Illness: " + patientIllness);

    }


    private void handlePatientSelected()
    {
        displayPatientDetails(patientList.getSelectedValue());
    }


    public void refreshView()
    {
        scoreLabel.setText("Score = " + game.getScore());

        patientList.clearSelection();
        patientListModel.clear();

        for (Patient patient : game.getPatients().getPatientsInServiceOrder())
        {
            patientListModel.addElement(patient);
        }
        displayPatientDetails(null);
    }


    public void handleTreatNextPatient()
    {
        Patient patientToTreat = game.treatNextPatient();
        if (patientToTreat == null)
        {
            displayMessage("No patients are waiting. ");
            return;
        }
        refreshView();
        String patientFirstName = patientToTreat.getFirstName();
        String patientLastName = patientToTreat.getLastName();
        String messageToDisplay =
            "You have treated " + patientFirstName + patientLastName;
        displayMessage(messageToDisplay);
    }


    private void registerListeners()
    {
        patientList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event)
            {
                if (!event.getValueIsAdjusting())
                {
                    handlePatientSelected();
                }
            }
        });

        treatNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event)
            {
               handleNextArrival();
            }
        });
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {
                handleNewGame();
            }
        });
        
        denyPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("entered action listener");
                handleChoice("Deny");
            }
        });
        admitPatientNButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                handleChoice("Admit");
            }
        });
        
        admitPatientPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                handleChoice("PriorityAdmit");
            }
        });

    }


    public void displayMessage(String message)
    {
        messageLabel.setText(message);
    }


    public void displayScore(int score)
    {
        String scoreNew = String.valueOf(score);
        scoreLabel.setText(scoreNew);
    }


    public void handleNewGame()
    {
        game.startGame();
        refreshView();
        displayPatientDetails(game.getCurrentPatient());
        displayMessage("New Game Has Started");
    }
    private void handleNextArrival()
    {
        Patient nextPatient = game.generatePatient();
        displayPatientDetails(nextPatient);
        displayMessage("New patient arrived.");
    }
    
    private void handleChoice(String choice) {
       game.updateScore(game.getCurrentPatient(), choice);
       int newScore = game.getScore();
       String newScoreInput = String.valueOf(newScore);
       
       boolean addedPatient = false;
       if(choice.equals("Admit") || choice.equals("PriorityAdmit")) {
           addedPatient = game.addPatient(game.getCurrentPatient(), game.getCurrentPatient().getCriticality());
           
       }
       refreshView();
       scoreLabel.setText(newScoreInput);
       handleNextArrival();

       if (addedPatient)
       {
           displayMessage("Added to waitlist.");
       }
       else if (choice.equals("Admit") || choice.equals("PriorityAdmit"))
       {
           displayMessage("Admission rejected.");
       }
    }
    
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                MainMenu game = new MainMenu();
                GameView view = new GameView(game);
                view.showWindow();
            }
        });
    }

}
