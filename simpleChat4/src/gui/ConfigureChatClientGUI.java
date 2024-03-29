package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConfigureChatClientGUI extends JFrame{
    private JTextField hostField;
    private JLabel hostLabel;
    private JTextField portField;
    private JLabel portLabel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JButton loginButton;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // récupérer la taille de l'écran
    private SpringLayout layout;
    
    private ChatClientGUI parent;

    public ConfigureChatClientGUI(String nom, ChatClientGUI parent) {
        super(nom);
        this.parent = parent;
        initGui();
        addComponents();
        addListeners();
        placeComponents();
        setVisible(false);
    }

    private void initGui(){
        setSize((int)(screenSize.getWidth()/4),(int)(screenSize.getHeight()/4));
        setLocation(screenSize.width/2-this.getSize().width/2, screenSize.height/2-this.getSize().height/2); // mettre au centre de l'écran
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.layout = new SpringLayout();
        getContentPane().setLayout(layout);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    private void addComponents(){
        this.hostField = new JTextField(15);
        this.hostLabel = new JLabel("Hôte :");
        this.portField = new JTextField(15);
        this.portLabel = new JLabel("Port : ");
        this.nameField = new JTextField(15);
        this.nameLabel = new JLabel("Nom : ");
        this.loginButton = new JButton("Se connecter");

        getContentPane().add(hostLabel);
        getContentPane().add(hostField);
        getContentPane().add(portField);
        getContentPane().add(portLabel);
        getContentPane().add(nameField);
        getContentPane().add(nameLabel);

        hostField.setText(parent.getHost());
        portField.setText(Integer.toString(parent.getPort()));
        nameField.setText(parent.getId());

        loginButton.setPreferredSize(new Dimension(getWidth(), 25));
        getContentPane().add(loginButton);
    }

    private void addListeners(){
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String hostValueField = hostField.getText();
                String portValueField = portField.getText();
                String nameValueField = nameField.getText();
                hostField.setText(parent.getHost());
                portField.setText(Integer.toString(parent.getPort()));
                nameField.setText(parent.getId());
                if(!(hostValueField.equals("") || portValueField.equals("") || nameValueField.equals(""))){
                	parent.sendMessage("#sethost "+ hostValueField);
                	parent.sendMessage("#setport "+ portValueField);
                	parent.sendMessage("#login "+ nameValueField);
                	parent.setId(nameValueField);
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null,
                            "Attention.",
                            "Ne laissez pas de champ vide",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

    }
    private void placeComponents(){
        layout.putConstraint(SpringLayout.WEST, hostLabel,
                50,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, hostLabel,
                getHeight()/4,
                SpringLayout.NORTH, getContentPane());
        layout.putConstraint(SpringLayout.WEST, hostField,
                hostLabel.getWidth()+50 + 80,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, hostField,
                getHeight()/4,
                SpringLayout.NORTH, getContentPane());

        layout.putConstraint(SpringLayout.WEST, portLabel,
                50,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, portLabel,
                getHeight()/4 + 25,
                SpringLayout.NORTH, getContentPane());
        layout.putConstraint(SpringLayout.WEST, portField,
                hostLabel.getWidth()+50 + 80,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, portField,
                getHeight()/4 + 25,
                SpringLayout.NORTH, getContentPane());

        layout.putConstraint(SpringLayout.WEST, nameLabel,
                50,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, nameLabel,
                getHeight()/4 + 50,
                SpringLayout.NORTH, getContentPane());
        layout.putConstraint(SpringLayout.WEST, nameField,
                portLabel.getWidth()+50 + 80,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.NORTH, nameField,
                getHeight()/4 + 50,
                SpringLayout.NORTH, getContentPane());

        layout.putConstraint(SpringLayout.WEST, loginButton,
                0,
                SpringLayout.WEST, getContentPane());
        layout.putConstraint(SpringLayout.SOUTH, loginButton,
                loginButton.getHeight(),
                SpringLayout.SOUTH, getContentPane());
    }
}
