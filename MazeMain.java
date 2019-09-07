public class MazeMain {
	public static void main(String[] args) {
		Maze maze = new Maze();
		Robot pepe = new Robot(maze);

		pepe.plan();

		while(!maze.win()) {
			System.out.println();
			maze.printMaze();

			pepe.takeAction();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {

			}
		}
    System.out.println("You win");
    
    while(!maze.beginning()) {
			System.out.println();
			maze.printMaze();

			pepe.backToBeginning();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {

			}
		}
    System.out.println("You win again?");
    
	}
}