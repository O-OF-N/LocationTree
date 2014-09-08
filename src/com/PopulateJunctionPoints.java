package com;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Data;
import model.JunctionPoint;

public class PopulateJunctionPoints {

	Map<Integer, Integer> locationType = Data.locationType;
	Map<Integer, Set<Integer>> path = Data.path;
	Map<Integer, Set<Integer>> pathThroughJunction = Data.pathThroughJunction;

	public Set<Integer> junctionsOnPath(int pathId) {
		Set<Integer> locations = path.get(pathId);
		Set<Integer> junctionPoints = new HashSet<>();
		for (Integer location : locations) {
			if (locationType.get(location) == 2)
				junctionPoints.add(location);
		}
		return junctionPoints;
	}

	public Set<Integer> locationsOnPath(int pathId) {
		return path.get(pathId);
	}

	public Set<Integer> pathThroughJunction(int locationId) {
		return pathThroughJunction.get(locationId);
	}
	
	/*public void buildJunctionPoints() {
		PopulateJunctionPoints pj = new PopulateJunctionPoints();
		Set<Integer> locations = Data.path.get(pathId);
		System.out.println(locations);
		for (Integer location : locations) {
			if (Data.locationType.get(location) == 2) {
				Set<Integer> pathIds = pj.pathThroughJunction(location);
				System.out.println(pathIds);
				for (Integer path : pathIds) {

					System.out.println(this.pathId);
					System.out.println(path.intValue());
					if (this.pathId != path.intValue()) {
						System.out.println("Here>>>>>>>");
						JunctionPoint jp = new JunctionPoint(this);
						jp.setLocationId(locationId);
						jp.setPathId(pathId);
						jp.addDirectChildren(pj.locationsOnPath(path));
						jp.buildJunctionPoints();
						junctionPoints.add(jp);
					} else{
						System.out.println("not Here>>>>>>");
					}
				}
			}
		}
	}*/
	
	public JunctionPoint buildJunctionPoints(Set<Integer> junctionPointIds, JunctionPoint parent){
		
		for(Integer junction : junctionPointIds){
			Set<Integer> pathIds = pathThroughJunction(junction);
			for(Integer pathId : pathIds){
				if(pathId != parent.getPathId()){
					JunctionPoint jp = new JunctionPoint(parent);
					jp.setLocationId(junction);
					jp.setPathId(pathId);
					jp.addDirectChildren(locationsOnPath(pathId));
					jp.setJunctionPointIds(junctionsOnPath(pathId));
					
					parent.getJunctionPoints().add(jp);
				}
			}
			
		}
			return parent;
	}

}
