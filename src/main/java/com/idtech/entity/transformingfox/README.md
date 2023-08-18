# Transforming an entity

Yes, I know there shouldn't be a readme here.

This example code allows you to transform a normal fox into a new cake fox when you feed it a cake.

# Steps

1. Make your new mob in Blockbench as usual, following all the steps on Gameplan.
    * Preferably, also label all the model parts and copy in setupAnim() from FoxModel.java to make sure animations work.
    * If your new mob is going to be tamable, use reference code in CakeFox.java (thanks to Kaupenjoe) for boilerplate and other methods that allows taming to work. I also inherited my cake fox from wolf instead of fox to simplify the taming stuff; this does mean unfortunately that you'd have to fix the sounds (also in CakeFox.java).
2. Make a new mob that will be identical to the pre-transformation mob (in this case, a fox) except for the fact that it will have an overriden mobInteract() that allows it transform when right clicked (see CustomFox.java for code reference)
    * Make the renderer, render factory, name (name it the same way the vanilla mob is named to make it as identical as possible), and registration for this custom mob too. Don't make a model for this one, and paste in the default mob texture from client.jar of a Minecraft 1.18.1 installation (or here).
3. Create events/EventMod.java and use the reference code here to replace all spawned pre-transformation mobs (foxes) with your identical custom one. This allows us to effectively override all foxes in the game to give them the ability to transform.
