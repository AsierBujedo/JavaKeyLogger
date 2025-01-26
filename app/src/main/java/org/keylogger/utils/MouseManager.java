package org.keylogger.utils;

import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.GlobalScreen;

public class MouseManager implements NativeMouseInputListener {
    private int currentX = 0;
    private int currentY = 0;

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        System.out.println("Mouse Clicked: " + e.getButton());
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {
        this.currentX = e.getX();
        this.currentY = e.getY();
        System.out.println("Mouse coordinates updated: " + this.currentX + ", " + this.currentY);
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {
        System.out.println("Mouse Released: " + e.getButton());
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {
        this.currentX = e.getX();
        this.currentY = e.getY();

        System.out.println("Mouse coordinates updated: " + this.currentX + ", " + this.currentY);

    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {
        System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
    }

    // int id = NativeMouseEvent.NATIVE_MOUSE_CLICKED; 
    // int modifiers = NativeInputEvent.BUTTON1_MASK; 
    // int x = 100; 
    // int y = 200; 
    // int clickCount = 1; 

    public static void postMouseEvent(int id, int modifiers, int x, int y, int clickCount) {
        GlobalScreen.postNativeEvent(new NativeMouseEvent(id, modifiers, x, y, clickCount));
    }

    public void postMouseEvent(int id, int modifiers, int clickCount) {
        GlobalScreen.postNativeEvent(new NativeMouseEvent(id, modifiers, this.currentX, this.currentY, clickCount));
    }
}
