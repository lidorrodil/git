package controller;

import java.util.ArrayList;
import java.util.HashMap;

import algorithms.mazeGenerators.Maze3D;
import algorithms.mazeGenerators.Position;

public class MyController extends CommonController {

	private HashMap<String, Maze3D> mazeList = new HashMap<String, Maze3D>();
	private HashMap<String, ArrayList<Position>> mazeSolution = new HashMap<String, ArrayList<Position>>();

	public MyController() {
		super();
	}

	@Override
	public void initCommands(HashMap<String, Command> commandMap) {

		// dir <path>
		commandMap.put("dir [^ \n]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				String[] dir = m.getDirPath(args[1]);
				if (dir != null)
					v.showDirPath(dir);
			}
		});

		// generate 3d maze <name> <dimensions (x,y,z)>
		commandMap.put("generate 3d maze [A-Za-z0-9]+ [0-9]{1,2} [0-9]{1,2} [0-9]{1,2}", new Command() {

			@Override
			public void doCommand(String[] args) {

				// no need to try/catch because regex already checked format
				int width = Integer.parseInt(args[4]);
				int height = Integer.parseInt(args[5]);
				int depth = Integer.parseInt(args[6]);
				// send the maze dimensions to the model to generate ( async )
				m.generateMaze(args[3], width, height, depth);
			}
		});

		// display <name>
		commandMap.put("display [^ \n]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				if (mazeList.containsKey(args[1])) {
					v.showMaze(mazeList.get(args[1]));
				} else {
					v.writeToConsole("couldn't find requested maze: " + args[1]);
				}

			}
		});

		// display cross section by <axis> <value> for <name>
		commandMap.put("display cross section by [XYZxyz] [0-9]{1,2} for [A-Za-z0-9]+", new Command() {

			@Override
			public void doCommand(String[] args) {

				if (mazeList.containsKey(args[7])) {
					int index = Integer.parseInt(args[5]);

					m.getMazeSection(mazeList.get(args[7]), args[4].toLowerCase().charAt(0), index);
				} else {
					v.writeToConsole("couldn't find requested maze: " + args[7]);							//change to args[7]
				}
			}
		});

		// save maze <name> <dir>
		commandMap.put("save maze [A-Za-z0-9]+ [^ \n]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				if (mazeList.containsKey(args[2]))
					m.saveMaze(mazeList.get(args[2]), args[3]);

			}
		});

		// load maze <dir> <name>
		commandMap.put("load maze [^ \n]+ [A-Za-z0-9]+", new Command() {

			@Override
			public void doCommand(String[] args) {

				Maze3D loadedMaze = m.loadMaze(args[2]);
				if (loadedMaze != null) {
					mazeList.put(args[3], loadedMaze);
					v.writeToConsole("Succeeded loading maze " + args[3]);
				}
			}
		});

		// maze size <name>
		commandMap.put("maze size [A-Za-z0-9]+", new Command() {

			@Override
			public void doCommand(String[] args) {

				if (mazeList.containsKey(args[2])) {
					Maze3D maze = mazeList.get(args[2]);			

					v.showMazeMemory(args[2], m.getMazeMemorySize(maze));

				}
			}
		});

		// file size <name>
		commandMap.put("file size [A-Za-z0-9]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				v.showMazeFileSize(args[2], m.getMazeFileSize(args[2]));
			}
		});

		// solve <name> <algorithm> (async)
		commandMap.put("solve [A-Za-z0-9]+ [A-Za-z0-9]+", new Command() {

			@Override
			public void doCommand(String[] args) {

				if (mazeList.containsKey(args[1])) {
					Maze3D maze = mazeList.get(args[1]);
					m.solveMazeByAlgorithm(maze, args[2], args[1]);
				}
			}
		});

		// display <solution> <name>
		commandMap.put("display solution [A-Za-z0-9]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				if (mazeSolution.containsKey(args[2])) {
					v.showSolution(mazeSolution.get(args[2]));
				} else
					v.writeToConsole("Solution for " + args[2] + " does not exist");

			}
		});

	}

	public void passDirPath(String[] str) {
		v.showDirPath(str);
	}

	@Override
	public void displayMaze(Maze3D maze) {
		v.showMaze(maze);
	}

	@Override
	public void displayMazeSection(int[][] section) {
		v.displayMazeSection(section);

	}

	@Override
	public void doneSolveMaze(String name, ArrayList<Position> solution) {
		// if a solution already exists - replace it
		if (mazeSolution.containsKey(name))
			mazeSolution.remove(name);
		// puts the new solution in the HashMap
		mazeSolution.put(name, solution);
	}

	@Override
	public void displaySolution(ArrayList<Position> arrayList) {
		v.showSolution(arrayList);

	}

	@Override
	public void doneGenerateMaze(String name, Maze3D generatedMaze) {

		// saves the maze in the HashMap<String,Maze3D>
		mazeList.put(name, generatedMaze);
		System.out.println("maze " + name + " is ready");

	}

	@Override
	public void writeToConsole(String s) {
		v.writeToConsole(s);
	}

}
