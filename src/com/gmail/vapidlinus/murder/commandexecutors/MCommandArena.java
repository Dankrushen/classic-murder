package com.gmail.vapidlinus.murder.commandexecutors;

import com.gmail.vapidlinus.murder.main.Match;
import com.gmail.vapidlinus.murder.main.Murder;
import com.gmail.vapidlinus.murder.tools.ChatContext;
import com.gmail.vapidlinus.murder.tools.CustomYaml;
import com.gmail.vapidlinus.murder.tools.Tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MCommandArena extends MCommand {
	public void execute(CommandSender sender, String[] args, Murder plugin) {
		if (args.length < 1) {
			sender.sendMessage(ChatContext.ERROR_NOTENOUGHARGUMENTS);
			return;
		}
		if ((sender instanceof Player)) {
			Player player = (Player) sender;
			String[] newArgs = new String[args.length - 1];
			for (int i = 0; i < newArgs.length; i++) {
				newArgs[i] = args[(i + 1)];
			}
			if (args[0].equalsIgnoreCase("addspawn")) {
				addSpawn(player, newArgs, plugin);
			} else if (args[0].equalsIgnoreCase("removespawn")) {
				removeSpawn(player, newArgs, plugin);
			} else if (args[0].equalsIgnoreCase("gotospawn")) {
				gotoSpawn(player, newArgs, plugin);
			} else if (args[0].equalsIgnoreCase("listspawns")) {
				listSpawns(player, newArgs, plugin);
			} else if (args[0].equalsIgnoreCase("addpartspawn")) {
				addPartSpawn(player, newArgs, plugin);
			} else if (args[0].equalsIgnoreCase("addarena")) {
				addArena(player, newArgs, plugin);
			} else if (args[0].equalsIgnoreCase("removearena")) {
				removeArena(player, newArgs, plugin);
			} else if (args[0].equalsIgnoreCase("emptyarena")) {
				emptyArena(player, newArgs, plugin);
			}
		}
	}

	void addSpawn(Player player, String[] args, Murder plugin) {
		if (args.length > 1) {
			player.sendMessage(ChatContext.ERROR_TOOMANYARGUMENTS);
			return;
		}
		if (args.length < 1) {
			player.sendMessage(ChatContext.ERROR_NOTENOUGHARGUMENTS);
			return;
		}
		try {
			YamlConfiguration config = CustomYaml.loadConfig("plugins/Murder/Arenas/" + args[0] + ".yml");
			if (config == null) {
				player.sendMessage(ChatContext.ERROR_ARENANOTFOUND);
				return;
			}
			List<?> list = config.getList("spawns");
			List<String> spawns = new ArrayList<String>();
			if (list != null) {
				for (Object object : list) {
					spawns.add(object.toString());
				}
			}
			String locString = Tools.locationToString(player.getLocation());
			spawns.add(locString);
			player.sendMessage(ChatContext.PREFIX_PLUGIN + "Spawn added at: " + ChatContext.COLOR_HIGHLIGHT + locString);

			config.set("spawns", spawns);
			CustomYaml.saveConfig(config, "plugins/Murder/Arenas/" + args[0] + ".yml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void removeSpawn(Player player, String[] args, Murder plugin) {
		if (args.length > 2) {
			player.sendMessage(ChatContext.ERROR_TOOMANYARGUMENTS);
			return;
		}
		if (args.length < 2) {
			player.sendMessage(ChatContext.ERROR_NOTENOUGHARGUMENTS);
			return;
		}
		try {
			YamlConfiguration config = CustomYaml.loadConfig("plugins/Murder/Arenas/" + args[0] + ".yml");
			if (config == null) {
				player.sendMessage(ChatContext.ERROR_ARENANOTFOUND);
				return;
			}
			List<?> list = config.getList("spawns");
			List<String> spawns = new ArrayList<String>();
			if (list != null) {
				for (Object object : list) {
					spawns.add(object.toString());
				}
			}
			int id = Integer.parseInt(args[1]);
			if (id < spawns.size()) {
				spawns.remove(id);
			}
			player.sendMessage(ChatContext.PREFIX_PLUGIN + "Spawn removed!");

			config.set("spawns", spawns);
			CustomYaml.saveConfig(config, "plugins/Murder/Arenas/" + args[0]
					+ ".yml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void gotoSpawn(Player player, String[] args, Murder plugin) {
		if (args.length > 2) {
			player.sendMessage(ChatContext.ERROR_TOOMANYARGUMENTS);
			return;
		}
		if (args.length < 2) {
			player.sendMessage(ChatContext.ERROR_NOTENOUGHARGUMENTS);
			return;
		}
		try {
			YamlConfiguration config = CustomYaml.loadConfig("plugins/Murder/Arenas/" + args[0] + ".yml");
			if (config == null) {
				player.sendMessage(ChatContext.ERROR_ARENANOTFOUND);
				return;
			}
			List<?> list = config.getList("spawns");
			List<String> spawns = new ArrayList<String>();
			if (list != null) {
				for (Object object : list) {
					spawns.add(object.toString());
				}
			}
			int id = Integer.parseInt(args[1]);
			Location location = Tools.stringToLocation((String) spawns.get(id), plugin.getServer());
			player.teleport(location);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void listSpawns(Player player, String[] args, Murder plugin) {
		if (args.length > 1) {
			player.sendMessage(ChatContext.ERROR_TOOMANYARGUMENTS);
			return;
		}
		try {
			YamlConfiguration config = CustomYaml.loadConfig("plugins/Murder/Arenas/" + args[0] + ".yml");
			if (config == null) {
				player.sendMessage(ChatContext.ERROR_ARENANOTFOUND);
				return;
			}
			List<?> list = config.getList("spawns");
			List<String> spawns = new ArrayList<String>();
			if (list != null) {
				for (Object object : list) {
					spawns.add(object.toString());
				}
			}
			player.sendMessage(ChatContext.PREFIX_PLUGIN + "Spawns: " + ChatContext.COLOR_HIGHLIGHT + spawns.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void addPartSpawn(Player player, String[] args, Murder plugin) {
		if (args.length > 1) {
			player.sendMessage(ChatContext.ERROR_TOOMANYARGUMENTS);
			return;
		}
		if (args.length < 1) {
			player.sendMessage(ChatContext.ERROR_NOTENOUGHARGUMENTS);
			return;
		}
		try {
			YamlConfiguration config = CustomYaml.loadConfig("plugins/Murder/Arenas/" + args[0] + ".yml");
			if (config == null) {
				player.sendMessage(ChatContext.ERROR_ARENANOTFOUND);
				return;
			}
			List<?> list = config.getList("partspawns");
			List<String> spawns = new ArrayList<String>();
			if (list != null) {
				for (Object object : list) {
					spawns.add(object.toString());
				}
			}
			String locString = Tools.locationToString(player.getLocation());
			spawns.add(locString);
			player.sendMessage(ChatContext.PREFIX_PLUGIN
					+ "Part spawn added at: " + ChatContext.COLOR_HIGHLIGHT
					+ locString);

			config.set("partspawns", spawns);
			CustomYaml.saveConfig(config, "plugins/Murder/Arenas/" + args[0] + ".yml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void addArena(Player player, String[] args, Murder plugin) {
		if (args.length > 1) {
			player.sendMessage(ChatContext.ERROR_TOOMANYARGUMENTS);
			return;
		}
		if (args.length < 1) {
			player.sendMessage(ChatContext.ERROR_NOTENOUGHARGUMENTS);
			return;
		}
		File f = new File("plugins/Murder/Arenas/" + args[0] + ".yml");
		if(f.exists()) {
			player.sendMessage(ChatContext.ERROR_ARENAEXISTS);
		} else {
			Match m = plugin.getMatch();
			if(m.isStarted()) {
				Tools.sendMessageAll(plugin.getServer(), ChatContext.PREFIX_PLUGIN + "Match was forced to end.");
				m.endMatch();
			}
			if(!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			player.sendMessage(ChatContext.PREFIX_PLUGIN
					+ "Created arena.");
		}
	}
	
	void removeArena(Player player, String[] args, Murder plugin) {
		if (args.length > 1) {
			player.sendMessage(ChatContext.ERROR_TOOMANYARGUMENTS);
			return;
		}
		if (args.length < 1) {
			player.sendMessage(ChatContext.ERROR_NOTENOUGHARGUMENTS);
			return;
		}
		File f = new File("plugins/Murder/Arenas/" + args[0] + ".yml");
		if(!f.exists()) {
			player.sendMessage(ChatContext.ERROR_ARENANOTFOUND);
		} else {
			Match m = plugin.getMatch();
			if(m.isStarted()) {
				Tools.sendMessageAll(plugin.getServer(), ChatContext.PREFIX_PLUGIN + "Match was forced to end.");
				m.endMatch();
			}
			f.delete();
			player.sendMessage(ChatContext.PREFIX_PLUGIN
					+ "Removed arena.");
		}
	}
	
	void emptyArena(Player player, String[] args, Murder plugin) {
		if (args.length > 1) {
			player.sendMessage(ChatContext.ERROR_TOOMANYARGUMENTS);
			return;
		}
		if (args.length < 1) {
			player.sendMessage(ChatContext.ERROR_NOTENOUGHARGUMENTS);
			return;
		}
		File f = new File("plugins/Murder/Arenas/" + args[0] + ".yml");
		if(!f.exists()) {
			player.sendMessage(ChatContext.ERROR_ARENANOTFOUND);
		} else {
			Match m = plugin.getMatch();
			if(m.isStarted()) {
				Tools.sendMessageAll(plugin.getServer(), ChatContext.PREFIX_PLUGIN + "Match was forced to end.");
				m.endMatch();
			}
			f.delete();
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			player.sendMessage(ChatContext.PREFIX_PLUGIN
					+ "Emptied arena.");
		}
	} 
}