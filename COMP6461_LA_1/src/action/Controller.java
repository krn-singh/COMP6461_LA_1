package action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

import utils.Attributes;

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
	public void getRequest(Attributes attributes) throws IOException {
		
		Socket socket = null;
		BufferedWriter bufferWriter = null;
		BufferedReader bufferReader = null;
		try {	
			socket = new Socket(attributes.getHost(), attributes.getPort());
			bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			request = "";
			
			// building a GET request
			request += "GET " +attributes.getPath()+" HTTP/1.0\r\n";
			request += "Host: "+attributes.getHost()+"\r\n";
			if (attributes.getHeaders() != null) {
				addHeaders(attributes.getHeaders());
			}
			request += "\r\n";
			
			bufferWriter.write(request);
			bufferWriter.flush();
			
			bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String response = "";
			String line;
			boolean isVerbose = true;
			
			// Getting response from host
			while ((line = bufferReader.readLine())!= null){

				if (line.trim().isEmpty()) {
					isVerbose = false;
					continue;
				}
				if (!isVerbose) {	response += line+"\n";	}				
			}
			System.out.println(response);
		} finally {
			request = null;
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
	public void getRequestWithVerbose(Attributes attributes) throws IOException {
		
		Socket socket = null;
		BufferedWriter bufferWriter = null;
		BufferedReader bufferReader = null;
		try {	
			socket = new Socket(attributes.getHost(), attributes.getPort());
			bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			request = "";
			
			// building a GET request
			request += "GET " +attributes.getPath()+" HTTP/1.0\r\n";
			request += "Host: "+attributes.getHost()+"\r\n";
			if (attributes.getHeaders() != null) {
				addHeaders(attributes.getHeaders());
			}
			request += "\r\n";
			
			bufferWriter.write(request);
			bufferWriter.flush();
			
			bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String response = "";
			String line;
			
			// Getting response from host
			while ((line = bufferReader.readLine())!= null){

				response += line+"\n";
			}
			System.out.println(response);
		} finally {
			request = null;
			bufferReader.close();
			bufferWriter.close();
			socket.close();
		}
	}
	
	/**
	 * Simple POST request to fetch additional data from host.
	 * 
	 * @param host Name of the host
	 * @param port Port Number(By default, HTTP has port number 80)
	 * @param path Directory within the host
	 * @param headers Collection of request headers with key-value pair
	 * @throws IOException 
	 */
	public void postRequest(Attributes attributes) throws IOException {
		
		Socket socket = null;
		BufferedWriter bufferWriter = null;
		BufferedReader bufferReader = null;
		try {	
			socket = new Socket(attributes.getHost(), attributes.getPort());
			bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			request = "";
			
			// building a POST request
			request += "POST " +attributes.getPath()+" HTTP/1.0\r\n";
			request += "Host: "+attributes.getHost()+"\r\n";
			if (attributes.getHeaders() != null) {
				addHeaders(attributes.getHeaders());
			}
			request += "\r\n";
			
			bufferWriter.write(request);
			bufferWriter.flush();
			
			bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String response = "";
			String line;
			boolean isVerbose = true;
			
			// Getting response from host
			while ((line = bufferReader.readLine())!= null){

				if (line.trim().isEmpty()) {
					isVerbose = false;
					continue;
				}
				if (!isVerbose) {	response += line+"\n";	}				
			}
			System.out.println(response);			
		} finally {
			request = null;
			bufferReader.close();
			bufferWriter.close();
			socket.close();
		}
	}
	
	/**
	 * POST request with verbose[-v] option. Prints the detail of the response such as 
	 * protocol, status, and headers. Verbosity could be useful for testing and 
	 * debugging stages where you need more information to do so.
	 * 
	 * @param host Name of the host
	 * @param port Port Number(By default, HTTP has port number 80)
	 * @param path Directory within the host
	 * @param headers Collection of request headers with key-value pair
	 * @throws IOException 
	 */
	public void postRequestWithVerbose(Attributes attributes) throws IOException {
		
		Socket socket = null;
		BufferedWriter bufferWriter = null;
		BufferedReader bufferReader = null;
		try {	
			socket = new Socket(attributes.getHost(), attributes.getPort());
			bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			request = "";
			
			// building a POST request
			request += "POST " +attributes.getPath()+" HTTP/1.0\r\n";
			request += "Host: "+attributes.getHost()+"\r\n";
			if (attributes.getHeaders() != null) {
				addHeaders(attributes.getHeaders());
			}
			request += "\r\n";
			
			bufferWriter.write(request);
			bufferWriter.flush();
			
			bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String response = "";
			String line;
			
			// Getting response from host
			while ((line = bufferReader.readLine())!= null){

				response += line+"\n";
			}
			System.out.println(response);			
		} finally {
			request = null;
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
	
	/**
	 * Associate the body of the HTTP Request with the inline data[-d], 
	 * meaning a set of characters for standard input. Similarly to -d, 
	 * -f associate the body of the HTTP Request with the data from a given file.
	 * 
	 * @param data Collection of HTTP Request data with key-value pair
	 */
	public void data(HashMap<String, String> data) {
		try {
			data.forEach((key,value) -> {
				request += key+": "+value+"\r\n";
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Save response to an external file
	 * 
	 * @param fileForHttpResponse User efined file name for storing response
	 * @param response HTTP Response
	 * @throws IOException
	 */
	public void saveResponse(String fileForHttpResponse, String response) throws IOException {
		BufferedWriter write = new BufferedWriter(new FileWriter(new File(fileForHttpResponse)));
		try {
			write.write(response);
		} catch (Exception e) {	
			e.printStackTrace();
		} finally {	
			write.close();
		}
	}
}
