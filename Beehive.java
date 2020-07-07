package Beehive;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.Timer;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Beehive implements ActionListener {
	int count =0;
	JLabel firstlabel,secondlabel,thridlabel,title,background;
	JTextField firstField,secondField,thridField; 
	JFrame frame;
	JPanel panel;
	JTextArea textArea;
	JButton addbutton,runbutton,Exitbutton;
	public Beehive() {
		Timer tm = new Timer();
		frame = new JFrame();
		
		// Create Buttons
		
		addbutton = new JButton("Create the configFile");
		addbutton.setVerticalTextPosition(AbstractButton.BOTTOM);
		addbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		addbutton.setPreferredSize(new Dimension(50,35));
		addbutton.setBackground(Color.CYAN);// set the color of the button
		addbutton.addActionListener(this);
		runbutton = new JButton("    Run Simulation    ");
		runbutton.setVerticalTextPosition(AbstractButton.BOTTOM);
		runbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		runbutton.setPreferredSize(new Dimension(50,35));
		runbutton.setBackground(Color.GREEN);
		runbutton.addActionListener(this);
		Exitbutton = new JButton("Stop the Simulation");
		Exitbutton.setVerticalTextPosition(AbstractButton.BOTTOM);
		Exitbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		Exitbutton.setBackground(Color.RED);
		Exitbutton.setPreferredSize(new Dimension(50,35));
		Exitbutton.addActionListener(this);
		
		// Label
		title = new JLabel("Beehive Simulation");
		firstlabel = new JLabel("simulationDays: ");
		secondlabel = new JLabel("initWorkers: ");
		thridlabel = new JLabel("initHoney: ");
		
		title.setForeground(Color.BLACK);
		firstlabel.setForeground(Color.BLACK);
		secondlabel.setForeground(Color.BLACK);
		thridlabel.setForeground(Color.BLACK);
		
		// Feild to enter the values.
		firstField = new JTextField(10);
		secondField = new JTextField(10);
		thridField = new JTextField(10);
		
		// add all the components to the Frame 
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		frame.add(panel,BorderLayout.CENTER);
		panel.add(title);
		panel.add(firstlabel);
		panel.add(firstField);
		panel.add(secondlabel);
		panel.add(secondField);
		panel.add(thridlabel);
		panel.add(thridField);
		panel.add(Box.createRigidArea(new Dimension(5, 5)));
		panel.add(addbutton);//add the addbutton
		panel.add(Box.createRigidArea(new Dimension(5, 5)));
		panel.add(runbutton);//add the runbutton
		panel.add(Box.createRigidArea(new Dimension(5, 5)));
		panel.add(Exitbutton);
		panel.add(Box.createRigidArea(new Dimension(5, 5)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 700);//set the size of the Frame
		frame.setTitle("BEEHIVE SIMULATION");
		
		frame.pack();
		
		frame.setVisible(true);
	
	}
	
	private static double numberOfWorkerBees=0;
	private static double simulationdays=0;
	private static int eggsLaid=0;
	private static int totalEggsLaid;
	private static int countDeath=0;
	private static double honey=3000;
	private static int countDay;
	private static int countBirths=0;
	private static int countLarva=0;
	private static int countPupa=0;
	private static int countDrones=0;
	private static int rose=0;
	private static int Hibiscus=0;
	private static int Frangipani=0;
	private static double nectar;
	private static ArrayList<Bee> BeeArray = new ArrayList<Bee>(1000);
	private static ArrayList<Flowers> FlowersArray = new ArrayList<Flowers>(5);
	private static Stack<Bee> workersLaunchChamber = new Stack<Bee>();
public  void CreateSimulationConfig(int num1,int num2 , int num3) {// Function to Create the configfile.
		try {
		      FileWriter myWriter = new FileWriter("C:\\Users\\Junaid\\Documents\\Programming\\workspace\\BeehiveVer2\\simconfig.txt");
		      myWriter.write("simulationDays "+num1+"\n");
		      myWriter.write("initWorkers "+num2+"\n");
		      myWriter.write("initHoney "+num3+"\n");
		      myWriter.close();
		      System.out.println("ConfigFile Created Successfully \n You can now Process with the Simulation");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}	

	
public static void readSimulationConfig() throws FileNotFoundException {
	try(BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Junaid\\Documents\\Programming\\workspace\\BeehiveVer2\\simconfig.txt"))) {
		    String str;
		    int i = 0;
		    double numbers[] = new double [3];
		    while ((str = in.readLine()) != null) {
		        String[] tokens = str.split(" ");
		        numbers [i] = Double.parseDouble(tokens[1]);
		        i++;
		    }
		    
	simulationdays = numbers[0];
	numberOfWorkerBees = numbers[1];
	honey =  numbers[2];
	System.out.println("Simulationdays \t\n"+simulationdays);
	System.out.println("initworker \t\n"+numberOfWorkerBees);
	System.out.println("Honey \t\n"+honey);
		 
		}
	catch (IOException e) {
		System.out.println(e.toString());
		}					  
	}
public static void logDailyStatusToFile1() {// load only the header of the file :)
	
	try {
		PrintWriter pw= new PrintWriter(new FileWriter("C:\\Users\\Junaid\\Documents\\Programming\\workspace\\BeehiveVer2\\simLog.csv",false));
		StringBuilder sb = new StringBuilder();
		
			sb.append("Day");
			sb.append(",");
			sb.append("eggsLaid");
			sb.append(",");
			sb.append("eggInHive");
			sb.append(",");
			sb.append("Larva");
			sb.append(",");
			sb.append("Pupa");
			sb.append(",");
			sb.append("Worker");
			sb.append(",");
			sb.append("Drone");
			sb.append(",");
			sb.append("Death");
			sb.append(",");
			sb.append("Birth");
			sb.append(",");
			sb.append("HoneyStock");
			sb.append(",");
			sb.append("Flower 1 Nectar");
			sb.append(",");
			sb.append("Flower 2 Nectar");
			sb.append(",");
			sb.append("Flower 3 Nectar");
			sb.append(",");
	
			sb.append("\r\n");
			pw.write(sb.toString());
			pw.close();
		
			
		
	}catch(Exception e){
}
}
public static void logDailyStatusToFile() {// load  the vales of the file :)
	
	try {
		PrintWriter pw= new PrintWriter(new FileWriter("C:\\Users\\Junaid\\Documents\\Programming\\workspace\\BeehiveVer2\\simLog.csv",true));
		StringBuilder sb = new StringBuilder();
		
		sb.append(countDay);
		sb.append(",");
		sb.append(eggsLaid);
		sb.append(",");
		sb.append(totalEggsLaid);
		sb.append(",");
		sb.append(countLarva);
		sb.append(",");
		sb.append(countPupa);
		sb.append(",");
		sb.append(numberOfWorkerBees);
		sb.append(",");
		sb.append(countDrones);
		sb.append(",");
		sb.append(countDeath);
		sb.append(",");
		sb.append(countBirths);
		sb.append(",");
		sb.append(honey);
		sb.append(",");
		sb.append(rose);
		sb.append(",");
		sb.append(Hibiscus);
		sb.append(",");
		sb.append(Frangipani);
		sb.append(",");
		

		sb.append("\r\n");
		pw.write(sb.toString());
		pw.close();
		
			
		
	}catch(Exception e){
		
}
}

public static void initBeesArray(double numberOfWorkerBees ) {
	for(int i=0;i<numberOfWorkerBees;i++) {
		Bee Bee1= new Bee("Worker",21,0,false,true);
	    BeeArray.add(Bee1);
		}
		
	}
public static int layDailyEggs() {
	
	
	//Generates a random number from 10-50
	Random randomEggs = new Random();
    final int MAX_EGGS = 50;
    final int MIN_EGGS = 10;
    
    
    int x = randomEggs.nextInt((MAX_EGGS - MIN_EGGS) + 1) + MIN_EGGS;
    eggsLaid = x;
    return x;
    
}
public static void addEggToHive(int eggsLaid) {
	
		for(int i =0;i<eggsLaid;i++) {
			if(BeeArray.size()<1000) {
				Bee Bee2 = new Bee("Egg",0,0,false,true);
				BeeArray.add(Bee2);
			}
		
	}
	}

public static void incrementAge() {
	for(Bee ob:BeeArray) {
		 int age =ob.getAge();
		 ob.incrementAge(age);
	}
}
public static void metamorphose() {
	Random random = new Random();
	int age;
	String type ;
	for(int i=0;i<BeeArray.size();i++) {
		age = BeeArray.get(i).getAge();
		type = BeeArray.get(i).getType();
		if(age>4 && type=="Egg") {
			BeeArray.get(i).setType("larva");
		}else if(age>10 && type=="larva") {
			BeeArray.get(i).setType("pupa");		
		}else if(age>20 && type=="pupa"){
			double probability = random.nextDouble();
			if(probability<=0.1) {
				BeeArray.get(i).setType("Drone");
			}else {
				BeeArray.get(i).setType("Worker");
			}
			
			}else if(type=="Worker" && age>=35) {
				funeral(i);
			}else if (type=="Drone" && age>=54) {
				funeral(i);
			}
	}
}
public static void feedingTime() {
	if(honey<2) {
		System.exit(0);// Terminal the simulation as less than 2 unit of honey and the queen won't able to feed
	}
	honey=honey-2;// Feeding the for the queen 
	
		for(int i=0;i<BeeArray.size();i++) {
		String type = BeeArray.get(i).getType();
		if(honey>0) {
			if(type=="larva") {
				honey=honey-0.5;
				BeeArray.get(i).setEaten(true);
			}else if (type=="Worker" || type=="Drone") {
				honey=honey-1;
				BeeArray.get(i).setEaten(true);
			}
		}else {
			BeeArray.get(i).setEaten(false);
		}
		
	}
		
}

public static void undertakencheck() {
	for(int i=0;i<BeeArray.size();i++) {
		String type = BeeArray.get(i).getType();
		Boolean isEaten = BeeArray.get(i).isEaten();
		if(type=="larva" && isEaten==false) {
			funeral(i);
		}else if (type=="Worker" && isEaten==false) {
			funeral(i);
			countDeath++;
		}else if(type=="Drone" && isEaten==false) {
			funeral(i);
		}
	}
}
public static void funeral(int i) {
	BeeArray.remove(i);
	countDeath++;
}
public static void counttypesofbee() {
	countBirths=0;
	countLarva=0;
	countPupa=0  ;  
	numberOfWorkerBees=0;
	countDrones=0;
	 for(int i=0;i<BeeArray.size();i++) {
		String type =BeeArray.get(i).getType();
		if(type=="larva") {
			countLarva++;
			countBirths++;
		}else if(type=="pupa"){
			countPupa++;
			countBirths++;
		}else if(type=="Worker") {
			numberOfWorkerBees++;
			
		}else if(type=="Drone") {
			countDrones++;
			
		}
	 }
}
public static void countflowers() {
	rose = FlowersArray.get(0).getCurrentNectarAvailable();
	Hibiscus=FlowersArray.get(1).getCurrentNectarAvailable();
	Frangipani=FlowersArray.get(2).getCurrentNectarAvailable();
	
}

public static void printFlowerGarden() {
	System.out.println("Flower Roses nectar Stock :"+rose);
	System.out.println("Flower Hibiscus nectar Stock :"+Hibiscus);
	System.out.println("Flower Frangipani nectar Stock "+Frangipani);
	
}

public static void AllWorkerBeesGardenSorties() {
	for(int i=0;i<BeeArray.size();i++) {
		String type =BeeArray.get(i).getType();
		if(type=="Worker") {
			workersLaunchChamber.push(BeeArray.get(i));
			BeeArray.remove(i);
		}
	}
	while(!workersLaunchChamber.isEmpty()){
		visitFlower(workersLaunchChamber.pop());
	}
	
}
public static void visitFlower(Bee bee) {
	Random random = new Random();

	 double probability = random.nextDouble();
	 int numofsorties = bee.getNectarCollectionSorties();
		if (probability<0.3333) {
		int current = FlowersArray.get(0).getCurrentNectarAvailable();
		int collection = FlowersArray.get(0).getNectarCollection();
		
		if(current>=20) {
			current = current-collection;
			nectar = nectar + collection;
			FlowersArray.get(0).setCurrentNectarAvailable(current);
			numofsorties++;
			bee.setNectatCollectionSorties(numofsorties);
		}
			else if(numofsorties<=5){
			visitFlower(bee);
		}
		
		 }else if(probability>0.33334 && probability<=0.66667) {
			 	int current = FlowersArray.get(1).getCurrentNectarAvailable();
				int collection = FlowersArray.get(1).getNectarCollection();
				if(current>=10) {
					current = current-collection;
					nectar = nectar + collection;
					FlowersArray.get(1).setCurrentNectarAvailable(current);
					numofsorties++;
					bee.setNectatCollectionSorties(numofsorties);
				}
				else  if(numofsorties<=5){
					visitFlower(bee);
				}
		 }else if(probability>0.66667 && probability<=0.9999) {
			 	int current = FlowersArray.get(2).getCurrentNectarAvailable();
				int collection = FlowersArray.get(2).getNectarCollection();
			 if(current>=50) {
					current = current-collection;
					nectar = nectar + collection;
					FlowersArray.get(2).setCurrentNectarAvailable(current);
					numofsorties++;
					bee.setNectatCollectionSorties(numofsorties);
					
			 }
			 else if(numofsorties<=5) {
				 visitFlower(bee);
			 }
		 }
		 
		 	honey = (int) honey+(nectar/40);
			double nectarRemain=nectar%40;
			nectar=nectarRemain;
			
			BeeArray.add(bee);
		 
}
public static void emptyStomachofAllBees() {
	for(int i=0;i<BeeArray.size();i++) {
		BeeArray.get(i).setEaten(false);
		BeeArray.get(i).setNectatCollectionSorties(0);
	}
}
public static void resetFlowerArray() {
	for(int i=0;i<FlowersArray.size();i++) {
		String name ;
		name=FlowersArray.get(i).getFlowerName();
		if(name=="Rose") {
			FlowersArray.get(i).setNectarCollection(20);
			FlowersArray.get(i).setCurrentNectarAvailable(20000);
			FlowersArray.get(i).setDailyNectarProduction(20000);
		}else if(name=="Frangip") {
			FlowersArray.get(i).setNectarCollection(50);
			FlowersArray.get(i).setCurrentNectarAvailable(50000);
			FlowersArray.get(i).setDailyNectarProduction(50000);
		}else if(name=="Hibiscus") {
			FlowersArray.get(i).setNectarCollection(10);
			FlowersArray.get(i).setCurrentNectarAvailable(10000);
			FlowersArray.get(i).setDailyNectarProduction(10000);
		}
	}
}
public static void printBeeHiveStatus() {
	
	 totalEggsLaid=eggsLaid+totalEggsLaid;	
	 counttypesofbee();
	
	//Print the BeehiveStatus
	System.out.println("************* This is Day " + countDay +" *****************");
	System.out.println("Queen laid "+ eggsLaid +" eggs! ");
	System.out.println("Beehive Status");
	System.out.println("Egg count: "+ totalEggsLaid);
	System.out.println("Larva Count: "+countLarva  );
	System.out.println("Pupa Count: " + countPupa);
	System.out.println("Worker Count: "+ numberOfWorkerBees);
	System.out.println("Drone Count: " + countDrones );
	System.out.println("Death Count: " + countDeath );
	System.out.println("Birth Count: "+ countBirths);
	System.out.println("Honey Stock: " + honey );
	printFlowerGarden();
}
public static void aDayPasses() {
	addEggToHive(layDailyEggs());
	incrementAge();
	counttypesofbee();
	metamorphose();
	resetFlowerArray();
	for(int i =0;i<5;i++) {
		AllWorkerBeesGardenSorties();
	}
		
	
	countflowers();
	emptyStomachofAllBees();
	feedingTime();
	undertakencheck();
    try {
    	Thread.sleep(1000);
    }catch(InterruptedException e) {
    		e.printStackTrace();
    		}

	countDay++;
}

	public static void main(String[] args) throws FileNotFoundException, Exception {
			Flowers Rose = new Flowers("Rose",20,20000,20000);
			Flowers Frangip = new Flowers("Frangip",50,50000,50000);
			Flowers Hibiscus = new Flowers("Hibiscus",10,10000,10000);
			FlowersArray.add(Rose);
			FlowersArray.add(Frangip);
			FlowersArray.add(Hibiscus);
			logDailyStatusToFile1();
			
			new Beehive();	
		
	}  
			
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== runbutton) {// when the runbutton is clicked 
			try {
				readSimulationConfig();// read config file
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			initBeesArray(numberOfWorkerBees);//add worker in the 
			for(int i=0;i<simulationdays;i++) {	
				aDayPasses();
				System.out.println();
				printBeeHiveStatus();
				System.out.println();
				logDailyStatusToFile();
		}
	}else if(e.getSource()==addbutton) { // add the values from the field to the configfile.
		int num1 = Integer.parseInt(firstField.getText());//get the values from the field
		int num2 = Integer.parseInt(secondField.getText());
		int num3 = Integer.parseInt(thridField.getText());
		CreateSimulationConfig(num1,num2,num3);// call the CreateSimulationConfig 
	}else if(e.getSource()==Exitbutton) {
		System.exit(0);
	}
	}
		
			
}

		
			


