import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


class ActionE implements ActionListener{
	private Test a;
	public ActionE(Test t) {
		// TODO Auto-generated constructor stub
		a = t;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		a.txt.append(str);
	} 
}

public class Test extends JFrame {
	
	JButton b1,b2,b3;
	private static final long serialVersionUID = 1L;

	JTextArea txt = new JTextArea();
	
	Test() {
	Panel p = new Panel();
	p.setLayout(new GridLayout());
	b1 = new JButton("ChangeTitile");
	b2 = new JButton("TurnRed");
	b3 = new JButton("AppendText");
	
	p.setLayout(new GridLayout());
	
	p.add(b1);
	p.add(b2);
	p.add(b3);
	p.add(txt);
	add(p);
	
	//注册事件监听器
	ActionE ct = new ActionE(this);
	ActionE tr = new ActionE(this); 
	ActionE at = new ActionE(this);

	b1.addActionListener(ct);
	b2.addActionListener(tr);
	b3.addActionListener(at);
	
	}
	
	
	public static void main(String []argc) {
		Test fra = new Test();
		fra.setTitle("实验按钮监视");
		fra.setBounds(330,300,500,100);
		fra.setVisible(true);
	}
}
