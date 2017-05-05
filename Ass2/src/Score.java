/*
	SCORE CLASS: Used for comparator of PriorityQueue

	Used for comparing each object in the PriorityQueue
	@Params: AStarState (state object), Weight (total w so far in current state)
	@Methods:
	int getScore = returns score of state
	int getState = returns the state object
	int compareTo = used to re-order PQ lowest->highest
	int hashCode = returns a hash number???
	boolean equals = compares SCORE objects
*/
public class Score implements Comparable<Score> {
	private Integer score;
	private final State state;

	public Score(State s, Integer scr) {
		this.state = s;
		this.score = scr;
	}

	public int getScore() {
		return score;
	}

	public State getState() {
		return state;
	}

	// We want the lowest score to be the head of the PQ
	@Override
	public int compareTo(Score s) {
		if (score > s.getScore()) {
			return 1;
		} else if (score < s.getScore()) {
			return -1;
		}
		return 0;
	}
	
	/**
	 * Override default hash method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	
	/**
	 * Override default equals method.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Score other = (Score) obj;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score)) {
			return false;
		}
		
		if (state == null) {
			if (other.state != null) return false;
		} else if (!state.equals(other.state)) {
			return false;
		}
		return true;
	}
}