package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import client.*;
import common.*;

public class ChatClientGUI extends JFrame implements ChatIF{

	
	//Class variables *************************************************
	
	/**
   	* The default port to connect on.
   	*/
	final public static int DEFAULT_PORT = 5555;
  
	  
    ChatClient client;

    private JButton configureButton;
    private JButton sendButton;
    private JButton quitButton;
    private JTextArea messagesArea;
    private JScrollPane scroll;
    private JTextField messageToSend;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // récupérer la taille de l'écran
    private SpringLayout layout;
    private ConfigureChatClientGUI configureFrame = null;
        
    //Constructors ****************************************************
    
    public ChatClientGUI(String nom,String host, int port, String id)
    {
        super(nom);
        initGui();
        addComponents();
        placeComponents();
        addListeners();
        setVisible(true);
        try
        {
            client= new ChatClient(host, port,id,this);
        }
        catch(IOException exception)
        {
            System.out.println("Error: Can't setup connection!"
                    + " Terminating client.");
            System.exit(1);
        }
        configureFrame = new ConfigureChatClientGUI("SimpleChat4 - Configuration", this);
    }

    public void display(String message)
    {
        messagesArea.append(message+"\n");
    }

    private void initGui(){
        setResizable(false);
        setSize(680,400);
        setLocation(screenSize.width/2-this.getSize().width/2, screenSize.height/2-this.getSize().height/2); // mettre au centre de l'écran
        this.layout = new SpringLayout();
        getContentPane().setLayout(layout);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private void addComponents(){
        this.configureButton = new JButton("Configurer");
        this.quitButton = new JButton("Quitter");
        this.sendButton = new JButton("Envoyer");

        quitButton.setPreferredSize(new Dimension(getWidth()/2, 25));
        configureButton.setPreferredSize(new Dimension(getWidth()/2, 25));
        getContentPane().add(configureButton);
        getContentPane().add(quitButton);
        getContentPane().add(sendButton);
        this.messageToSend = new JTextField(48);
        getContentPane().add(messageToSend);
        this.messagesArea = new JTextArea(19,57);
        messagesArea.setEditable(false);
        messagesArea.setLineWrap(true);
        this.scroll=new JScrollPane(messagesArea);
        getContentPane().add(scroll);
    }

    private void addListeners(){
        quitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int option = JOptionPane.showConfirmDialog(
                        null,
                        "Etes vous sûr de vouloir quitter ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    client.handleMessageFromClientUI("#quit");
                }
            }
        });
        configureButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(configureFrame != null){
                    configureFrame.setVisible(true);
                }
            }
        });
        messageToSend.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int key = keyEvent.getKeyCode();
                if(key == 10 && !messageToSend.getText().trim().equals("")){
                    String messageValueField = messageToSend.getText();
                    sendMessage(messageValueField);
                    messageToSend.setText("");
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        null,
                        "Etes vous sûr de vouloir quitter ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    client.handleMessageFromClientUI("#quit");
                }
            }
        });
        sendButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!messageToSend.getText().trim().equals("")){
                	String messageValueField = messageToSend.getText();
                    sendMessage(messageValueField);
                    messageToSend.setText("");
                }
            }
        });
    }
    private void placeComponents(){
        layout.putConstraint(SpringLayout.WEST, configureButton,
                0,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.SOUTH, configureButton,
                configureButton.getHeight(),
                SpringLayout.SOUTH, getContentPane());

        layout.putConstraint(SpringLayout.EAST, quitButton,
                0,
                SpringLayout.EAST, getContentPane());
        layout.putConstraint(SpringLayout.SOUTH, quitButton,
                quitButton.getHeight(),
                SpringLayout.SOUTH, getContentPane());

        layout.putConstraint(SpringLayout.EAST, sendButton,
                0,
                SpringLayout.EAST, getContentPane());
        layout.putConstraint(SpringLayout.SOUTH, sendButton,
                -sendButton.getHeight() - quitButton.getHeight() - 35,
                SpringLayout.SOUTH, getContentPane());

        layout.putConstraint(SpringLayout.WEST, messageToSend,
                10,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.SOUTH, messageToSend,
                -sendButton.getHeight()/2 - quitButton.getHeight() - 38,
                SpringLayout.SOUTH, getContentPane());

        layout.putConstraint(SpringLayout.WEST, messagesArea,
                0,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, messagesArea,
                10,
                SpringLayout.NORTH, getContentPane());
    }
    public void sendMessage(String message){
        client.handleMessageFromClientUI(message);
    }
    public String getId() {
		return client.getId();
    }
	public String getHost() {
		return client.getHost();
	}
	public int getPort() {
		return client.getPort();
	}
	
	public void setId(String id){
		client.setId(id);
	}
	
    public static void main(String[] args){
        String nomApp = "Simple Chat 4";
        String host = "localhost";
        int port = 5555;
        String id = "Anonymous";

        JFrame c = new ChatClientGUI(nomApp,host,port,id);
    }
}
