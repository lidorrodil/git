package view;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;

public interface View {
	void start();

	public void showDirPath(String[] str);

	void showMaze(Maze3D maze);

	void displayMazeSection(int[][] section);

	void showMazeMemory(String name, long memory);

	void showMazeFileSize(String name, long kilobytes);

	void showSolution(ArrayList<Position> arrayList);

	void writeToConsole(String s);
}
