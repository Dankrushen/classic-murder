package com.gmail.vapidlinus.murder.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import com.gmail.vapidlinus.murder.main.Murder;

public class WeatherChangeListener implements Listener {
	public WeatherChangeListener(Murder plugin) {
	}

	@EventHandler
	public void onWeatherChangeEvent(WeatherChangeEvent event) {
		event.setCancelled(true);
		return;
	}
}
