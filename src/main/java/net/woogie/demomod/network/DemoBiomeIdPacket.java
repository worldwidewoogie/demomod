package net.woogie.demomod.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.woogie.demomod.Config;

public class DemoBiomeIdPacket extends DemoPacket {
	public static final int packetType = 1;

	public static FMLProxyPacket createPacket(Collection<Integer> set) {
		ByteBuf byteBuf = Unpooled.buffer();

		PacketBuffer data = new PacketBuffer(byteBuf);

		data.writeByte(packetType);
		data.writeInt(set.size());
		for (Integer dimID : set) {
			data.writeInt(dimID.intValue());
		}
		return buildPacket(data);
	}

	public static FMLProxyPacket createPacket(Integer dim) {
		ArrayList set = new ArrayList();
		set.add(dim);
		return createPacket(set);
	}

	@Override
	public byte getPacketType() {
		return packetType;
	}

	@Override
	public void handle(PacketBuffer networkData, EntityPlayer paramEntityPlayer) {

		int length = networkData.readInt();

		if (length != 1) {
			Config.biomeId = networkData.readInt();
		}
	}
}
