# The Waiting Game

**CS 2114 - Project 3: Deliverable 4 — Second Prototype + Tests**  
**Group 72**

**Team Members:** Tyler Berry, Caroline Collier, Megha Dabbeeru, Essan Salem

## Project Overview

The Waiting Game is an emergency room triage simulation where players make decisions about patient admission and priority based on their symptoms, illness, and criticality.

Our project follows a Model-View-Controller (MVC) architecture.

- **Model:** Handles patient generation, queue operations, validation, scoring, and saving/loading game information.
- **View:** Displays the graphical interface, patient information, waiting queue, and score.
- **Controller:** Connects player actions with the model and coordinates gameplay.

## How to Run

### Running the Game

1. Open the project in Eclipse.
2. Ensure the `Patient Generator` data file is available in the project's working directory.
3. Make sure all Java files have consistent package declarations.
4. Open `GameView.java`.
5. Right-click the file and select **Run As → Java Application**.
6. The graphical interface will open, allowing players to make patient admission decisions.

The interface includes buttons for denying admission, admitting patients normally, admitting priority patients, generating another patient, and starting a new game.

The graphical interface communicates with `MainMenu`, which manages the patient queue, validation, and scoring.

### Running the JUnit Tests

1. Open the project in Eclipse.
2. Ensure the required JUnit libraries are included in the build path.
3. Navigate to the `src` folder containing the `waitingGame` package.
4. Right-click an individual test class.
5. Select **Run As → JUnit Test**.
6. Review the JUnit results to identify successful tests, failures, or errors.

The current test files include:

- `PatientTest.java`
- `PatientQueueTest.java`
- `ChoiceValidatorTest.java`
- `MainMenuTest.java`
- `SaveTest.java`

Most tests use JUnit 5, while `MainMenuTest` currently uses JUnit 4.

These tests are designed to evaluate the model independently of the graphical interface.

## Reflection

### Changes From Deliverable 3

For Deliverable 3, our primary focus was creating a functioning model that could operate independently of the graphical interface. We implemented patient generation, queue operations, scoring, and validation while developing the initial structure of our Swing interface.

For Deliverable 4, we focused on connecting the graphical interface to the controller and expanding the functionality available to the player.

**Changes to the model:**

- Continued developing patient generation, classification, and scoring.
- Added additional patient methods, including `getCriticality()` and `setPatient()`.
- Added `Save.java` to begin implementing saving and loading game information.
- Continued using the linked-node `PatientQueue` implementation to manage patients according to their severity.
- Maintained `ChoiceValidator` as a separate class responsible for checking patient criticality and priority selections.

**Changes to the controller:**

- Connected additional gameplay actions to `MainMenu`.
- Continued using controller methods to manage patient generation, admission, treatment, and scoring.
- Allowed the graphical interface to retrieve the current patient, waiting queue, and score through the controller.

**Changes to the view:**

- Expanded `GameView` beyond its initial layout.
- Added action listeners for patient admission decisions.
- Connected the New Game button to the controller.
- Added functionality to generate and display incoming patients.
- Implemented `refreshView()` to update the displayed waiting queue and score.
- Added patient selection functionality to display information about patients currently waiting.

**Changes to testing:**

- Continued testing patient classification, queue operations, and priority validation.
- Added controller tests through `MainMenuTest`.
- Added preliminary saving and loading tests through `SaveTest`.
- Expanded testing coverage to include invalid priority values, null patients, and boundary cases.

### How Our Design Absorbed Change

Our Deliverable 3 README identified **Skip and Requeue a Patient** as the proposed non-trivial change feature.

The proposed feature would allow players to skip the next patient and move that patient to the back of their severity group without removing them from the waiting room.

We originally predicted that implementing this feature would affect `PatientQueue`, `MainMenu`, `GameView`, and `PatientQueueTest`.

However, we have not implemented this feature for Deliverable 4. We decided to prioritize completing the original MVP, including graphical interface integration and saving/loading functionality.

Skip/requeue remains one of our stretch goals.

The following table compares our Deliverable 3 predictions with our current implementation.

| Class | D3 Prediction | D4 Outcome |
|---|---|---|
| `PatientQueue` | Add `requeueNextPatient()` | Not yet implemented |
| `MainMenu` | Add `skipNextPatient()` | Not yet implemented |
| `GameView` | Add Skip button and event listener | Not yet implemented |
| `PatientQueueTest` | Add tests for requeue behavior | Not yet implemented |
| `Patient` | No skip-related changes expected | Updated for other MVP functionality |
| `ChoiceValidator` | No changes expected | No skip-related changes |
| `ScoreManager` | No changes expected | No skip-related changes |

Although the new feature remains unfinished, our existing design separates queue operations, controller responsibilities, and graphical interactions.

This separation provides a foundation for implementing skip/requeue later without requiring significant changes to unrelated classes.

### Model-View Independence

Our model continues to operate independently of the graphical interface.

The `Patient`, `PatientQueue`, `ChoiceValidator`, and `ScoreManager` classes do not rely on Swing components to perform their primary responsibilities.

The updated `GameView` communicates with `MainMenu` to perform gameplay actions.

For example, when a player selects an admission decision:

1. `GameView` receives the player's selection.
2. The controller processes the decision through `MainMenu`.
3. The appropriate model methods update scoring and patient placement.
4. `GameView` refreshes its displayed score and waiting queue.

This allows the model to remain separate from the graphical interface.

The view does maintain a `DefaultListModel` to display patients, but this list is rebuilt using the current queue information whenever `refreshView()` executes.

Consequently, the displayed list is intended to reflect the model rather than act as a separate source of game state.

One area that could be improved is how `GameView` accesses queue information. Currently, the view retrieves the queue through `MainMenu` and calls `getPatientsInServiceOrder()`.

A future improvement would be to provide a controller method that returns the information needed by the view without exposing the queue directly.

### What Changed From Our D3 Plan?

Our Deliverable 3 Next Steps identified several priorities for Deliverable 4.

Some of these have progressed as planned, while others remain incomplete.

**Completed or partially implemented:**

- Expanded the graphical interface.
- Connected several GUI event listeners to controller methods.
- Implemented patient admission buttons.
- Added functionality for updating the score display.
- Added functionality for refreshing the waiting queue.
- Added preliminary saving and loading functionality.
- Added additional unit tests.

**Changes from our original plan:**

- We prioritized completing existing MVP functionality before implementing skip/requeue.
- Saving/loading remains part of our MVP rather than replacing the proposed change feature.
- Some methods and test cases were added to support the expanded interface and persistence functionality.
- Additional testing and error handling are still required before the complete gameplay sequence can be considered reliable.

### What Does Not Work Yet?

Several features and issues remain unresolved.

- Skip/requeue has not yet been implemented.
- Saving/loading has not been fully connected to the controller and graphical interface.
- The treatment button currently generates another patient instead of calling the existing treatment method.
- Some patient generation and missing-file cases may still cause errors.
- Some invalid-input cases require additional handling and testing.
- The priority-admission strings are inconsistent between parts of the program.
- The current `GameView` and model files have different package declarations that need to be reconciled.
- The complete test suite has not yet been verified as passing.
- `ScoreManagerTest.java` has not yet been implemented.

These issues will be prioritized as we prepare the final version of the project.

### Design Improvements for Future Features

One improvement that would make future features easier to implement is creating a clearer interface between `MainMenu` and the model.

Currently, `GameView` retrieves the queue through the controller to display patient information.

Providing controller methods that return only the information needed by the GUI would reduce direct dependencies between the view and individual model classes.

We could also standardize the representation of patient admission categories to eliminate inconsistencies between `"Priority Admit"` and `"PriorityAdmit"`.

These changes would make additional features, such as skip/requeue, easier to implement and test without requiring changes throughout the application.

## Next Steps

### Remaining Features for the Final Build

For the final version of The Waiting Game, our team plans to complete the following tasks in order:

1. Resolve remaining compilation and package compatibility issues.
2. Finish connecting the treatment functionality to the graphical interface.
3. Complete saving and loading functionality and integrate it into the controller and view.
4. Improve error handling for patient generation and invalid inputs.
5. Verify that queue operations, scoring, and patient classification work consistently.
6. Expand JUnit testing and resolve any failing tests.
7. Test the complete gameplay sequence through the graphical interface.
8. Prepare and rehearse our final demonstration.

Our primary goal is to complete and stabilize the MVP before adding additional functionality.

### Stretch Goals

**Committed MVP functionality:**

- Patient generation and classification.
- Patient admission decisions.
- Priority-based waiting queue.
- Patient treatment.
- Scoring.
- Saving and loading game information.
- Graphical interface integration.

**Stretch goal retained for consideration:**

- **Skip and Requeue a Patient:** Allow players to move the next patient to the back of their severity group without changing their criticality or score.

**Deferred stretch goals:**

- More complex illness and symptom generation.
- Additional patient information and expanded gameplay mechanics.

We will focus on completing the MVP before deciding whether sufficient time remains to implement additional stretch features.

### Three-Minute Demonstration Plan

For our final presentation, we plan to demonstrate the core gameplay sequence and the interaction between our graphical interface and model.

| Time | Demonstration |
|---|---|
| 0:00–0:30 | Launch the game and introduce the patient triage interface. |
| 0:30–1:00 | Display an arriving patient's information and explain the available admission choices. |
| 1:00–1:30 | Make several admission decisions and demonstrate changes to the score and waiting queue. |
| 1:30–2:00 | Treat a patient and demonstrate how the queue updates. |
| 2:00–2:30 | Demonstrate saving and loading the game once this functionality is complete. |
| 2:30–2:50 | Demonstrate an invalid-input or empty-queue case without crashing. |
| 2:50–3:00 | Summarize the project's design, testing, and remaining limitations. |

This is our planned demonstration for the final build. Any features that remain incomplete will be excluded from the live demonstration and identified as limitations.

If skip/requeue is completed, we will incorporate it into the demonstration.

### Team Responsibilities

| Team Member | Responsibilities |
|---|---|
| Tyler Berry | Continue improving patient generation, error handling, and patient-related testing. |
| Caroline Collier | Complete controller integration, treatment functionality, and scoring interactions. |
| Megha Dabbeeru | Continue developing the graphical interface, queue functionality, and associated testing. |
| Essan Salem | Verify validation logic, expand validation tests, and help identify invalid-input issues. |
| Entire Team | Complete integration testing, resolve outstanding problems, update documentation, and prepare the final demonstration. |

## Attribution

| Class / Test File | Contributor | Contribution |
|---|---|---|
| `Patient.java` | Tyler | Patient implementation, generation, and revisions |
| `PatientTest.java` | Tyler | Patient unit testing |
| `PatientQueue.java` | Megha | Queue implementation and ordering logic |
| `PatientQueueTest.java` | Megha | Queue unit testing |
| `Node.java` | Megha | Linked-node implementation |
| `ChoiceValidator.java` | Essan | Validation implementation |
| `ChoiceValidatorTest.java` | Essan | Validation unit testing |
| `MainMenu.java` | Caroline | Controller implementation |
| `MainMenuTest.java` | Caroline | Controller unit testing |
| `ScoreManager.java` | Megha | Scoring implementation |
| `GameView.java` | Megha | GUI implementation and event listeners |
| `Save.java` | Tyler | Saving and loading implementation |
| `SaveTest.java` | Tyler | Saving and loading tests |

## GenAI Usage
Our team used GenAI to assist with understanding class responsibilities, designing validation logic, creating and reviewing test cases, and evaluating our implementation against the project specification. We also used GenAI to review how a proposed skip/requeue feature would affect the model, controller, and view.

