package gui;

import sun.security.krb5.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClientGUI extends JFrame{
    private JButton configureButton;
    private JButton sendButton;
    private JButton quitButton;
    private JTextArea messagesArea;
    private JScrollPane scroll;
    private JTextField messageToSend;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // récupérer la taille de l'écran
    private SpringLayout layout;
    private ConfigureChatClientGUI configureFrame = null;

    public ChatClientGUI(String nom)
    {
        super(nom);
        initGui();
        addComponents();
        placeComponents();
        addListeners();
        setVisible(true);
    }

    private void initGui(){
        setResizable(false);
        setSize((int)(screenSize.getWidth()/2),(int)(screenSize.getHeight()/2));
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
        this.messagesArea = new JTextArea(19,55);
        messagesArea.setEditable(false);
        this.scroll=new JScrollPane(messagesArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
                    System.out.println("quit");
                }
            }
        });
        configureButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(configureFrame == null){
                    configureFrame = new ConfigureChatClientGUI("SimpleChat4 - Configuration");
                }else{
                    configureFrame.setVisible(true);
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
                    System.out.println("quit");
                }
            }
        });
        sendButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String messageValueField = messageToSend.getText();
                // effacer le texte
                messageToSend.setText("");
                messagesArea.append(messageValueField+"\n");
                System.out.println(messageValueField);
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
                10,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, messagesArea,
                10,
                SpringLayout.NORTH, getContentPane());
    }

    public static void main(String[] args){
        String nomApp = "Simple Chat 4";
        JFrame c = new ChatClientGUI(nomApp);

    }
}
