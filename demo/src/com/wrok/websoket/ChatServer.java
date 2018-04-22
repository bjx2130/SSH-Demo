package com.wrok.websoket;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;

/**
 * Created by ocean on 2016-09-28.
 */
//@ServerEndpoint(value = "/websocket/chat")
public class ChatServer extends WebSocketServer {

    public ChatServer( int port ) throws UnknownHostException {
        super( new InetSocketAddress( port ) );
    }

    public ChatServer( InetSocketAddress address ) {
        super( address );
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {
        this.sendToAll(conn, "onOpen 进入房间!---->  新连接: " + handshake.getResourceDescriptor() );
        System.out.println("onOpen 进入房间!--->"+ conn.getRemoteSocketAddress().getAddress().getHostAddress()  );
    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
        this.sendToAll(conn,"onClose 离开房间!--->"+conn + " has left the room!" );
        System.out.println("onClose 离开房间!--->"+ conn + " has left the room!" );
    }

    @Override
    public void onMessage( WebSocket conn, String message ) {
        //this.sendToAll( message );
        System.out.println("onMessage 接收消息---->"+ conn.getRemoteSocketAddress().getAddress().toString() + ": " + message );
    }



    public static void main( String[] args ) throws InterruptedException , IOException {
        WebSocketImpl.DEBUG = true;
        int port = 8887; // 843 flash policy port
        try {
            port = Integer.parseInt( args[ 0 ] );
        } catch ( Exception ex ) {
        }
        ChatServer s = new ChatServer( port );
        s.start();
        System.out.println( "ChatServer started on port: " + s.getPort() );

        BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
        while ( true ) {
            String in = sysin.readLine();
            s.sendToAll( null,in );
            if( in.equals( "exit" ) ) {
                s.stop();
                break;
            } else if( in.equals( "restart" ) ) {
                s.stop();
                s.start();
                break;
            }
        }
    }
    @Override
    public void onError( WebSocket conn, Exception ex ) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    /**
     * Sends <var>text</var> to all currently connected WebSocket clients.
     *
     * @param text
     *            The String to send across the network.
     * @throws InterruptedException
     *             When socket related I/O errors occur.
     */
    public void sendToAll(WebSocket client, String text ) {

        Collection<WebSocket> con = super.connections();

        synchronized ( con ) {
            for( WebSocket c : con ) {
                if (client==c){
                    System.out.print("***************************");
                    c.send( text );

                }
            }
        }
    }
}
