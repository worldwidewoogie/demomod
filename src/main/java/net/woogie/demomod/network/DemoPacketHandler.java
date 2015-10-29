package net.woogie.demomod.network;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DemoPacketHandler {
	public static final String channel = "extraDimensions";
	public static FMLEventChannel bus;
	private static HashMap<Byte, DemoPacket> packethandlers = new HashMap();

	public static void registerPacketHandler(DemoPacket mPacket) {
		if (packethandlers.get(Byte.valueOf(mPacket.getPacketType())) != null) {
			return;
		}
		packethandlers.put(Byte.valueOf(mPacket.getPacketType()), mPacket);
	}

	public void onPacketData(NetworkManager manager, FMLProxyPacket pkt, EntityPlayer player) {
		try {
			if ((pkt == null) || (pkt.payload() == null)) {
				throw new RuntimeException("Empty packet sent");
			}
			PacketBuffer data = new PacketBuffer(pkt.payload());
			byte type = data.readByte();
			try {
				DemoPacket handler = packethandlers.get(Byte.valueOf(type));
				if (handler == null) {
					throw new RuntimeException("Unrecognized packet sent");
				}
				handler.handle(data, player);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPacketDataClient(FMLNetworkEvent.ClientCustomPacketEvent event) {
		FMLProxyPacket pkt = event.packet;
		onPacketData(event.manager, pkt, Minecraft.getMinecraft().thePlayer);
	}

	@SubscribeEvent
	public void onPacketDataServer(FMLNetworkEvent.ServerCustomPacketEvent event) {
		FMLProxyPacket pkt = event.packet;
		onPacketData(event.manager, pkt, ((NetHandlerPlayServer) event.handler).playerEntity);
	}
}
