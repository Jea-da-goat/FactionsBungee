package com.itndev.factions.SocketConnection;

import com.itndev.factions.SocketConnection.Client.NettyClient;
import com.itndev.factions.SocketConnection.Client.Old.Client;
import com.itndev.factions.SocketConnection.IO.ResponceList;

public class Main {

    public static void launch() throws Exception {
        //Client client = new Client(Socket.Address, Socket.Port);
        NettyClient client = new NettyClient(Socket.Address, Socket.Port);
        client.run();
    }

    public static void disconnect() {
        NettyClient.getConnection().disconnect();
        //ResponceList.get().disconnect();
    }
}
