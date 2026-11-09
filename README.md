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

**Changes to validation and testing:**
- Our specification placed patient priority validation in
  `ChoiceValidator`, which is how we implemented it.
  We kept validation separate from queue and scoring logic
  so that it can be tested independently.
- Our specification included testing for normal, invalid,
  and extreme inputs. We implemented JUnit tests for
  `ChoiceValidator`, `PatientQueue`, and `Patient`.
- Since `Patient` does not have a constructor that allows
  us to specify criticality, we used a helper method with
  reflection in `ChoiceValidatorTest` to assign known
  criticality values for testing.
- We still need to verify how exact numerical priority
  validation should interact with the queue's severity
  categories.

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

**Predicted classes and methods affected:**

| Class | Expected Changes |
|---|---|
| `PatientQueue` | Add `requeueNextPatient()` to move a patient to the back of their severity group. |
| `MainMenu` | Add `skipNextPatient()` to connect the player's action to the queue. |
| `GameView` | Modify `buildLayout()` to add the Skip button and implement its event listener. |
| `PatientQueueTest` | Add tests for `requeueNextPatient()` covering empty queues, one patient, and multiple patients in different priority groups. |

**Classes not expected to change:**

- `Patient`: Patient information and criticality will remain unchanged.
- `ChoiceValidator`: The existing priority validation rules should not need modifications.
- `ScoreManager`: Skipping will not affect the score under the proposed rules.

This prediction assumes skipping does not change a patient's
criticality or the player's score.

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
| `GameView.java` | Megha | Initial GUI development |
| `Driver.java` | Caroline | Model demonstration driver |


## GenAI Usage

Our team used GenAI to assist with understanding class responsibilities, designing validation logic, creating and reviewing test cases, and evaluating our implementation against the project specification. We also used GenAI to review how a proposed skip/requeue feature would affect the model, controller, and view.

