## Poker Bot - A Exploration into CounterFactual Regret Minimization (CFR)

### CFR and its Applications
CFR is a machine learning algorithim that is used to solve imperfect-information games. This technique is based on regret minimization where faced with a decision, players choose the decision with the highest regret value. Pluribus, a poker bot that utilized Monte Carlo CFR, was the first AI bot capable of beating human experts in six-player no-limit Hold'em, the most widely played poker format in the world. 

### Rock Paper Scissors
We start by examining the game of Rock Paper Scissors. The bot picks a decision at random (rock or paper or scissors), and than calculates the regret of not picking the other decisions. It than updates its overall strategy by factoring in the regret and thereby changing the frequencies at which to choose decisions. 

<br> **Strategy**: Suppose player one chooses rock and the bot randomly picks scissors. It starts by giving the actual pick a score of -1. It than takes a look at the other 2 decisions. it gives paper and rock a score of 1 and 0 respectively. It will than update it's strategy by picking rock and paper a higher frequency of the time the next time it plays.  
<br> **Examples**: How does a bot respond when a player picks rock 40% of the time, paper 30% of the time, and scissors 30% of the time? What about 30% 35% 35% respectively for rock paper and scissors? What about when we put 2 bots to play against each other?

![image](https://user-images.githubusercontent.com/61204939/187297927-b14130e5-88f3-4cff-84c1-812c70a31cb3.png) <br>
![image](https://user-images.githubusercontent.com/61204939/187298515-39b72e4e-3e83-4b44-a862-f0919e647fbb.png) <br>
![image](https://user-images.githubusercontent.com/61204939/187298976-222a1b10-9c03-43a4-b2f5-b498fce1a54c.png) <br>

**Takeaways**: A bot using CFR will find an optimal solution against the strategy it is playing against. In the first example where the player is playing (.4, .3, .3), we see that the bot adjust by playing paper almost 100% of the time, knowing that the player is throwing rock at a higher frequency than he is supposed too. In the second example where a player pis playing (.3, .35, .35), we see that the both almost exclusively plays scissors. which the majority of the time will result in a win or a tie. If two bots are both using CFR than we reach a equilibrium where both bots will simply pick any of the three choices at random.

### Colonel Blotto
Colonel Blotto is a game played by 2 players. each player will have N soldiers in total. And each player will assign each of the N soldiers onto one of S battlefields. A player claims a battlefield if they send more soldiers to a battlefield than the other player. We examine how a bot using CFR will play the game. Our version of Colonel Blotto will have 5 soldiers to distribute among 4 battlefields

<br> **Strategy** The bot will initially be randomly placing soldiers down on each of the three battlefields. It will than compare how it would have done with other strategies and will slowly pick the actions that have the highest regret values. Strategies could be defined as 302 - representing 3 soldiers on the first battlefield 0 on the second and 2 on the third. 

<br. **Examples** How does the bot respond with a player only using the strategy 221? What about 301? What about another bot? <br>






