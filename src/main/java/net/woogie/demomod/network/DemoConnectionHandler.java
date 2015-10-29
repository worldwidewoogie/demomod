package net.woogie.demomod.network;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.woogie.demomod.Config;

public class DemoConnectionHandler {
	private static boolean connected = false;

	@SubscribeEvent
	public void clientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
		connected = true;
	}

	@SubscribeEvent
	public void clientDisconnectionFromServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
		if (connected) {
			connected = false;
		}
	}

	@SubscribeEvent
	public void serverConnectionFromClient(FMLNetworkEvent.ServerConnectionFromClientEvent event) {
		event.manager.sendPacket(DemoBiomeIdPacket.createPacket(Config.biomeId));
	}
}
