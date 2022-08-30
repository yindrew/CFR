## Poker Bot - A Exploration into Counterfactual Regret Minimization (CFR)

### CFR and its Applications
CFR is a machine learning algorithim that is used to solve imperfect-information games. This technique is based on regret minimization where faced with a decision, players choose the decision with the highest regret value. Pluribus, a poker bot that utilized Monte Carlo CFR, was the first AI bot capable of beating human experts in six-player no-limit Hold'em, the most widely played poker format in the world. 

### Rock Paper Scissors
We start by examining the game of Rock Paper Scissors. The bot picks a decision at random (rock or paper or scissors), and than calculates the regret of not picking the other decisions. It than updates its overall strategy by factoring in the regret and thereby changing the frequencies at which to choose decisions. 

<br> **Strategy**: Suppose player one chooses rock and the bot randomly picks scissors. It starts by giving the actual pick a score of -1. It than takes a look at the other 2 decisions. it gives paper and rock a score of 1 and 0 respectively. It will than update it's strategy by picking rock and paper at higher frequency of the time the next time it plays.  
<br> **Examples**: How does a bot respond when a player picks rock 40% of the time, paper 30% of the time, and scissors 30% of the time? What about 30% 35% 35% respectively for rock paper and scissors? What about when we put 2 bots to play against each other?

![image](https://user-images.githubusercontent.com/61204939/187297927-b14130e5-88f3-4cff-84c1-812c70a31cb3.png) <br>
![image](https://user-images.githubusercontent.com/61204939/187298515-39b72e4e-3e83-4b44-a862-f0919e647fbb.png) <br>
![image](https://user-images.githubusercontent.com/61204939/187298976-222a1b10-9c03-43a4-b2f5-b498fce1a54c.png) <br>

**Takeaways**: A bot using CFR will find an optimal solution against the strategy it is playing against. In the first example where the player is playing (.4, .3, .3), we see that the bot adjust by playing paper almost 100% of the time, knowing that the player is throwing rock at a higher frequency than he is supposed too. In the second example where a player pis playing (.3, .35, .35), we see that the bot almost exclusively plays scissors. which the majority of the time will result in a win or a tie. If two bots are both using CFR than we reach a equilibrium where both bots will simply pick any of the three choices at random.

### Colonel Blotto
Colonel Blotto is a game played by 2 players. each player will have N soldiers in total. And each player will assign each of the N soldiers onto one of S battlefields. A player claims a battlefield if they send more soldiers to a battlefield than the other player. We examine how a bot using CFR will play the game. Our version of Colonel Blotto will have 5 soldiers to distribute among 4 battlefields

<br> **Strategy**: The bot will initially be randomly placing soldiers down on each of the three battlefields. It will than compare how it would have done with other strategies and will slowly pick the actions that have the highest regret values. Strategies could be defined as 302 - representing 3 soldiers on the first battlefield 0 on the second and 2 on the third. 

<br> **Examples**: How does the bot respond with a player only using the strategy 221? What about 302? What about another bot? <br>
![image](https://user-images.githubusercontent.com/61204939/187317514-63c7def0-376a-4c35-82ec-ebec66b1e8df.png)
![image](https://user-images.githubusercontent.com/61204939/187317533-5ad48b27-d94e-46e8-856e-ad357f80ccff.png)
![image](https://user-images.githubusercontent.com/61204939/187317818-188d55b0-6e29-4a02-853d-9eaa0d4860dd.png)

**Takeaways**: We see that the bot is able to accurately find the best strategies against a fixed strategy. We see that when two bots play each other, they reach a equilibrium where they randomly select between the 9 strategies shown above.  



### Kuhn Poker
Kuhn poker is a game played by 2 players. Each player will draw a card from a deck holding 3 cards (1, 2, 3) without replacement. There are 2 actions a player can take, pass or bet. Players make actions alternatively. Shown below is a summary of possible play sequences with resulting chip payoffs. <br>
![image](https://user-images.githubusercontent.com/61204939/187319092-454ba419-3c7e-40e3-9159-d43620b48b01.png)

<br> **Strategy**: The bot will try and figure out what the best action is with the current information. It will initially start making actions at random, but using depth first search(dfs), will find the regret values at each decision node for all possible actions. This will eventually lead the bot to have a strategy that is superhuman. 

**Example**: With two bots playing against each other, we see what the optimal solution to this game will be
![image](https://user-images.githubusercontent.com/61204939/187321937-f5c22dbd-fcd9-4f8a-b764-808323f57633.png)


**Takeaways**: What we are shown here is the strategies based on the gamestate the player is currently in. For example, if the game state is 1b, it represents the player is currently holding a 1, as well as the fact that the other player has currently bet. It then shows that if the player is holding a 1 and is facing a bet, the other player must have a higher card. The bot recognizes this and passes 100% of the time when facing a bet.  


### Referenced Papers
https://ai.facebook.com/blog/pluribus-first-ai-to-beat-pros-in-6-player-poker/ <br>
http://modelai.gettysburg.edu/2013/cfr/cfr.pdf <br>
https://www.science.org/doi/10.1126/science.aay2400












