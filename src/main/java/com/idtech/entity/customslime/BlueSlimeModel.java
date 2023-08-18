package com.idtech.entity.customslime;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.idtech.BaseMod;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

/** Extend SlimeModel instead of EntityModel */
/* SlimeModel extends HierarchicalModel instead of the usual EntityModel,
 * which allows for multiple layers (createOuterBodyLayer() and createInnerBodyLayer()) */
public class BlueSlimeModel<T extends Entity> extends SlimeModel<T> {
	/** Don't think it matters too much what you name these here, just make sure ResourceLocation follows lowercase, no spaces */
	public static final ModelLayerLocation INNER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BaseMod.MODID, "blueslime"), "main");
	public static final ModelLayerLocation OUTER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BaseMod.MODID, "blueslime_outer"), "main");

	/** Change constructor and remove bb_main */
	public BlueSlimeModel(ModelPart root) {
		super(root);
	}

	/** Move the line(s) containing the cube(s) for the outer layer here
	 * into a new function createOuterBodyLayer(); the last 3 parameters
	 * in .addBox() tells you the dimensions of each cube so that can be
	 * helpful in knowing which cube is which part of the slime */
	/* addOrReplaceChilds() etc. and no bb_main have also been changed from
	 * default Blockbench export to look more like SlimeModel's code just to be safe;
	 * matching names have also been added to make sure animations etc. work correctly.
	 * If you're doing this, make sure the names match the correct pieces. */
	public static LayerDefinition createOuterBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("cube", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	/** Move the line(s) containing the cube(s) for the inner layer here
	 * into a new function createInnerBodyLayer() */
	public static LayerDefinition createInnerBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("cube", CubeListBuilder.create().texOffs(0, 20).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(0, 3).addBox(-3.0F, -7.0F, -4.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -7.0F, -4.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		partdefinition.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(5, 2).addBox(0.0F, -4.0F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	/** Remove renderToBuffer() */
}