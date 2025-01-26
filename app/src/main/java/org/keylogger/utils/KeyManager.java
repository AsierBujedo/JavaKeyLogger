package org.keylogger.utils;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class KeyManager implements NativeKeyListener {
	private short hotKeyflag = 0x00;
	private static final short MASK_CTRL = 1 << 0;
	private static final short MASK_ALT = 1 << 1;


	@Override
    public void nativeKeyPressed(NativeKeyEvent e) {

		// If the VK_ESCAPE (escape) key is pressed, tries to unregister the native hook.
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        } else if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
			hotKeyflag |= MASK_CTRL;
		} else if (e.getKeyCode() == NativeKeyEvent.VC_ALT) {
			hotKeyflag |= MASK_ALT;
		} 
		
		if (hotKeyflag == (MASK_CTRL | MASK_ALT)) {
			try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

		if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
			hotKeyflag &= ~MASK_CTRL;
		} else if (e.getKeyCode() == NativeKeyEvent.VC_ALT) {
			hotKeyflag &= ~MASK_ALT;
		}

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	// int id = NativeKeyEvent.NATIVE_KEY_PRESSED;
	// int modifiers = NativeKeyEvent.CTRL_MASK; 
	// int rawCode = 29; 
	// int keyCode = NativeKeyEvent.VC_CONTROL; 
	// char charCode = NativeKeyEvent.CHAR_UNDEFINED;

	public static void postKeyEvent(int id, int modifiers, int rawCode, int keyCode, char charCode) {
		GlobalScreen.postNativeEvent(new NativeKeyEvent(id, modifiers, rawCode, keyCode, charCode));
	}
}