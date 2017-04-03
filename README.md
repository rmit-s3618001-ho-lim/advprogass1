# advprogass1

The main class of this program is the Ozlympic class. It will run the program and go to the Driver class where the main menu is displayed.

MAIN MENU
Option 1: Create a new game
- When the user input '1' in the main menu, this option displays a new menu that asks the user 
what kind of game does he want to make.
- If s/he inputs '1' in this create menu, a Swimming game will be created and the user prediction is resetted.
- If s/he inputs '2' in this create menu, a Cycling game will be created and the user prediction is resetted.
- If s/he inputs '3' in this create menu, a Running game will be created and the user prediction is resetted.
- If s/he inputs '4' in this create menu, the user will return to the main menu

Option 2: Predict a winner
- When the user input '2' in the main menu, this option displays the participants in the current game 
and prompts the user to predict a winner
- The user can choose to input 1-8 but if the number that the user input does not have a participant, this option will
inform the user that there is no participant in that option and displays the participant list again.
- Inputting '9' will send the user back to the main menu without making a prediction.

Option 3: Start the game
- When the user input '3' in the main menu, this option will first check if there is enough participants or
if there is a game currently ready. Not meeting either of this will send the user back to the main menu
- If there is a game ready and enough participants, this option will calculate the race time of the participants,
choose its winner, award them their points and inform the user if he predicted the winner correctly
- The user do not have to predict a winner in order to run this option
- The same game can be started again but the points will not be redistributed 

Option 4: Display the final results of all games
- When the user input '4' in the main menu, this option will display the details of every game including its ID, official, and 
its winners

Option 5: Display the points of all athletes
- When the user input '5' in the main menu, this option will display the ID, name and points of every athlete in 
this program

Option 6: Exit
- As implied, when the user inputs '6' in the main menu, this option will exit the program.

