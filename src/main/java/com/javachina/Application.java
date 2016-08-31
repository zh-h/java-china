package com.javachina;

import static com.blade.Blade.$;

import com.blade.embedd.EmbedJettyServer;
import com.blade.plugin.AopPlugin;

public class Application {
	
	public static void main(String[] args) throws Exception {
		new AopPlugin().start();
		$().start(EmbedJettyServer.class);
	}
}
