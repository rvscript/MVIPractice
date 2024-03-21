/*
Simple MVI practice
*/
Set up an MVI.

Model: Unlike other patterns, In MVI Model represents the state of the UI. i.e for example UI might have different states like Data Loading, Loaded, Change in UI with user Actions, Errors, User current screen position states. Each state is stored as similar to the object in the model.
View: The View in the MVI is our Interfaces which can be implemented in Activities and fragments. It means to have a container which can accept the different model states and display it as a UI. They use observable intents(Note: This doesn't represent the Android traditional Intents) to respond to user actions.
Intent: Even though this is not an Intent as termed by Android from before. The result of the user actions is passed as an input value to Intents. In turn, we can say we will be sending models as inputs to the Intents which can load it through Views.
