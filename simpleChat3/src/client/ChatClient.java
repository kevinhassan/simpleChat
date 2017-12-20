// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import common.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

import com.lloseng.ocsf.client.ObservableClient;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient implements Observer
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  String id;
  ObservableClient oClient;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port,String id, ChatIF clientUI) 
    throws IOException 
  {
    this.clientUI = clientUI;
    this.id = id;
    this.oClient = new ObservableClient (host,port); //Construit l'observable client
		oClient.addObserver(this);
	}

  
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
    if(msg.equals("#close")){
			clientUI.display("Server disconnected nicely");
			try {
				observableClient.closeConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			clientUI.display(msg.toString());
		}
  }

  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(String message)
  {
	try
    {
		String[] msg = message.split(" ");
		// Vérifier que le premier caractère contient un #
		if (msg[0].startsWith("#")) {
			switch(msg[0]){
			case "#quit":
				//notifier le serveur puis faire appel à closeConnection
				if (oClient.isConnected()){
					try
				    {
						oClient.sendToServer(message);
						oClient.closeConnection();
				    }
				    catch(IOException e) {}
				}
				quit();
				break;
			case "#logoff":
				if (oClient.isConnected()){
					oClient.sendToServer(message);
					oClient.closeConnection();
				}
				else{
					clientUI.display("You are not connected to any server !");
				}
				break;
			case "#sethost":
				if (!oClient.isConnected()){
					String host = msg[1];
					oClient.setHost(host);
				}
				break;
			case "#setport":
				if (!oClient.isConnected()){
					String port = msg[1];
					oClient.setPort(Integer.parseInt(port));
				}
				break;
			case "#login":
				if (!oClient.isConnected()){
					oClient.openConnection();
					if(msg.length>1){
						oClient.sendToServer(message);
					}else{
						oClient.sendToServer("#login "+ id);
					}
				}
				break;
			case "#gethost":
				if (!oClient.isConnected()){
					clientUI.display(oClient.getHost());
				}
				break;
			case "#getport":
				if (!oClient.isConnected()){
					clientUI.display(Integer.toString(oClient.getPort()));
				}
				break;
			}      
		} else {
			// Si le message ne commence pas par un # 
			oClient.sendToServer(message);
		}
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    System.exit(0);
	}
	
	protected void connectionEstablished(){
		clientUI.display("Connection established !");
	}
  
  public void connectionClosed(){
	 clientUI.display("Connection closed");
  }
  
  public void connectionException(Exception exception){
	  clientUI.display(exception.getMessage());
  }

  public void update(Observable o, Object arg) {
	  switch(arg[0]){
	  //Faire un switch sur les différents cas que l'on reçoit : CONNECTION_EXCT, etc
			case "#OC:Connection established.":
				
				break;
			case "#OC:Connection closed.":
				
				break;
			case "exception":

				break;
			default:
				//Par défaut faire handleMessageFromServer
  			handleMessageFromServer(arg);
			}      
	  
  }
}
//End of ChatClient class
