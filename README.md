# Escape-jam
Jam submission for DevSoc's Game Jam in October 2024!

# More about the project
The jam was held by the university's game development society (DevSoc). It was under the theme "don't let it escape" and it lasted for a week.
I decided to go with a turn-based hidden movement type of game.


You are placed in a 3x3 grid, and you need to navigate from the bottom middle space to your goal in the top middle space. However there is a creature that starts at your goal and wants to go to the bottom middle space (where you start). You and the creature take turns moving, but you cannot see where each other are. On your turn, you may move to an adjacent accessible room or block off a corridor to an adjacent room. The creature simply moves once on its turn (it was going to have some decision making and pathfinding, but i could not implement it in time). You win if you reach your goal before the creature reaches your starting location, without the creature catching up to you. You lose if the creature reaches your starting location, or if it shares a space with you.


The game runs on the terminal, by printing on it and reading from it. I figured it would take less effort making graphics and I wanted to play around with the text input and output. I want to return to this eventually and complete the game! (Update: Alternatively, simply run App.java in the bin directory! Make sure Java 17 or higher is installed :)


Update: I now have some spare time to finish this small project! I'll work on this incrementally and make regular commits (hopefully). You can see my to-do list in the issues section!