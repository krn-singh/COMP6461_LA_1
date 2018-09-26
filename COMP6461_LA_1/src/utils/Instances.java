package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Contains instances of required classes and implements the singleton design pattern.
 * 
 * @author karan
 *
 */
public class Instances {

	// static variable socket of type Singleton 
	private static Socket socket = null;
	// static variable inputReader of type Singleton 
	private static InputStreamReader inputReader = null;
	// static variable outputReader of type Singleton 
	private static OutputStreamWriter outputReader = null;
	// static variable bufferReader of type Singleton 
	private static BufferedReader bufferReader = null;
	// static variable bufferWriter of type Singleton 
	private static BufferedWriter bufferWriter = null;
	
	/**
	 * static method to create instance of Socket class
	 * 
	 * @return instance of Socket class
	 */
    public static Socket getSocketInstance() 
    { 
        if (socket == null) 
        	socket = new Socket(); 
  
        return socket; 
    }
    
	/**
	 * static method to create instance of InputStreamReader class
	 * 
	 * @return instance of InputStreamReader class
	 */
    public static InputStreamReader getInputStreamInstance()
    { 
    	try {
    		if (inputReader == null) 
            	inputReader = new InputStreamReader(getSocketInstance().getInputStream()); 
		} catch (IOException e) {
			e.printStackTrace();
		}

        return inputReader; 
    }
    
	/**
	 * static method to create instance of OutputStreamWriter class
	 * 
	 * @return instance of OutputStreamWriter class
	 */
    public static OutputStreamWriter getOutputStreamInstance()
    { 
    	try {
    		if (outputReader == null) 
    			outputReader = new OutputStreamWriter(getSocketInstance().getOutputStream(), "UTF-8"); 
		} catch (IOException e) {
			e.printStackTrace();
		}

        return outputReader; 
    }
    
	/**
	 * static method to create instance of BufferedReader class
	 * 
	 * @return instance of BufferedReader class
	 */
    public static BufferedReader getBufferedReaderInstance()
    { 
    	if (bufferReader == null) 
			bufferReader = new BufferedReader(getInputStreamInstance()); 
    	
        return bufferReader; 
    }
    
	/**
	 * static method to create instance of BufferedWriter class
	 * 
	 * @return instance of BufferedWriter class
	 */
    public static BufferedWriter getBufferedWriterInstance()
    { 
    	if (bufferWriter == null) 
    		bufferWriter = new BufferedWriter(getOutputStreamInstance()); 
    	
        return bufferWriter; 
    }
}
