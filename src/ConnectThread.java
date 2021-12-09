import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectThread extends Thread{
    String host;
    int port = 11113;
    Peer peer;
    public ConnectThread(String h, int p){
        host = h;
        port = p;
    }
    public ConnectThread(String h){
        host = h;
    }
    public void run(){
        Socket otherSocket;
        try{
            otherSocket = new Socket(host, port);
        }catch(IOException e){
            if(e.getClass().equals(IOException.class)) {
                System.err.println("Unknown host: " + host);
            }else{
                System.err.println("Error at creating the socket for "+host);
            }
            return;
        }
        Peer peer = new Peer(otherSocket);
        WorkerThread workerThread = new WorkerThread(peer);
        workerThread.start();
    }
}
