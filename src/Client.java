import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 8080);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        System.out.println("1er argument de l'operation ?");
        String arg1=inFromUser.readLine();
        System.out.println("2eme argument de l'operation ?");
        String arg2=inFromUser.readLine();
        System.out.println("Quels protocoles souhaitez-vous utiliser ?");
        System.out.println("1: SourceColl");
        System.out.println("2: ByteColl");
        System.out.println("3: ObjectColl");
        String choix=inFromUser.readLine();
        switch (choix){
            case "1": SourceColl(arg1,arg2,clientSocket,outToServer,inFromServer);
                break;
            case "2": ByteColl(arg1,arg2,clientSocket,outToServer,inFromServer);
                break;
            case "3": ObjectColl(arg1,arg2,clientSocket,outToServer,inFromServer);
                break;
        }
        clientSocket.close();

    }

    public static void SourceColl(String arg1, String arg2, Socket clientSocket, DataOutputStream outToServer, BufferedReader inFromServer) throws IOException {
        ByteStream BStream=new ByteStream();
        BStream.toStream(outToServer,1);
        String reponse = inFromServer.readLine();
        System.out.println(reponse);
        File toCompile= new File("./src/Calc.java");
        //BStream.toStream(outToServer, (int)toCompile.length());
        BStream.toStream(outToServer,toCompile);
    }

    public static void ByteColl(String arg1, String arg2, Socket clientSocket, DataOutputStream outToServer, BufferedReader InFromServer) throws IOException {
        ByteStream BStream=new ByteStream();
        BStream.toStream(outToServer,2);
    }

    public static void ObjectColl(String arg1, String arg2, Socket clientSocket, DataOutputStream outToServer, BufferedReader InFromServer) throws IOException {
        ByteStream BStream=new ByteStream();
        BStream.toStream(outToServer,2);
    }
}
