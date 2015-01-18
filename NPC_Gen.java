// Generate-NPC
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;  // Using Frame class in package java.awt
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NPC_Gen extends JFrame
{
	String INPUT_FILE = "blerg";
	NPC_Gen()
	{
		
		
		
			
		
		add(new NPCGenInnards());
		
		setTitle("NPC Generator");
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		repaint();
		
	}
	
	public class NPCGenInnards extends JTabbedPane 
	{
			int DISTANCE = 10;
		
			NPCGenInnards()
			{
				class myTab extends Container
				{
					myTab()
					{
						setLayout(new GridBagLayout());
					
					}
				
				
				
				}
				
				myTab FileInputTab = new myTab();
				FileInputTab.setName("File Input");
				FileInputTab.add(new JTextField());
				FileInputTab.add(new JButton());
				
				add(FileInputTab);
				
				
				final List<List<String>> allAttributes = new ArrayList(0);
				
				BufferedReader in;
				try
				{
					in = new BufferedReader(new FileReader(INPUT_FILE));
					String text = in.readLine(); 
					in.close();
				} catch (FileNotFoundException e)
				{
					System.out.println("Error: File Not Found");
				} catch (IOException e)
				{
					System.out.println("Error: File Processing Error");
				} 

				

				myTab blerg = new myTab();
				blerg.setName("Generation");
				
				class Generator extends JButton
				{
					Generator()
					{
						this.setText("Generate new NPC");
						this.addActionListener(new ActionListener()
						{
		
							public void actionPerformed( ActionEvent e)
							{
								Random random = new Random();
								
								int first_x = random.nextInt(allAttributes.size());
								int first_y = random.nextInt(allAttributes.get(0).size());
								
								String baseAttribute = find_closest(allAttributes, first_x, first_y);
								
								double direction = Math.toRadians(random.nextInt(360));
								int distance = random.nextInt(DISTANCE);
								
								
								
								int y_dif = (int) (Math.sin(direction)*distance);
								int  x_dif = (int) (Math.cos(direction)*distance);
								
								int new_x = first_x + x_dif;
								int new_y = first_y + y_dif;
		
								//Somehow get the next attribute
								
								
								System.out.println("blerg");
								
							}
						});					
					}
				}
				
				blerg.add(new Generator());
				add(blerg);
				
				myTab AttributeTab = new myTab();
				AttributeTab.setName("Attribute Mapper");
				add(AttributeTab);

			}
			
			String find_closest(List<List<String>> theAttributes, int x, int y)
			{
				if(x == theAttributes.size())
					return "null";
				else if(y == theAttributes.get(0).size())
					return "null";
				
				String here = theAttributes.get(x).get(y);
				if(here != "null")
				{
					return here;
				}
				
				for(int i =1; x-i <= 0 && y-i <= 0; i++)
				{
					here = theAttributes.get(x).get(y-i);
					if(here != "null")
					{
						return here;
					}
					
					here = theAttributes.get(x-i).get(y);
					if(here != "null")
					{
						return here;
					}
					
					here = theAttributes.get(x-i).get(y-i);
					if(here != "null")
					{
						return here;
					}
				}
				return "null";
			}
			
			
		
		
		
		
		
		
	}
	public static void main(String[] args)
	{
		new NPC_Gen();
   
		}
}
