# The Waiting Game
**CS 2114 - Project 3: Deliverable 3**  
**Group 72**

**Team Members:** Tyler Berry, Caroline Collier, Megha Dabbeeru, Essan Salem

## Project Overview

The Waiting Game is an emergency room triage simulation where players make decisions about patient priority based on their symptoms and criticality.

Our project follows a Model-View-Controller (MVC) architecture.

- **Model:** Handles patients, queue operations, validation, and scoring.
- **View:** Displays the graphical interface and patient information.
- **Controller:** Connects player actions with the model.

## How to Run

### Running the Model

1. Open the project in Eclipse.
2. Ensure the `Patient Generator` data file is available in the working directory.
3. Run `Driver.java` as a Java application.
4. Observe patient generation, queue operations, and scoring in the console.

### Running the View

The current `GameView.java` contains the initial Swing layout. Event listeners and complete gameplay integration are planned for Deliverable 4.

The view requires a small initialization fix before it can run reliably.

## Reflection

### Changes From Our Specification

Our original specification outlined the complete game architecture, including the model, controller, and graphical interface.

For Deliverable 3, we focused primarily on implementing the core model and establishing the initial structure of the view.

**Changes to the model:**

- Implemented `PatientQueue` using linked nodes and severity-based grouping.
- Implemented patient classification and scoring calculations.
- Created `ChoiceValidator` to check priority selections and patient criticality.
- Added JUnit tests for queue operations, patient behavior, and validation.

**Changes to the controller:**

- `MainMenu.addPatient()` returns a boolean instead of void.
- `MainMenu.updateScore()` accepts both a patient and a classification choice.
- Added a console driver to demonstrate model functionality without a GUI.

**Changes to the view:**

- Created the initial Swing layout.
- Deferred event listeners, interface updates, and complete gameplay functionality to Deliverable 4.

### Model-View Independence

Our model does not depend on the graphical interface.

The `PatientQueue`, `Patient`, `ChoiceValidator`, and `ScoreManager` classes contain the core game logic without requiring Swing.

The `Driver` class demonstrates this separation by exercising model functionality through the controller without creating a GUI.

### Unknowns and Challenges

**Problems we have addressed:**

- Implementing a linked patient queue.
- Separating model logic from the graphical interface.
- Checking valid and invalid player inputs.
- Organizing responsibilities across classes.

**Problems we are still addressing:**

- Connecting the GUI to the controller.
- Implementing complete treatment and game-reset actions.
- Improving patient generation error handling.
- Completing additional tests and integration.

### What Does Not Work Yet?

- GUI buttons do not yet perform gameplay actions.
- Queue information does not automatically refresh in the GUI.
- Some patient generation and invalid-input cases need additional handling.
- The complete graphical gameplay sequence has not yet been implemented.

These features are planned for Deliverable 4.

## Next Steps

### Remaining MVP Features

For Deliverable 4, our team plans to:

1. Finish the graphical interface.
2. Connect GUI event listeners to `MainMenu`.
3. Implement patient treatment and queue updates.
4. Complete score display and game-reset functionality.
5. Improve patient generation and error handling.
6. Expand unit testing and test the complete gameplay sequence.

### Proposed Non-Trivial Change Feature

**Feature: Skip and Requeue a Patient**

We propose adding a feature that allows players to skip the next patient and move that patient to the back of their severity group.

This feature changes queue state and requires additional model logic.

**Predicted classes affected:**

| Class | Expected Changes |
|---|---|
| `PatientQueue` | Add `requeueNextPatient()` |
| `MainMenu` | Add `skipNextPatient()` |
| `GameView` | Add a Skip button and event listener |
| `PatientQueueTest` | Add tests for requeue behavior |

**Classes not expected to change:**

- `Patient`
- `ChoiceValidator`
- `ScoreManager`

We will compare these predictions with the actual implementation in Deliverable 4.

### Stretch Goals

**Planned:**

- Complete patient treatment functionality.
- Implement the new skip/requeue feature.

**Deferred:**

- CSV save/load persistence.
- More complex illness and symptom generation.

### Additional Testing

We plan to add tests for:

- Empty queue operations.
- Invalid patient information.
- Score updates and resets.
- Controller methods.
- Skip/requeue behavior.
- GUI and controller integration.

### Team Responsibilities

| Team Member | Planned Responsibilities |
|---|---|
| Tyler | Patient generation and related testing |
| Caroline | Controller integration and scoring |
| Megha | GUI development and event listeners |
| Essan | Validation logic and testing |
| Team | Queue improvements, integration testing, and debugging |


## Attribution

| Class / Test File | Contributor | Contribution |
|---|---|---|
| `Patient.java` | Tyler | Implementation |
| `PatientTest.java` | Tyler | Patient unit testing |
| `PatientQueue.java` | Megha | Queue implementation |
| `PatientQueueTest.java` | Megha | Queue unit testing |
| `Node` | Megha | Linked-node implementation |
| `ChoiceValidator.java` | Essan | Implementation |
| `ChoiceValidatorTest.java` | Essan | Unit testing |
| `MainMenu.java` | Caroline | Controller implementation |
| `ScoreManager.java` | Megha | Scoring implementation |
| `GameView.java` | Caroline | Initial GUI development |
| `Driver.java` | Caroline | Model demonstration driver |


## GenAI Usage

Our team used GenAI to assist with understanding class responsibilities, designing validation logic, creating and reviewing test cases, and evaluating our implementation against the project specification. We also used GenAI to review how a proposed skip/requeue feature would affect the model, controller, and view.

