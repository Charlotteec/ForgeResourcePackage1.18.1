# New Slime-based Entity

Yes, I know there shouldn't be a readme here.

Slimes are tricky since their models have two layers, a translucent outer layer and an opaque inner layer. Thus, the visuals will be a bit different from what is outlined in the Game Plan/Rise curriculums (especially the renderer—there are two of them now, BlueSlimeRenderer.java for the inner layer and BlueSlimeOuterLayer.java for the outer layer just like vanilla slime code).

Big thanks to Kaupenjoe for particles (https://www.youtube.com/watch?v=DM69u76tXMI).

# Slime Steps

1. In Blockbench, make both outer and inner layers in one modded entity (change brush/fill bucket opacity on the top of the Blockbench UI for translucent outer layer).
    * Take the usual considerations (entity faces north, centered around origin, doesn't go below "ground").
    * Make sure the front of eyes/mouth do not align with the outer layer to avoid Z-fighting (texture flickering), and back of eyes/mouth also don't align with the inner body surface.
    * Avoid decimal sizes of cubes (you can make the eyes/mouth 1 pixel large on the Z-axis and move them 0.5 pixels into the inner body so they don't align with either the inside or outside layers).
    * For the easiest coding experience, match the model size of the vanilla slime (6 pixels inner layer, 8 pixels outer).
2. Export as Java entity and save texture as usual, following Game Plan.
3. Edit the exported .java file using detailed instructions in the BlueSlimeModel.java file included in this repository.
4. Copy in BlueSlimeOuterLayer.java and BlueSlimeRenderer.java from this repository instead of Game Plan; render factory not needed.
5. Do BlueSlimeEntity.java as usual, following Game Plan (reference also included here).
    * In BlueSlimeEntity.java, use the reference to update hitboxes if you did not match the model size of the vanilla slime.
6. Copy in registrations made in EntityMod.java and the bottom of BaseMod.java from this repository (there are small differences from Game Plan).
7. This reference does not cover natural spawning though, especially in slime chunks (overriding a certain percentage of slime spawns with an event listener also doesn't work unless you want normal slimes to sometimes split into blue slimes).

# Changing Slime Particle Steps

1. Draw a particle texture and put it into resources/assets/examplemod/textures/particle (singular "particle").
    * Unfortunately, it seems very difficult to replicate the exact slime's particle and change only its color, since those particles seem to come from the slimeball through a hardcoded particle provider class.
    * If anyone figures this out please make it look better!
2. Copy in BlueSlimeParticle.java, ParticleMod.java, and RegisterParticleFactories.java.
3. Copy in registerParticles() to BaseMod.java after registerBlocks(), registerItems() etc. to register your new particle.
4. Copy in resources/assets/examplemod/particles/item_blue_slime.json (plural "particles"). This is like the model file that points the code towards the texture from step 1, so make sure the name after "examplemod:" in this file matches your texture file's name from step 1.
5. In BlueSlimeEntity.java, override getParticleType() to tell the slime to use the new particles.
