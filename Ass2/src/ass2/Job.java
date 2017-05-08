/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */


/**
 * Job Class
 * 
 * Represents each job that needs to be completed.
 */
public class Job {
	private String from;
	private String to;
	private int cost;
	
	/**
	 * Job constructor
	 * @param from city #1
	 * @param to city #2
	 * @param cost of traveling between cities
	 */
	public Job(String from, String to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
	
	/**
	 * Grabbing cost of the job
	 * @return job cost.
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * Override default hash method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}
	
	/**
	 * Override default equals method
	 * 
	 * This is to consider that two states are the same.
	 * If they are in the same location and have the same jobs.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Job other = (Job) obj;
		if (from == null) {
			if (other.from != null) return false;
		} else if (!from.equals(other.from)) {
			return false;
		}

		if (to == null) {
			if (other.to != null) return false;
		} else if (!to.equals(other.to)) {
			return false;
		}
		return true;
	}
}
