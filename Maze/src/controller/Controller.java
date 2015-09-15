package controller;

import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;
import model.Model;
import view.View;

public interface Controller {

	void setModel(Model m);

	void setView(View v);

	HashMap<String, Command> getCommandMap();

	void doneGenerateMaze(String name,Maze3D generatedMaze);

	void displayMaze(Maze3D maze);

	void displayMazeSection(int[][] section);

	void doneSolveMaze(String name, ArrayList<Position> solution);

	void displaySolution(ArrayList<Position> arrayList);

	void writeToConsole(String s);
}
