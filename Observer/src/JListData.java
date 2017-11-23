import java.util.Vector;

import javax.swing.AbstractListModel;

class JListData extends AbstractListModel { 
   private Vector data; //the color name list 
   public JListData()    { 
      data = new Vector(); 
   } 
   public int getSize()    { 
      return data.size(); 
   } 
   public Object getElementAt(int index)    { 
      return data.elementAt(index); 
   } 
  //add string to list and tell the list about it 
   public void addElement(String s)    { 
      data.addElement(s); 
      fireIntervalAdded(this, data.size()-1, data.size()); 
   } 
} 
