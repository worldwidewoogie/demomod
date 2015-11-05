package net.woogie.demomod.entity.mob;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class DemoEntityMob extends EntityMob {
	public DemoEntityMob(World worldIn) {
		super(worldIn);
	}

	public boolean isAIEnabled() {
		return true;
	}
}