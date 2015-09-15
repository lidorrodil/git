package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.AStar;
import algorithms.search.BestFirstSearch;
import algorithms.search.CommonSearcher;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.SearcheableMaze;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel extends CommonModel {

	public MyModel(Controller c) {
		super(c);
	}

	public String[] getDirPath(String str) {

		File f = new File(str);

		if (str.length() == 0) {
			c.writeToConsole("Invalid path");
			return null;
		}

		if (!f.isDirectory()) {
			c.writeToConsole(str + " is not a directory");
			return null;
		}
		return f.list();
	}

	@Override
	public void generateMaze(String name, int width, int height, int depth) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				Maze3D myMaze = new MyMaze3dGenerator().generate(width, height, depth);

				c.doneGenerateMaze(name, myMaze);
			}
		}).start();
	}

	@Override
	public void getMazeSection(Maze3D maze3d, char section, int index) {

		switch (section) {
		case 'x':
			c.displayMazeSection(maze3d.getCrossSectionByX(index));
			break;
		case 'y':
			c.displayMazeSection(maze3d.getCrossSectionByY(index));
			break;
		case 'z':
			c.displayMazeSection(maze3d.getCrossSectionByZ(index));
			break;
		}
	}

	@Override
	public void saveMaze(Maze3D maze3d, String fullPath) {
		try {
			OutputStream out = new MyCompressorOutputStream(new FileOutputStream(fullPath + ".maz"));
			out.write(maze3d.toByteArray());
			out.flush();
			out.close();

			c.writeToConsole("saved maze");
		} catch (Exception e) {
			c.writeToConsole("error: " + e.getMessage());
		}
	}

	@Override
	public Maze3D loadMaze(String fullPath) {

		MyDecompressorInputStream in;
		try {
			in = new MyDecompressorInputStream(new FileInputStream(new File(fullPath + ".maz")));

			byte[] b = new byte[in.getLength()];
			in.read(b);

			Maze3D loadedMaze = new Maze3D(b);

			in.close();

			return loadedMaze;

		} catch (FileNotFoundException e) {
			c.writeToConsole("no such file " + fullPath);
		} catch (IOException e) {
			c.writeToConsole("bad file format in " + fullPath);
		}
		return null;
	}

	@Override
	public long getMazeMemorySize(Maze3D maze) {
		byte[] b = maze.toByteArray();
		return b.length;
	}

	@Override
	public long getMazeFileSize(String fullPath) {
		File mazeFile = new File(fullPath + ".maz");
		return mazeFile.length() / 1024;
	}

	@Override
	public void solveMazeByAlgorithm(Maze3D maze, String algorithm, String name) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				SearcheableMaze searchableMaze = new SearcheableMaze(maze);
				CommonSearcher<Position> searcher;
				ArrayList<Position> solution = null;

				if (algorithm.equals("BestFirstSearch")) {

					searcher = new BestFirstSearch<Position>();
					solution = searcher.search(searchableMaze).getSolution();
				}

				if (algorithm.equals("AStarManhattanDistance")) {

					searcher = new AStar<Position>(new MazeManhattanDistance());
					solution = searcher.search(searchableMaze).getSolution();

				}

				if (algorithm.equals("AStarMazeAirDistance")) {

					searcher = new AStar<Position>(new MazeAirDistance());
					solution = searcher.search(searchableMaze).getSolution();

				}

				c.doneSolveMaze(name, solution);

				c.writeToConsole("solution for " + name + " is ready");
			}
		}).start();
	}
}
