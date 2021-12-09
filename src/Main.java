
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main {
    static int serverPort = 11113;
    static String name;
    static ServerSocket serverSocket;
    static UUID myUuid;

    public static void main(String[] args){
        List<String> parameters = Arrays.asList(args);
        String host;
        int destPort;
        myUuid = UUID.randomUUID();
        for(String parameter : parameters){
            if(parameter.equals("--port")){
                serverPort = Integer.parseInt(parameters.get(parameters.indexOf(parameter)+1));
            }
            if(parameter.equals("--name")){
                name = parameters.get(parameters.indexOf(parameter)+1);
            }
            if(parameter.equals("--connect")){
                String connectPara = parameters.get(parameters.indexOf(parameter)+1);
                try {
                    host = connectPara.substring(0, connectPara.indexOf(":"));
                    destPort = Integer.parseInt(connectPara.substring(connectPara.indexOf(":") + 1));
                } catch (IndexOutOfBoundsException e) {
                    host = connectPara;
                    destPort = 11113;
                }
                new ConnectThread(host, destPort).start();
            }
        }
    }
}
