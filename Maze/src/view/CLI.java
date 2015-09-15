package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import controller.Command;

public class CLI extends Thread {

	BufferedReader in;
	PrintWriter out;
	HashMap<String, Command> commandMap;

	public CLI(BufferedReader in, PrintWriter out, HashMap<String, Command> commandMap) {
		this.in = in;
		this.out = out;
		this.commandMap = commandMap;
	}

	public void start() {
		new Thread(new Runnable() {

			public void run() {
				// Command command;
				String line;
				try {

					while (!(line = in.readLine()).equals("exit")) {

						boolean commandOk = false;

						String command = null;
						
						//matching all regular expressions with the given user command
						Iterator<String> regexIterator = commandMap.keySet().iterator();
						while (regexIterator.hasNext() && !commandOk) {
							command = regexIterator.next();
							commandOk = line.matches(command);

						}
						//matched a command
						if (commandOk) {
							System.out.println("command " + line + " is ok");
							Command userCmd = commandMap.get(command);
							//execute the user command
							userCmd.doCommand(line.split(" "));
						}
						//no command match
						else {
							System.out.println("Unrecognized command " + line);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}

}
