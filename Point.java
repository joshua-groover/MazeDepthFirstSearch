public class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Point other) {
		if (this.x == other.x) {
			if (this.y == other.y) {
				return true;
			}
		}
		return false;
	}
}