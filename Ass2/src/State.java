/**
 * COMP2911 FreightSystem Assignment 2
 * Date: April 2017
 * Author: Brian Lam
 */

import java.util.HashSet;

/**
	State class.

	Represents different states, i.e. city / jobsList.
*/
public class State {
	
	private String city;
	private HashSet<Job> jobs;
	
	/**
	 * Constructor for State
	 */
	public State(String city, HashSet<Job> jobs) {
		this.city = city;
		this.jobs = jobs;
	}
	
	/**
	 * Returns location of the city in the current state
	 */	
	public String getCity() {
		return city;
	}
	
	/**
	 * Returns a set of jobs in the current state
	 */
	public HashSet<Job> getJobs() {
		return jobs;
	}
	
	/**
	 * Override default hash methods
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((jobs == null) ? 0 : jobs.hashCode());
		return result;
	}
	
	/**
	 * Override default equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		State other = (State) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city)) {
			return false;
		}

		if (jobs == null) {
			if (other.jobs != null)
				return false;
		} else if (!jobs.equals(other.jobs)) {
			return false;
		}
		return true;
	}
}
