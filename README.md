# advprogass1

The main class of this program is the Ozlympic class. It will run the program and go to the Driver class where the main menu is displayed.

MAIN MENU
Button 1: Create a new game
- When the user click on this button in the main menu, this option displays a new menu that asks the user 
what kind of game does he want to make.
- If s/he clicks on Swimming Game in this create menu, a Swimming game will be created.
- If s/he clicks on Cycling Game in this create menu, a Cycling game will be created.
- If s/he clicks on Running Game in this create menu, a Running game will be created.
- If s/he clicks on return to previous menu, the user will return to the main menu
- For now, we will assume the user clicks on Swimming Game. The user can click on the Add Athletes button to choose an athlete to add into the game. The user must input the ID of a swimmer/superathlete in a given list to add the athlete into the game.
- The user can click on Add Official button to choose an official to add to the game. The user must input the ID of an official in a given list to add him to the game
- Clicking on "Return to main menu" will first show the details of the created game before returning to the main menu. Once this button is clicked, the user can no longer add any more athletes and officials to the game

Button 2: Start the game
- When the user click on this button in the main menu, this option will first check if there is enough participants, a referee, whether the game has already been run and if there is a game currently ready. Not meeting any of this will send the user back to the main menu
- If all of the conditions above holds true, this option will calculate the race time of the participants and 
choose its winner. Then, the user would be able to view the results before returning to the previous menu.
- The game results would be written in a text file and the database

Button 3: Display the final results of all finished games
- When the user clicks on this button in the main menu, this option will display the details of every finished game including its ID, official, and its winners

Button 4: Display the points of all athletes
- When the user clicks on this button in the main menu, this option will display the ID, name and points of every athlete in 
this program

Button 5: Exit
- As implied,clicking on this button will exit the program.

