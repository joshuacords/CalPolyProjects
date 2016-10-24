import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class UniversalFA {

	ArrayList alphabet = new ArrayList<String>();
	ArrayList states = new ArrayList<String>();
	ArrayList finalStates = new ArrayList<String>();
	//HashMap stores transitions as state1 + alphabet, state2
	HashMap<String, String> transition = new HashMap<String, String>();
	String initialState;
	String newLine = System.getProperty("line.separator").toString();
	
	
	//loads a text file "fileName" into the UniversalFA 
	//Format space separated
	//alphabet		ex: a b c 1 2 3
	//states		ex: q0 q1 q2
	//initial state	ex: q0
	//final states	ex: q1 q2
	//transitions	ex: q0 a q1
	//					q0 b q2
	//					q1 a q1
	//					q1 b q2
	public void load(String fileName)
	{
		String line;
		String split[];
		try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //input alphabet
            if((line = bufferedReader.readLine()) != null) {
            	
            	split = line.split(" ");
            	for(String s : split)
            	{
            		alphabet.add(s);
            	}
            	
            }   
            //input states
            if((line = bufferedReader.readLine()) != null) {
            	
            	split = line.split(" ");
            	for(String s : split)
            	{
            		states.add(s);
            	}
            	
            }
            	
            //input initial state
            if((line = bufferedReader.readLine()) != null) {
            	
            	split = line.split(" ");
            	for(String s : split)
            	{
            		initialState = s;
            	}
            	
            }	
       
            //input final states
            if((line = bufferedReader.readLine()) != null) {
            	
            	split = line.split(" ");
            	for(String s : split)
            	{
            		//check if final state is a member of states
                	if(states.contains(s))
                	{
                		finalStates.add(s);
                	}
                	else
                	{
                		System.out.println("Error: Final state " + s + " is not a member of states.");
                		return;
                	}
            	}
            	
            }	
            	
        	//input transitions
            while((line = bufferedReader.readLine()) != null) {
            	String line1, line2;
            	split = line.split(" ");
            	line = split[0];
            	line1 = split[1];
            	line2 = split[2];
            	
            	//check if state transitions are valid and enter it to hashmap
            	if(states.contains(line) && alphabet.contains(line1) && states.contains(line2))
            	{
            		transition.put(line +","+ line1, line2);
            	}
            	else
            	{
            		System.out.println("Error: Transition " + line + ", " + line1 + " -> " + line2 + " is not a a proper transition.");
            		return;
            	}
            }
            bufferedReader.close();  
            //check if 
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
		
		
	}
	
	public String toString()
	{
		String transitionOutput = transition.toString();
		transitionOutput = transitionOutput.replace(" ", "\n");
		String output = "Alphabet: {" + alphabet.toString() + "}" + newLine + "States: {" + states + "}" + newLine +"Initial State: " +
		initialState + newLine + "Final States: {" + finalStates + "}" + newLine + "Transitions: \n" + transitionOutput + "\n\nTest Strings:\n";
		output = output.replace("[",  "");
		output = output.replace("]",  "");
		//add all transitions
		//output.concat("Transitions: "+ newLine + transition.toString());
		return output;
	}
	
	public boolean acceptString(char[] test)
	{
		boolean accept = false;
		String state = initialState;
		for(char c : test){	
			state = transition.get(state +","+ c);
			}

//		String tempState = state;	//for testing only
		//state = transition.get(state +","+ c);
//		System.out.println("State: " + tempState + "+" + c + " -> " + state); //for testing only
		accept = finalStates.contains(state);
		
		return accept;
	}
	
	
	
	
}
