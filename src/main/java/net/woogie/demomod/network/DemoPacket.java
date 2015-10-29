package net.woogie.demomod.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public abstract class DemoPacket {
	public abstract void handle(PacketBuffer packetBuffer, EntityPlayer paramEntityPlayer);

	public abstract byte getPacketType();

	protected static FMLProxyPacket buildPacket(PacketBuffer payload) {
		return new FMLProxyPacket(payload, DemoPacketHandler.channel);
	}
}
