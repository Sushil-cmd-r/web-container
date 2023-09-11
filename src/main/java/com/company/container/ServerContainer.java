package com.company.container;

import com.company.config.ConfigManager;
import com.company.config.ConfigType;
import com.company.config.Configuration;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerContainer {
    private int port;
    private final ConfigManager configManager;

    public ServerContainer(ConfigType type) {
        this.configManager = ConfigManager.getInstance(type);
    }

    private void start() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Configuration config = configManager.loadConfig();
        this.port = config.getPort();
        System.out.println(config);
        try(ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress(port));

            while (serverSocket.isOpen()) {
                SocketChannel conn = serverSocket.accept();
                executor.submit(new RequestHandler(conn));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public static void main(String[] args) {
        new ServerContainer(ConfigType.XML).start();
    }
}
