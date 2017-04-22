import java.util.*;

public class MainSystem {

}

/* DESIGN */
// Single truck, completing one job at a time.
// AIM: Schedule an optimal route for the truck to complete a joblist (set of jobs)
// Truck can be run continuously (perhaps with different drivers), so the system can focus on scheduling the optimal route
// The journey may include additional/multiple road trips from the end of one job to the start of the next job.

// JOBLIST
// Joblist contains X number of distinct jobs
// Jobs inside joblist can be scheduled in any order
// First job always starts in SYDNEY

// COSTS
// Travel costs between towns -> travel costs are the same in either direction between two towns
// Unloading cost for each job

// TOWNS
// Name of town = 1 word
// If there is no cost specified between two towns, it is not possible to travel between the two towns in one trip
// All jobs will only be between towns that ARE directly connected (can be done in one trip)

// TRIANGLE INEQUALITY
// For any towns A, B, C, travel from A->C