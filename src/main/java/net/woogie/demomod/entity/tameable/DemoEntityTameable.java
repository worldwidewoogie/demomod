package net.woogie.demomod.entity.tameable;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;

public class DemoEntityTameable extends EntityTameable {
	public DemoEntityTameable(World worldIn) {
		super(worldIn);
	}

	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}
}