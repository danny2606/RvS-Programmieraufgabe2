import java.io.IOException;

public class WorkerThread extends Thread{
    Peer peer;
    public WorkerThread(Peer p) {
        peer = p;
        peer.workerThread = this;
    }
    public void run(){
        if(!peer.handshakeWasMade){
            peer.sendHello();
            while(true){
                try {
                    if (!peer.eingaben.ready()) break;
                } catch (IOException e) {
                    System.err.println("Error while waiting for HI from "+peer.verbindung.getInetAddress());
                    System.exit(1);
                }
            }

        }
    }
}
