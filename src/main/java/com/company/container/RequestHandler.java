package com.company.container;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RequestHandler implements Runnable {
    private final SocketChannel conn;

    public RequestHandler(SocketChannel conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        try(BufferedReader reader = new BufferedReader(Channels.newReader(conn, UTF_8));
            PrintWriter writer = new PrintWriter(Channels.newWriter(conn, UTF_8))
        ){
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

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
