package com.gmail.vapidlinus.murder.tools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_9_R2.EntityHuman;
import net.minecraft.server.v1_9_R2.EntityTracker;
import net.minecraft.server.v1_9_R2.EntityTrackerEntry;
import net.minecraft.server.v1_9_R2.WorldServer;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/*
 * https://gist.github.com/aadnk/3773860
 */

public class TeleportFix implements Listener {

	public TeleportFix(Plugin plugin, Server server) {
	}

	/*@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerTeleport(PlayerTeleportEvent event) {

		final Player player = event.getPlayer();
		final int visibleDistance = server.getViewDistance() * 16;

		// Fix the visibility issue one tick later
		server.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				// Refresh nearby clients
				updateEntities(getPlayersWithin(player, visibleDistance));
			}
		}, TELEPORT_FIX_DELAY);
	}*/

	public void updateEntities(List<Player> observers) {
		// Refresh every single player
		for (Player player : observers) {
			updateEntity(player, observers);
		}
	}

	public void updateEntity(Entity entity, List<Player> observers) {

		World world = entity.getWorld();
		WorldServer worldServer = ((CraftWorld) world).getHandle();

		EntityTracker tracker = worldServer.tracker;
		EntityTrackerEntry entry = (EntityTrackerEntry) tracker.trackedEntities
				.get(entity.getEntityId());

		List<EntityHuman> nmsPlayers = getNmsPlayers(observers);

		// Force Minecraft to resend packets to the affected clients
		entry.trackedPlayers.removeAll(nmsPlayers);
		entry.scanPlayers(nmsPlayers);
	}

	private List<EntityHuman> getNmsPlayers(List<Player> players) {
		List<EntityHuman> nsmPlayers = new ArrayList<EntityHuman>();

		for (Player bukkitPlayer : players) {
			CraftPlayer craftPlayer = (CraftPlayer) bukkitPlayer;
			nsmPlayers.add(craftPlayer.getHandle());
		}

		return nsmPlayers;
	}
}