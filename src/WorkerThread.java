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
            //wait for HI
            while(true){
                try {
                    if (!peer.eingaben.ready()) break;
                } catch (IOException e) {
                    System.err.println("Error while waiting for HI from "+peer.verbindung.getInetAddress());
                    System.exit(1);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //read HI
            String answer;
            try {
                answer = peer.eingaben.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
