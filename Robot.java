import java.util.ArrayList;
import java.util.Stack;


public class Robot {
	Maze maze;
  private int count = 0;
  private ArrayList<Point> pointPath = new ArrayList<Point>();
  public Stack<Character> directionPath = new Stack<Character>();
  private int[][] mark;
  private char[]  directions;
  private char[] backDirections;
  private char heading;
  private int xloc;
  private int yloc;
  private int a=0;
  private int b=0;
	
  public Robot(Maze maze) {
		this.maze = maze;
	}

	public void plan() {
  //This code is where the robot plans, it is essentially where
  //all of the commands get run from
    setInitialLocation();
    setMarkMatrix();
    this.directionPath.push('E'); 
    setHeading();    
    while(!atGoal() && !visitedAllPoints())
    {
      while(unvisitedNeighbors() != true)
      {
        count++;
        setHeading();
        mark[yloc][xloc] = 0;
        moveBack();   
      } 
      while(unvisitedNeighbors() == true)
      {
        count++;
        setHeading();
        mark[yloc][xloc] = 0;
        if(checkRight() == true)
        {
          move(0);
        }
        else if(checkStraight() == true)
        {
          move(1);
        }
        else if(checkLeft() == true)
        {
          move(2);
        }
      }
     }
    setGetDirections();
    setBackDirection();
	}

	public void takeAction() {
    maze.move(directions[a]);
    a++;
	}
  
  public void backToBeginning()
  {
    maze.move(backDirections[b]);
    b++;
  }
  
  
  public void setHeading()
  {
    this.heading = directionPath.pop();
    this.directionPath.push(heading);
  } 
  
  public void moveBack()
  {
    char lastMove = directionPath.peek();
    
    if(lastMove == 'N')
    {
      yloc = yloc + 1;
      directionPath.pop();
    }
    else if(lastMove == 'W')
    {
      xloc = xloc + 1;
      directionPath.pop();
    }
    else if(lastMove == 'S')
    {
      yloc = yloc - 1;
      directionPath.pop();
    }
    else if(lastMove == 'E')
    {
      xloc = xloc - 1;
      directionPath.pop();
    }
    else
    {
      System.out.println("ERROR: Failed to go back a certain direction - moveBack method");
    } 
  }
  
  public void move(int dir)
  {
    
    //right
    if(dir == 0 && heading == 'N')
    {
      xloc = xloc + 1;
      this.directionPath.push('E');
    }
    else if(dir == 0 && heading == 'W')
    {
      yloc = yloc - 1;
      this.directionPath.push('N');
    }
    else if(dir == 0 && heading == 'S')
    {
      xloc = xloc - 1;
      this.directionPath.push('W');
    }
    else if(dir == 0 && heading == 'E')
    {
      yloc = yloc + 1;
      this.directionPath.push('S');
    }
    
    //straight
    else if(dir == 1 && heading == 'N')
    {
      yloc = yloc - 1;
      this.directionPath.push('N');
    }
    else if(dir == 1 && heading == 'W')
    {
      xloc = xloc - 1;
      this.directionPath.push('W');
    }
    else if(dir == 1 && heading == 'S')
    {
      yloc = yloc + 1;
      this.directionPath.push('S');
    }
    else if(dir == 1 && heading == 'E')
    {
      xloc = xloc + 1;
      this.directionPath.push('E');
    }
    
    //left
    else if(dir == 2 && heading == 'N')
    {
      xloc = xloc - 1;
      this.directionPath.push('W');
    }
    else if(dir == 2 && heading == 'W')
    {
      yloc = yloc + 1;
      this.directionPath.push('S');
    }
    else if(dir == 2 && heading == 'S')
    {
      xloc = xloc + 1;
      this.directionPath.push('E');
    }
    else if(dir == 2 && heading == 'E')
    {
      yloc = yloc - 1;
      this.directionPath.push('N');
    }
  }
  
  
  public void setMarkMatrix()
  {
    int height = maze.getHeight();
    int width = maze.getWidth();
    mark = new int[height][width];
    
    for(int h=0; h<height; h++)
    {
      for(int w=0; w<width; w++)
      {
        if(maze.mazeVal(w,h) == '.')
        {
          mark[h][w] = 1;
        }
        else if(maze.mazeVal(w,h) == 'X')
        {
          mark[h][w] = 0;
        }
        System.out.print(mark[h][w]);
      }
      System.out.println();
    }
  }
  
  public void printMarkMatrix()
  {
    int height = maze.getHeight();
    int width = maze.getWidth();
    
    for(int h=0; h<height; h++)
    {
      for(int w=0; w<width; w++)
      {
        if(xloc == w && yloc == h)
        {
          System.out.print('R');
        }
        else
        {
          System.out.print(mark[h][w]);
        }
      }
      System.out.println();
    }
  }
  
  //sets initial location and heading for the point
  public void setInitialLocation()
  {
    int[] initialLocation = new int[2];
    initialLocation = maze.getInitialLocation();
    xloc = initialLocation[0];
    yloc = initialLocation[1];
    heading = 'S';
  }
  
  public boolean atGoal()
  {
    int[] goal;
    goal = maze.getGoalLocation();
    int xgoal = goal[0];
    int ygoal = goal[1];
    
    if(xloc == xgoal && yloc == ygoal)
    {
      System.out.println("You win!");
      return true;
    }
    return false;
  }
  
  public boolean visitedAllPoints()
  {
    for(int h=0; h<mark.length; h++)
    {
      for(int w=0; w<mark[0].length; w++)
      {
        if(mark[h][w] == 1)
        {
          return false;
        }
      }
    }
    return true;  
  }
  
  //check if neighbors of the point are unvisited
  public boolean unvisitedNeighbors()
  {
    if(checkLeft() == true || checkRight() == true || checkStraight() == true)
    {
      return true;
    }
    
    else
    {
      return false;
    }
  }
  
  //checks if their is an unvisited point to the left of the current point
  public boolean checkLeft()
  {
    if(heading == 'N')
    {
      if(mark[yloc][xloc-1] == 1)
      {
        return true;
      }
    }
    else if(heading == 'W')
    {
      if(mark[yloc+1][xloc] == 1)
      {
        return true;
      }
    }
    else if(heading == 'S')
    {
      if(mark[yloc][xloc+1] == 1)
      {
        return true;
      }
    }
    else if(heading == 'E')
    {
      if(mark[yloc-1][xloc] == 1)
      {
        return true;
      }
    }
    return false;
  }

  
  //checks if their is an unvisited point to the right of the current point
  public boolean checkRight()
  {
    if(heading == 'N')
    {
      if(mark[yloc][xloc+1] == 1)
      {
        return true;
      }
    }
    else if(heading == 'W')
    {
      if(mark[yloc-1][xloc] == 1)
      {
        return true;
      }
    }
    else if(heading == 'S')
    {
      if(mark[yloc][xloc-1] == 1)
      {
        return true;
      }
    }
    else if(heading == 'E')
    {
      if(mark[yloc+1][xloc] == 1)
      {
        return true;
      }
    }
    return false;
  }
  
  
  //checks if there is an unvisited point straight ahead of the current point
  public boolean checkStraight()
  {
    if(heading == 'N')
    {
      if(mark[yloc-1][xloc] == 1)
      {
        return true;
      }
    }
    else if(heading == 'W')
    {
      if(mark[yloc][xloc-1] == 1)
      {
        return true;
      }
    }
    else if(heading == 'S')
    {
      if(mark[yloc+1][xloc] == 1)
      {
        return true;
      }
    }
    else if(heading == 'E')
    {
      if(mark[yloc][xloc+1] == 1)
      {
        return true;
      }
    }
    return false;
  }
  
  public void setGetDirections()
  {   
    int length = directionPath.size();
    System.out.println(length);
    directions = new char[length];
    for(int i=(length-1); i>-1; i--)
    {
      directions[i] = directionPath.pop();
    }
  }
  
  public void setBackDirection()
  {      
    int length = directions.length;
    int l2 = length - 1;
    backDirections = new char[length];
    for(int i=(l2); i>-1; i--)
    {
      if(directions[i] == 'N')
      {
        backDirections[l2-i] = 'S';
      }
      else if(directions[i] == 'E')
      {
        backDirections[l2-i] = 'W';
      }
      if(directions[i] == 'S')
      {
        backDirections[l2-i] = 'N';
      }
      if(directions[i] == 'W')
      {
        backDirections[l2-i] = 'E';
      }
    }
  }
}