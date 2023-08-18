# Zombo, but Thor

Yes, I know there shouldn't be a readme here.

# Steps

1. Make your Lightning Hammer and Zombo/Thor as usual, following all the steps on Gameplan.
    * Use the reference code here to equip Thor with a lightning hammer on spawn.
    * The reference code here also includes code to prevent damage from its own lightning, and makes it not burn in the day.
2. Add a new method to LightningHammerItem.java that allows passing in of a non-player LivingEntity and also summons lightning at Thor's attack target instead of where it's looking (reference code here).
3. Create a custom behavior goal for Thor and copy the reference code from ZombieThorAttackGoal.java. This new goal simulates right-click by calling that new method in LightningHammerItem.java we made, and also prevents Thor from being limited to melee range with the lightning.
4. Override addBehaviorGoals() in Thor (reference code also included) to use our new goal instead of the original ZombieAttackGoal.