package waitingGame;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class MainMenuTest
{
    private MainMenu game;
    
    @Before
    public void setUp() {
        game = new MainMenu();
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(game);
        
        assertNotNull(game.getCurrentPatient());
        assertEquals(0, game.getScore());
        assertTrue(game.getPatients().isEmpty());
    }
    
    @Test
    public void testStartGame() {
        game.startGame();
        
        assertEquals(0, game.getScore());
        assertTrue(game.getPatients().isEmpty());
        assertNotNull(game.getCurrentPatient());
    }
    
    @Test
    public void testGeneratePatient() {
        Patient patient = game.generatePatient();
        
        assertNotNull(patient);
        assertNotNull(patient.getFirstName());
        assertNotNull(patient.getLastName());
        assertTrue(patient.getAge() > 0);
        assertNotNull(patient.getOccupation());
        assertNotNull(patient.getIllness());
        assertNotNull(patient.getSymptoms());
        assertTrue(patient.getCriticality() > 0);
    }

    @Test
    public void testAddPatient()
    {
        Patient patient = game.getCurrentPatient();

//        boolean result =game.addPatient(patient, patient.getCriticality());
//
//        if (patient.getCriticality() > 33) {
//            assertTrue(result);
//        }
//        else {
//            assertFalse(result);
//        }
    }

    @Test
    public void testValidateChoice()
    {
        Patient patient = game.getCurrentPatient();
        assertTrue(game.validateChoice(patient, patient.getCriticality()));
        assertFalse(game.validateChoice(patient, -1));
    }

    @Test
    public void testUpdateScore()
    {
        Patient patient = game.getCurrentPatient();
        game.updateScore(patient, patient.classifyPatient());
        int currentScore = game.getScore();
        game.updateScore(null, "Admit");
        assertEquals(currentScore, game.getScore());
    }
    
    @Test
    public void testGetPatients()
    {
        assertNotNull(game.getPatients());
        assertTrue(game.getPatients().isEmpty());
    }

    @Test
    public void testGetScore()
    {
        assertEquals(0, game.getScore());
    }

    @Test
    public void testEndGame()
    {
        game.endGame();
        assertTrue(true);
        
        MainMenu newGame = new MainMenu();
        newGame.endGame();
        assertTrue(true);
    }

}