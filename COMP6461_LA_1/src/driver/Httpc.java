package driver;

import java.io.IOException;
import java.util.HashMap;

import action.Controller;
import utils.Constants;

public class Httpc {
	
	private static String URL;
	private static String host;
	private static String path;
	private static String query;
	private static HashMap<String, String> headers = null;
	
	private static void parseURL(String URL) {
		String str = URL;
		
		//check if URL starts with http:// or http://
		if(str.startsWith("http://"))
			str = str.substring(7);
		else if(str.startsWith("https://"))
			str = str.substring(8);
		
		//checking first occurence of '/' in the string without http:// or https://
		int index1 = str.indexOf('/');
		//checking first occurence of '?' in the string without http:// or https://
		int index2 = str.indexOf('?');
		
		//splitting the string into host, path & query based on indices of '/' & '?'
		host = str.substring(0, index1);
		if(index2 == -1) {
			path = str.substring(index1);
		}
		else {
			path = str.substring(index1, index2);
			query = str.substring(index2);
		}
	}
	
	private static void parseCommand(String[] args) throws IOException {
		
		Controller obj = new Controller();
		
		String command = args[1];
		switch (command) {
		case "help":
			if (args.length == 2)
				System.out.println(Constants.HELP);					//command -> httpc help
			else if (args.length == 3 && args[2].equals("get"))
				System.out.println(Constants.GETHELP);				//command -> httpc help get
			else if (args.length == 3 && args[2].equals("post"))
				System.out.println(Constants.POSTHELP);				//command -> httpc help post
			else
				System.out.println(Constants.INVALID);				//Invalid Command
				break;
		case "get":
			if(args.length == 3)
				obj.getRequest(host, Constants.DEFAULT_PORT, path, headers);				//command -> httpc get URL
			else if (args.length == 4)
				obj.getRequestWithVerbose(host, Constants.DEFAULT_PORT, path, headers);	//command -> httpc get -v URL
			else
				headers = new HashMap<>();
				if(args[2].equals("-v")) {	//with verbose
					int numHeaders = (args.length-4)/2;
					for(int i=0; i<numHeaders; i++) {
						int argNumber =  4+i*2;
						String keyValue[] = args[argNumber].split(":");
						headers.put(keyValue[0] + "\"", "\"" + keyValue[1]);
					}
					obj.getRequestWithVerbose(host, Constants.DEFAULT_PORT, path, headers); //command -> httpc get -v (-h key:value)* URL
				}
				else { 	//without verbose
					int numHeaders = (args.length-3)/2;
					for(int i=0; i<numHeaders; i++) {
						int argNumber =  3+i*2;
						String keyValue[] = args[argNumber].split(":");
						headers.put(keyValue[0] + "\"", "\"" + keyValue[1]);
					}
					obj.getRequest(host, Constants.DEFAULT_PORT, path, headers); //command -> httpc get (-h key:value)* URL
				}
			break;
		case "post":
			//callPost();
			break;
		default:
			System.out.println(Constants.INVALID);
			break;
		}		
	}

	public static void main(String[] args) throws IOException {
		
		if(!args[1].equals("help")) {
			
			//check if output is to be stored into file
			if(args[args.length - 2].equals("-o")) {
				//getting URL i.e. the third last element of the command line arguments
				URL = args[args.length - 3];
				Constants.storeOutputToFile = true;
			}
			else {
				//getting URL i.e. the last element of the command line arguments
				URL = args[args.length - 1];
			}
			//split URL into host, path & query
			parseURL(URL);
		}
		
		//parse arguments
		parseCommand(args);
	}

}
