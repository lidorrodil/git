package model;

import algorithms.mazeGenerators.Maze3D;

public interface Model {
	public String[] getDirPath(String str);

	public void generateMaze(String name, int width, int height, int depth);

	public void getMazeSection(Maze3D maze3d, char section, int index);

	public void saveMaze(Maze3D maze3d, String fullPath);

	public Maze3D loadMaze(String fullPath);

	public long getMazeMemorySize(Maze3D maze);

	public long getMazeFileSize(String fullPath);

	public void solveMazeByAlgorithm(Maze3D maze, String algorithm, String name);
}
