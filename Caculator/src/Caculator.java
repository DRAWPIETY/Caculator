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
	
	private String compute(String input)//即1237 的 样例  
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
                    double ans = s.peek();//取出栈顶元素    
                    s.pop();//消栈    
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
			cac.wri.append("苏州大学2016级计科2班  池庆");
	    else if(actionCommand.equals("="))//当监听到等号时，则处理 input  
	    {  
	    	String str1;
	    	String str2= "";
	    	try
	    	{
	    		str1 = cac.wri.getText();
	    		str2 = compute(str1);
	    	}
	    	catch(Exception exc) {
	    		cac.wri.append("\n请按C清除");
	    		actionCommand = "";
	    	}
	    	cac.wri.append(actionCommand+str2);
	    }  
	    else  
	    	cac.wri.append(actionCommand);//数字为了避免多位数的输入 不需要加空格  
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
		p1.setLayout(new GridLayout(5,4));	// 面板内网格布局
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
				b[i].setFont(new Font("宋体", Font.PLAIN, 14));
			}
			
		}
		
		add(wri,BorderLayout.NORTH);					//整个框架中的布局
		add(p1);
	}
	
	public static void main(String []args){
		Caculator f = new Caculator();
		f.setTitle("Calutor");
		f.setBounds(750,300,240,290);					//设置窗口的位置和大小
		f.setVisible(true);
	}
	
}

