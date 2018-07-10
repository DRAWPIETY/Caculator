import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.*;

class Action  implements ActionListener{
	private Caculator cac;
	
	public Action(Caculator c){
		cac = c;
	}
	
	private String compute(String input)//��1237 �� ����  
    {  
        String str[];  
        str = input.split(" ");  
        Stack<Double> s = new Stack<Double>();  
        double m = Double.parseDouble(str[0]);  
        s.push(m);  
        for(int i=1;i<str.length;i++)  
        {  
            if(i%2==1)    
            {    
                if(str[i].compareTo("+")==0)    
                {    
                    double help = Double.parseDouble(str[i+1]);    
                    s.push(help);    
                }    
                    
                if(str[i].compareTo("-")==0)    
                {    
                    double help = Double.parseDouble(str[i+1]);    
                    s.push(-help);    
                }    
                    
                if(str[i].compareTo("*")==0)    
                {    
                    double help = Double.parseDouble(str[i+1]);    
                    double ans = s.peek();//ȡ��ջ��Ԫ��    
                    s.pop();//��ջ    
                    ans*=help;    
                    s.push(ans);    
                }    
                    
                if(str[i].compareTo("/")==0)    
                {    
                    double help = Double.parseDouble(str[i+1]);    
                    double ans = s.peek();    
                    s.pop();    
                    ans/=help;    
                    s.push(ans);    
                }    
            }    
        }    
        double ans = 0.0;    
        while(!s.isEmpty())    
        {    
            ans+=s.peek();    
            s.pop();    
        }    
        String result = String.valueOf(ans);  
        return result;  
    }  
	
	
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		// TODO Auto-generated method stub
		if(actionCommand.equals("C"))
		{
			cac.wri.setText("");
		}
		else if(actionCommand.equals("+")||actionCommand.equals("-")||actionCommand.equals("*") ||actionCommand.equals("/"))  
			cac.wri.append(" "+actionCommand+" ");
	    else if(actionCommand.equals("Inf"))
			cac.wri.append("���ݴ�ѧ2016���ƿ�2��  ����");
	    else if(actionCommand.equals("="))//���������Ⱥ�ʱ������ input  
	    {  
	    	String str1;
	    	String str2= "";
	    	try
	    	{
	    		str1 = cac.wri.getText();
	    		str2 = compute(str1);
	    	}
	    	catch(Exception exc) {
	    		cac.wri.append("\n�밴C���");
	    		actionCommand = "";
	    	}
	    	cac.wri.append(actionCommand+str2);
	    }  
	    else  
	    	cac.wri.append(actionCommand);//����Ϊ�˱����λ�������� ����Ҫ�ӿո�  
	}
	
}


public class Caculator extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextArea wri = new JTextArea();
	
	
	public Caculator()
	{
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(5,4));	// ��������񲼾�
		String symbol[]={"Inf","00","000","C","7","8","9","+","4","5","6","-","1","2","3","*","0",".","=","/"};
		
		Action act = new Action(this);
		
		JButton b[] = new JButton[20];
		for(int i = 0; i < symbol.length;i++){
			b[i]= new JButton(symbol[i]);
			b[i].addActionListener(act);
			p1.add(b[i]);
			if(symbol[i]=="Inf" 
					|| symbol[i]=="00" 
					|| symbol[i]=="000" 
					|| symbol[i]=="C" 
					|| symbol[i]=="+" 
					|| symbol[i]=="-"
					||symbol[i]=="*"
					||symbol[i]=="=" 
					|| symbol[i]=="/" )
			{
				b[i].setForeground(Color.RED);
				b[i].setFont(new Font("����", Font.PLAIN, 14));
			}
			
		}
		
		add(wri,BorderLayout.NORTH);					//��������еĲ���
		add(p1);
	}
	
	public static void main(String []args){
		Caculator f = new Caculator();
		f.setTitle("Calutor");
		f.setBounds(750,300,240,290);					//���ô��ڵ�λ�úʹ�С
		f.setVisible(true);
	}
	
}

