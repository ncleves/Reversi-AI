# Reversi-AI

#####Two different implementations of "perfect information" Artificial Intelligence: One learning, using a Q-Learning/SARSA reinforcement learner, the other searching, using the Minimax searching algorithm modified with Alpha-beta pruning.#####

<p align="center">
  <img src="https://static1.squarespace.com/static/568df01e69a91a35061d8ba9/t/56d36bb7a3360c0b24e36575/1456696505695/?format=1500w"   alt="Reversi Alphabeta vs SARSA"/>
</p>
This GIF demonstrates a learning AI plays against a searching AI. Can you guess which AI is the winner?

***
###Making a move###

|Initial Board             |  Where dark may play      |Dark's move    |
|:-------------------------:|:-------------------------:|:---------------:|
|![initboard](https://static1.squarespace.com/static/568df01e69a91a35061d8ba9/t/56d7557986db43c1b33ed766/1456952773230/initboard.png?format=500w)|![moves1](https://static1.squarespace.com/static/568df01e69a91a35061d8ba9/t/56cb7dd2d210b842c72358a2/1456176893174/?format=500w)|![darkplay1](https://static1.squarespace.com/static/568df01e69a91a35061d8ba9/t/56d7558286db43c1b33ed7f0/1456952706934/darkplay1.png?format=500w)|


The class "TestReversi" is where the game can be started from. Settings can be modified in this class, currently by commenting or uncommenting parts of code.
TestReversi is currently set up so that a human player can play against the reinforcement learner.
Make a move by specifying a row and a col like so: 3 4 (row 3, col 4)

Features to add in the future: Ask the user when they start the game who they would like to play against, a searching AI, a learning AI, or a human.
Or if they would like, they can watch two AI play against each other. (Remove having to comment and uncomment code to change who plays the game).
