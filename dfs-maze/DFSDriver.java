import java.util.*;
import java.io.*;

public class DFSDriver
{
	public static void main(String args[])
	{
		char[][] maze = loadMaze(args[0], 8, 8);
		Stack ourStack = new Stack();
		Point startPoint = findStart(maze);
		dfs(maze, startPoint, ourStack);
	}
	
	public static void dfs(char[][] maze, Point currentPoint, Stack stack){
		System.out.println(stack);
		if( maze[currentPoint.x][currentPoint.y] == 'G'){
			System.out.println("Found goal at: " + currentPoint.toString());
			return;
		}
		maze[currentPoint.x][currentPoint.y] = 'V';
		printMaze(maze);
		Point validNeighbor = findNeighbor(maze, currentPoint);
		if(validNeighbor == null){
			Point prevPoint = stack.pop();
			dfs(maze, prevPoint, stack);
		}
		else{
			stack.push(currentPoint);
			dfs(maze, validNeighbor, stack);
		}
	}

	public static Point findNeighbor(char[][] maze, Point currentPoint){
		if(maze[currentPoint.x + 1][currentPoint.y] == 'O' || maze[currentPoint.x + 1][currentPoint.y] == 'G'){
			return new Point(currentPoint.x + 1, currentPoint.y);
		} 
		else if(maze[currentPoint.x][currentPoint.y + 1] == 'O' || maze[currentPoint.x][currentPoint.y + 1] == 'G'){
			return new Point(currentPoint.x, currentPoint.y + 1);
		} 
		else if(maze[currentPoint.x - 1][currentPoint.y] == 'O' || maze[currentPoint.x - 1][currentPoint.y] == 'G'){
			return new Point(currentPoint.x - 1, currentPoint.y);
		} 
		else if(maze[currentPoint.x][currentPoint.y - 1] == 'O' || maze[currentPoint.x][currentPoint.y - 1] == 'G'){
			return new Point(currentPoint.x, currentPoint.y - 1);
		}
		return null;
	}

	public static char[][] loadMaze(String fileName, int xSize, int ySize){
		char[][] maze = new char[xSize][ySize];
		try{
			Scanner s = new Scanner(new File(fileName));
			int curX = 0;
			while(s.hasNextLine()){
				String line = s.nextLine();
				for(int curY=0; curY<line.length(); curY++){
					maze[curX][curY] = line.charAt(curY);
				}
				curX++;
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		return maze;
	}

	public static void printMaze(char[][] maze){
		for(int i=0; i<maze.length; i++){
			for(int j=0; j<maze[i].length; j++){
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}

	public static Point findStart(char[][] maze){
		for(int i=0; i<maze.length; i++){
			for(int j=0; j<maze[i].length; j++){
				if(maze[i][j] == 'S'){
					return new Point(i, j);
				}
			}
		}
		return null;
	}
}