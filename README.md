# GameTheory

This game theory application is based on *prizes* and *actors*. 
- Prizes are a resource with an energy value of 1.
- Actors conform the population of the game and will fight each other for the resources in order to survive and reproduce.

For each round, if the amount of actors is lower or equal to the amount of prizes each actor will be assigned to a different prize. Otherwise, up to two actors will be randomly assigned to each prize. When an actor is assigned a prize, it will perform one of three actions. If another actor has also been assigned to the same prize, the outcome of the interaction influences the amount of energy each actor recieves.
    
		+--------+------+-------+------|
		| Action |   Opponent action   |
		+--------+------+-------+------|
		|        | Give | Share | Take |
		+--------+------+-------+------|
		| Give   | 0    | 0     | 0    |
		| Share  | 1    | 0.5   | 0    |
		| Take   | 1    | 1     | 0    |
		+--------+------+-------+------|

In order to advance to the next round, an actor requires a minimum amount of energy. If the actor manages to obtain a surplus of energy, it will be able to reproduce and an additional actor will join the population. However, if an actor doesn't have any remaining energy at the end of a round it will be removed from the population permanently.

### Behaviours

The strategies defined in the project for the different actors are as follows:
- IDLE: Always *GIVE*.
- SHARE: Always *SHARE*.
- TAKE: Always *TAKE*.
- RANDOM: Random action.
- COPYCAT: Repeats the last action seen.
- VENGEFUL: *SHARE* until an opponent *TAKE*s. Then always *TAKE*.


###Usage:

	GameTheory <iterations> <prize_count> <population_string>
	
**Iterations**:
- Number of rounds to run the game for.
	
**Prize count**:
- Number of prizes that the population will have access to.
	
**Population string**:
- An encoded string that determines the initial population.
- Follows the format `"BehaviourType Count;BehaviourType Count...`
    - E.g. `IdleBehaviour 20;RandomBehaviour 40;CopycatBehaviour 5`