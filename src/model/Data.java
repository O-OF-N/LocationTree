package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Data {

	public static Map<Integer, Integer> locationType = new HashMap<Integer, Integer>();
	public static Map<Integer, Set<Integer>> path = new HashMap<Integer, Set<Integer>>();
	public static Map<Integer, Set<Integer>> pathThroughJunction = new HashMap<Integer, Set<Integer>>();

	public static void populateData() {
		locationType.put(10, 2);
		locationType.put(11, 1);
		locationType.put(12, 2);
		locationType.put(13, 1);
		locationType.put(14, 1);
		locationType.put(15, 1);
		locationType.put(16, 2);
		locationType.put(17, 1);
		locationType.put(18, 1);
		//Set<Integer> path1 = new HashSet<>();
		//path1.add(10);
		//path.put(1, path1);
		Set<Integer> path2 = new HashSet<>();
		path2.add(10);
		path2.add(11);
		path2.add(12);
		path.put(2, path2);
		Set<Integer> path3 = new HashSet<>();
		path3.add(14);
		path3.add(13);
		path3.add(12);
		path3.add(15);
		path3.add(16);
		path3.add(17);
		path.put(3, path3);
		Set<Integer> path4 = new HashSet<>();
		path4.add(16);
		path4.add(18);
		path.put(4, path4);
		Set<Integer> paths = new HashSet<>();
		paths.add(2);
		pathThroughJunction.put(10, paths);
		paths = new HashSet<>();
		paths.add(3);
		paths.add(2);
		pathThroughJunction.put(12, paths);
		paths = new HashSet<>();
		paths.add(3);
		paths.add(4);
		pathThroughJunction.put(16, paths);

	}

}
