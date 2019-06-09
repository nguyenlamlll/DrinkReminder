package com.letrannguyenlam.services;

import java.awt.*;
import java.net.MalformedURLException;

public class TrayService {
    private String caption;
    private String text;
    private TrayIcon.MessageType messageType;
    private TrayIcon trayIcon;
    private SystemTray tray = SystemTray.getSystemTray();
    public TrayService(String caption, String text, TrayIcon.MessageType messageType) {
        this.caption = caption;
        this.text = text;
        this.messageType = messageType;
    }

    public void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
        //SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        //Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

//        trayIcon = new TrayIcon(image, "Tray Demo");
//        //Let the system resize the image if needed
//        trayIcon.setImageAutoSize(true);
//        //Set tooltip text for the tray icon
//        trayIcon.setToolTip("System tray icon demo");
//        tray.add(trayIcon);

        var icons = tray.getTrayIcons();
        if(icons.length > 0){
            trayIcon = icons[0];
            trayIcon.displayMessage(caption, text, messageType);
        }
    }

    public void removeTrayIcon() {
        //trayIcon.
        tray.remove(trayIcon);
    }

    public void setSystemTray(SystemTray tray) {
        this.tray = tray;
    }
}
