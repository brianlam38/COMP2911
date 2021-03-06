/**
	SCORE CLASS
	
	Used as the PriorityQueue comparator, for comparing
	States by their score in the AStar search algorithm.
*/

/**
 * Score is a comparator class for use in AStar's Priority Queue.
 * 
 * It helps order the priority queue by a certain State's total estimated cost.
 * States with lower total estimated cost will therefore be expanded first.
 */
public class Score implements Comparable<Score> {
	
	private Integer score;
	private final State state;
	
	/**
	 * Score constructor
	 * @param state for comparison
	 * @param score for comparison
	 */
	public Score(State state, Integer score) {
		this.state = state;
		this.score = score;
	}
	
	/**
	 * Get the current state object
	 * @return
	 */
	public State getState() {
		return state;
	}

	/**
	 * Get the current cost
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Override default comparison method so that the
	 * lowest cost state will be at the top of the PQ.
	 */
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