import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

class ListFrame extends JFrame implements Observer { 
   JList list; 
   JPanel p; 
   JScrollPane lsp; 
   JListData listData; 
   public ListFrame(Subject s)    { 
    super("Color List"); 
    //put panel into the frmae 
    p = new JPanel(true); 
    getContentPane().add("Center",  p); 
    p.setLayout(new BorderLayout()); 
    //Tell the Subject we are interested 
    s.registerInterest(this); 
    //Create the list 
    listData = new JListData();  //the list model 
    list = new JList(listData);  //the visual list 
    lsp = new JScrollPane();     //the scroller 
    lsp.getViewport().add(list); 
    p.add("Center", lsp); 
    lsp.setPreferredSize(new Dimension(100,100)); 
    setBounds(250, 100, 100, 100); 
    setVisible(true); 
   } 
   //-------------------------------- 
   public void sendNotify(String s)    { 
      listData.addElement(s);
      
   }
}