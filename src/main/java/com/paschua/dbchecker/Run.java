package com.paschua.dbchecker;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class Run {

    private static final Logger LOG = Logger.getLogger(Run.class.getSimpleName());

    public static void main(String[] args) {
        try {
            String strFile = new String();
            if (args.length == 0) {
                strFile = "";
            } else {
                strFile = args[0];
            }

            Properties properties = PropLoader.loadPropertiesFile(strFile);

            boolean isConnected = false;
            for (int i = 1; i < 21; i++) {
                LOG.log(Level.INFO, "Trying to connect...[" + i + "]");
                isConnected = Connector.isConnected(properties);
                if (isConnected) {
                    LOG.log(Level.INFO, "Connected!");
                    break;
                }
                wait(15000);
            }
            if (!isConnected) {
                LOG.log(Level.WARNING, "Database not found!");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void wait(int mills) {
        try {
            LOG.log(Level.INFO, "Waiting " + mills + " milliseconds before retry...\n");
            Thread.sleep(mills);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
