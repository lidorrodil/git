package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import controller.Command;
import controller.Controller;

public class MyView extends CommonView implements View {

	CLI cli;
	HashMap<String, Command> commandMap;

	public MyView(Controller c) {
		super(c);

		cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out),
				getController().getCommandMap());
	}

	@Override
	public void start() {
		cli.start();

	}

	public void showDirPath(String[] str) {
		System.out.println("show the directories:");
		for (String s : str)
			System.out.println(s);
	}

	@Override
	public void showMaze(Maze3D maze) {
		maze.printMaze();
	}

	@Override
	public void displayMazeSection(int[][] section) {

		int axis1Delimiter = section.length;
		int axis2Delimiter = section[0].length;

		for (int i = 0; i < axis1Delimiter; i++) {
			for (int j = 0; j < axis2Delimiter; j++) {
				System.out.print(section[i][j] + ",");
			}
			System.out.println();
		}
	}

	@Override
	public void showMazeMemory(String name, long memory) {

		System.out.println("The size of "+name+" is: "+memory);

	}

	@Override
	public void showMazeFileSize(String name,long kilobytes) {
		System.out.println("The file size of maze " + name + " is: " + kilobytes + " (kilobytes)");

	}

	@Override
	public void showSolution(ArrayList<Position> arrayList) {
		System.out.println("Solution path:" + arrayList);

	}

	@Override
	public void writeToConsole(String s) {
		System.out.println(s);
	}

}
