#TicTacToe

A simple command line application written in Kotlin to play TicTacToe against the computer.

Here's a sample of the application:
```
Welcome to TicTacToe
The X will start the game, now I'll randomly choose who of us will be the X player.
You'll be the X player, so it's your turn.
Insert your move > B2
 | | 
-+-+-
 |X| 
-+-+-
 | | 

I'm now thinking...
O| | 
-+-+-
 |X| 
-+-+-
 | | 

Insert your move > 
```


## Build
Build it with:
```
gradle jar
```
and it will create a JAR file into the build/libs directory.

## Launch
Now you can execute the JAR just by using the `java` executable:
```
java -jar build/libs/tictactoe-1.0-SNAPSHOT.jar 
```
This call will default to a 3x3 sized grid; if you want to play on bigger grids (with maximum size of 9x9), you can add an argument to the call:
```
java -jar build/libs/tictactoe-1.0-SNAPSHOT.jar 7

```
that will create a 7x7 grid.

## How to play
The game starts with the random choice of the X player between the user and the AI of the program. The X player is always the first of the game.When you have to insert your move, you must enter a letter (starting from 'A') for the column and a number (starting from 1) for the row.
```
A1

```
will insert your sign into first column and first row.

If you type:
```
C2
```
it will insert your sign into the third column and second row.

## Info on program
This program uses the [Minimax](https://en.wikipedia.org/wiki/Minimax) algorithm to make the AI choose the best move. The depth of the recursion is based on the size of the grid to avoid endless computation. To determine which depth to use, I used a simple heuristic based on computation time on my box to have a response in not more than 10 seconds.
