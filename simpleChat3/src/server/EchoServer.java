package server;// This file contains material supporting section 3.7 of the textbook:// "Object Oriented Software Engineering" and is issued under the open-source// license found at www.lloseng.com import java.io.*;import java.util.Observable;import java.util.Observer;import com.lloseng.ocsf.server.ObservableServer;import common.ChatIF;/** * This class overrides some of the methods in the abstract  * superclass in order to give more functionality to the server. * * @author Dr Timothy C. Lethbridge * @author Dr Robert Lagani&egrave;re * @author Fran&ccedil;ois B&eacute;langer * @author Paul Holden * @version July 2000 */public class EchoServer implements Observer {    private ChatIF serverUI;  private ObservableServer oServer;//Constructors ****************************************************    /**   * Constructs an instance of the echo server.   *   * @param port The port number to connect on.   */  public EchoServer(int port, ChatIF serverUI)   {    this.oServer = new ObservableServer(port);    this.serverUI = serverUI;  }  protected void clientConnected(ObservableServer client) {	  serverUI.display("The client is connected ");  }  protected void clientDisconnected(ObservableServer client) {	  serverUI.display("The client is disconnected ");  }  protected void clientException(ObservableServer client, Throwable exception) {	  serverUI.display("The client is brutally disconnected ");  }  //Instance methods ************************************************    /**   * This method handles any messages received from the client.   *   * @param msg The message received from the client.   * @param client The connection from which the message originated.   */  public void handleMessageFromClient(Object msg, ObservableServer client) {    String[] message = msg.toString().split(" ");    if(message[0].equals("#quit") || message[0].equals("#logoff")){	    	try {			client.close();			serverUI.display("The client : "+client.getInfo("id")+ " has been disconnected.");		} catch (IOException e) {			e.printStackTrace();		}    }else if(message[0].equals("#login")){    	client.setInfo("id", message[1]);    	serverUI.display("A new client is connected as : "+ client.getInfo("id"));    }    else{    	oServer.sendToAllClients(msg);    	serverUI.display("Message received: " + msg + " from " + client.getInfo("id"));    }  }    public void handleMessageFromServerUI(Object msg) {	  String[] message = msg.toString().split(" ");	  switch(message[0]){		case "#quit":			System.exit(0);			break;		case "#stop":			oServer.stopListening();			break;		case "#close":		try {			oServer.stopListening();			oServer.close();		} catch (IOException e) {			e.printStackTrace();		}			break;		case "#setport":			if (!oServer.isListening()){				String port = message[1];				oServer.setPort(Integer.parseInt(port));			}			break;		case "#start":			if (!oServer.isListening()){				try {					oServer.listen();				} catch (IOException e) {					e.printStackTrace();				}			}			break;		case "#getport":			serverUI.display(Integer.toString(oServer.getPort()));			break;		default:			oServer.sendToAllClients("Server MSG> "+msg);		}  }      /**   * This method overrides the one in the superclass.  Called   * when the server starts listening for connections.   */  protected void serverStarted()  {	  serverUI.display("Server listening for connections on port " + oServer.getPort());  }    /**   * This method overrides the one in the superclass.  Called   * when the server stops listening for connections.   */  protected void serverStopped()  {	  serverUI.display("Server has stopped listening for connections.");  }    public void accept()   {    try    {      BufferedReader fromConsole =         new BufferedReader(new InputStreamReader(System.in));      String message;            while (true)       {        message = fromConsole.readLine();        handleMessageFromServerUI(message);      }    }     catch (Exception ex)     {      serverUI.display        ("Unexpected error while reading from console!");    }  }@Override  public void update(Observable o, Object arg) {	o.handleMessageFromClient(arg);	//SWITCH	//Utiliser ObservableOriginatorServer	//Normalement problème résolu pour le connection to client  }}//End of EchoServer class