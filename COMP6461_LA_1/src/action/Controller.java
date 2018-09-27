package action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

/**
 * Provides the methods that perform the required actions(say, GET, POST) as per the user request.
 * 
 * @author karan
 *
 */
public class Controller {

	// Stores the request
	private String request = null;
	
	/**
	 * Simple GET request to fetch data from host.
	 * 
	 * @param host Name of the host
	 * @param port Port Number(By default, HTTP has port number 80)
	 * @param path Directory within the host
	 * @param headers Collection of request headers with key-value pair
	 * @throws IOException 
	 */
	public void getRequest(String host, int port, String path, HashMap<String, String> headers) throws IOException {
		
		Socket socket = null;
		BufferedWriter bufferWriter = null;
		BufferedReader bufferReader = null;
		try {	
			socket = new Socket(host, port);
			bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			request = "";
			
			// building a GET request
			request += "GET " +path+" HTTP/1.0\r\n";
			request += "Host: "+host+"\r\n";
			if (headers != null) {
				addHeaders(headers);
			}
			request += "\r\n";
			
			bufferWriter.write(request);
			bufferWriter.flush();
			
			bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String response;
			boolean isVerbose = true;
			
			// Getting response from host
			while ((response = bufferReader.readLine())!= null){

				if (response.trim().isEmpty()) {
					isVerbose = false;
					continue;
				}
				if (!isVerbose) {	System.out.println(response);	}				
			}			
		} finally {
			request = null;
			headers = null;
			bufferReader.close();
			bufferWriter.close();
			socket.close();
		}
	}
	
	/**
	 * GET request with verbose[-v] option. Prints the detail of the response such as 
	 * protocol, status, and headers. Verbosity could be useful for testing and 
	 * debugging stages where you need more information to do so.
	 * 
	 * @param host Name of the host
	 * @param port Port Number(By default, HTTP has port number 80)
	 * @param path Directory within the host
	 * @param headers Collection of request headers with key-value pair
	 * @throws IOException 
	 */
	public void getRequestWithVerbose(String host, int port, String path, HashMap<String, String> headers) throws IOException {
		
		Socket socket = null;
		BufferedWriter bufferWriter = null;
		BufferedReader bufferReader = null;
		try {	
			socket = new Socket(host, port);
			bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			request = "";
			
			// building a GET request
			request += "GET " +path+" HTTP/1.0\r\n";
			request += "Host: "+host+"\r\n";
			if (headers != null) {
				addHeaders(headers);
			}
			request += "\r\n";
			
			bufferWriter.write(request);
			bufferWriter.flush();
			
			bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String response;
			
			// Getting response from host
			while ((response = bufferReader.readLine())!= null){

				System.out.println(response);				
			}			
		} finally {
			request = null;
			headers = null;
			bufferReader.close();
			bufferWriter.close();
			socket.close();
		}
	}
	
	/**
	 * Adds headers to the request message.
	 * 
	 * @param headers Collection of request headers with key-value pair
	 */
	public void addHeaders(HashMap<String, String> headers) {
		try {
			headers.forEach((key,value) -> {
				request += key+": "+value+"\r\n";
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
