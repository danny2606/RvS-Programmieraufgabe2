import java.io.IOException;
import java.net.Socket;

public class AcceptThread extends Thread{
    public void run(){
        while(!Main.serverSocket.isClosed()){
            try {
                Socket peerConnection = Main.serverSocket.accept();
                Peer peer = new Peer(peerConnection);
                new WorkerThread(peer);
            }catch(IOException e){
                System.err.println("Error at accepting a connection");
            }
        }
    }
}
