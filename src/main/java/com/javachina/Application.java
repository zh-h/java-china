package com.javachina;

import static com.blade.Blade.$;

import com.blade.embedd.EmbedJettyServer;

public class Application {
	
	public static void main(String[] args) throws Exception {
		$().start(EmbedJettyServer.class);
	}
}
