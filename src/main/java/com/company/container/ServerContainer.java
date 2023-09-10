package com.company.container;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ServerContainer {
    private final int port;

    public ServerContainer(int port) {
        this.port = port;
    }

    private void start() {
        try(ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress(port));

            SocketChannel conn = serverSocket.accept();
            BufferedReader reader = new BufferedReader(Channels.newReader(conn, UTF_8));
            PrintWriter writer = new PrintWriter(Channels.newWriter(conn, UTF_8));

            String line = reader.readLine();
            while (!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
            }

            writer.println("""
                    HTTP/1.1 200 OK
                    Content-Type: text/html
                    
                    <html>
                        <body>
                            <h1>Hello World</h1>
                        </body>
                    </html>
                    """);
            writer.flush();

            reader.close();
            writer.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServerContainer(8888).start();
    }
}
