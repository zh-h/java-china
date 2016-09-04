package org.javachina;

import static com.blade.Blade.$;

import com.blade.plugin.AopPlugin;

public class Application {
	
	public static void main(String[] args) throws Exception {
		new AopPlugin().startup();
		$().start(Application.class);
	}
}
