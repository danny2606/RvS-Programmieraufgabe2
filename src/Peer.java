import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Peer {
    BufferedReader eingaben;
    PrintStream ausgaben;
    Socket verbindung;
    WorkerThread workerThread;
    UUID uuid;
    boolean alive;
    boolean handshakeWasMade;

    public Peer(Socket connection){
        verbindung = connection;
        try {
            this.eingaben = new BufferedReader(new InputStreamReader(this.verbindung.getInputStream(), StandardCharsets.UTF_8), 16384);
            this.ausgaben = new PrintStream(connection.getOutputStream(), true, StandardCharsets.UTF_8);
        }catch(IOException e){
            System.err.println("Error at initializing a new peer");
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void sendHello(){
        String command = "HELLO --uuid "+Main.myUuid+" --port "+Main.serverPort;
        if(Main.name!=null){
            command = command.concat("--name "+Main.name);
        }
        byte[] commandBytes = command.getBytes(StandardCharsets.UTF_8);
        try {
            ausgaben.write(commandBytes);
        }catch(IOException e){
            System.err.println("Error at sending data to "+verbindung.getInetAddress());
        }
    }
}
