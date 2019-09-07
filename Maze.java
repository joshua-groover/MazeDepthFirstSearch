public class Maze {
	private char[][] maze = {{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
							 {'X', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', 'X'},
							 {'X', '.', 'X', '.', 'X', 'X', 'X', '.', 'X', 'X', 'X', 'X', '.', 'X', '.', 'X', '.', 'X'},
							 {'X', '.', 'X', '.', '.', '.', 'X', '.', '.', 'X', '.', 'X', '.', 'X', '.', 'X', '.', 'X'},
							 {'X', '.', 'X', '.', 'X', '.', 'X', '.', '.', '.', '.', 'X', '.', 'X', '.', 'X', '.', 'X'},
							 {'X', '.', '.', '.', 'X', '.', 'X', '.', 'X', '.', '.', '.', '.', 'X', '.', '.', '.', 'X'},
							 {'X', '.', 'X', '.', 'X', '.', '.', '.', 'X', 'X', '.', '.', '.', '.', '.', 'X', '.', 'X'},
							 {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}};

	private Point robot_loc = new Point(1, 1);
  private Point start_loc = new Point(1, 1);
	private Point goal_loc = new Point(10, 3);

	public Point getGoal() {
		Point goal_copy = new Point(goal_loc.x, goal_loc.y);
		return goal_copy;
	}
  
  public int[] getGoalLocation() {
		int[] location = new int[2];
    for (int y = 0; y < maze.length; y++) {
			for (int x = 0; x < maze[y].length; x++) {
				Point current = new Point(x, y);
				if (current.equals(goal_loc)) {
					location[0] = x;
          location[1] = y;
          return location;
				}
			}
		}
    
    return location;
  }
  
  public int[] getInitialLocation() {
		int[] location = new int[2];
    for (int y = 0; y < maze.length; y++) {
			for (int x = 0; x < maze[y].length; x++) {
				Point current = new Point(x, y);
				if (current.equals(robot_loc)) {
					location[0] = x;
          location[1] = y;
          return location;
				}
			}
		}
    
    return location;
  }
  
  public boolean checkIfAtGoal(Point location)
  {
    if(location.equals(goal_loc))
    {
      //System.out.println("True in maze");

      return true;
    }
    
    //System.out.println("False in maze");
    return false;
  }
    
  public int getHeight() {
		int height = maze.length;
		return height;
	}
  
  public int getWidth() {
    int width = maze[0].length;
    return width;
  }
  
  public char mazeVal(int x, int y)
  {
    char mazeValue = maze[y][x];
    return mazeValue;
  }

	public char look(char direction) {
		Point lookedAt = generatePoint(robot_loc, direction);

		if (lookedAt == null) {
			return 'E';
		}

		return maze[lookedAt.y][lookedAt.x];
	}

	public boolean isWall(Point loc) {
		if (maze[loc.y][loc.x] == 'X') {
			return true;
		}
		return false;
	}

	public boolean win() {
		
    return robot_loc.equals(goal_loc);
	}
  
  public boolean beginning() {
		
    return robot_loc.equals(start_loc);
	}

	public boolean move(char direction) {
		Point potentialLoc = generatePoint(robot_loc, direction);
		
		if (potentialLoc == null) {
			return false;
		}

		if (!isWall(potentialLoc)) {
			System.out.println("Moving the robot " + direction);
			robot_loc = potentialLoc;
			return true;
		}
		else {
			return false;
		}
	}

	public void printMaze() {
		for (int y = 0; y < maze.length; y++) {
			for (int x = 0; x < maze[y].length; x++) {
				Point current = new Point(x, y);
				if (current.equals(robot_loc)) {
					System.out.print('R');
				} else if (current.equals(goal_loc)) {
					System.out.print('G');
				} else {
					System.out.print(maze[y][x]);
				}
			}
			System.out.println();
		}
	}

	private Point generatePoint(Point initial, char direction) {
		Point newPoint = null;
		switch (direction) {
			case 'N':
				newPoint = new Point(initial.x, initial.y - 1);
				break;
			case 'S':
				newPoint = new Point(initial.x, initial.y + 1);
				break;
			case 'E':
				newPoint = new Point(initial.x + 1, initial.y);
				break;
			case 'W':
				newPoint = new Point(initial.x - 1, initial.y);
				break;
			default:
				System.out.println("ERROR: incorrect direction, must be N S E or W. Direction requested: " + direction);
				break;
		}

		return newPoint;
	}
}