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
