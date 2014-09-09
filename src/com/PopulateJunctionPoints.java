package com;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import model.Data;
import model.JunctionPoint;

public class PopulateJunctionPoints {

	Map<Integer, Integer> locationType = Data.locationType;
	Map<Integer, Set<Integer>> path = Data.path;
	Map<Integer, Set<Integer>> pathThroughJunction = Data.pathThroughJunction;
	
	public JunctionPoint buildCommonJunctionPoint(int locationId){
		Set<JunctionPoint> currentJunctions = new HashSet<JunctionPoint>();
		Set<JunctionPoint> childJunctions = new HashSet<JunctionPoint>();
		JunctionPoint commonJunction = null;
		Set<Integer> pathIds = pathThroughJunction(10);
		for (Integer pathId : pathIds) {
			commonJunction = new JunctionPoint(null);
			commonJunction.setLocationId(10);
			commonJunction.setPathId(pathId);
			commonJunction.addDirectChildren(locationsOnPath(pathId));
			commonJunction.setJunctionPointIds(junctionsOnPath(pathId));
			buildChildJunctionPoints(commonJunction);
			currentJunctions.add(commonJunction);
			while (currentJunctions.size() > 0) {
				for (JunctionPoint currentParent : currentJunctions) {
					for (JunctionPoint jp1 : currentParent.getJunctionPoints()) {
						buildChildJunctionPoints(jp1);
						childJunctions.add(jp1);
					}
				}
				currentJunctions = new HashSet<JunctionPoint>();
				currentJunctions.addAll(childJunctions);
				childJunctions = new HashSet<JunctionPoint>();
			}

		}
		return commonJunction;
	}

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

	public JunctionPoint buildChildJunctionPoints(JunctionPoint parent) {
		Set<Integer> junctionPointIds = parent.getJunctionPointIds();
		for (Integer junction : junctionPointIds) {
			if (junction != parent.getLocationId()) {
				Set<Integer> pathIds = pathThroughJunction(junction);
				for (Integer pathId : pathIds) {
					if (pathId != parent.getPathId()) {
						JunctionPoint jp = new JunctionPoint(parent);
						jp.setLocationId(junction);
						jp.setPathId(pathId);
						jp.addDirectChildren(locationsOnPath(pathId));
						jp.setJunctionPointIds(junctionsOnPath(pathId));
						parent.getJunctionPoints().add(jp);
					}
				}
			}
		}
		return parent;
	}

	public Set<JunctionPoint> findLocation(int locationId, Set<JunctionPoint> jps) {
		Set<JunctionPoint> junctionPoints = new LinkedHashSet<JunctionPoint>();
		JunctionPoint currentJunctionPoint = null;
		
		while(true){
			for (JunctionPoint jp : jps) {
				if (jp.getAllChildren().contains(locationId)) {
					currentJunctionPoint = jp;
					break;
				} else{
					currentJunctionPoint = null;
				}
			}
			if(null!= currentJunctionPoint){
			junctionPoints.add(currentJunctionPoint);
			if(currentJunctionPoint.getDirectChildren().contains(locationId))
				break;
			else{
				jps = new HashSet<JunctionPoint>();
				jps.addAll(currentJunctionPoint.getJunctionPoints());
			}
			}
		}
		return junctionPoints;

	}


}
