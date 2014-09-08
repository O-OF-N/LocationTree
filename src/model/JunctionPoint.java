package model;

import java.util.HashSet;
import java.util.Set;

public class JunctionPoint {

	private int locationId;
	private Set<Integer> directChildren;
	private Set<Integer> allChildren;
	private Set<Integer> junctionPointIds;
	private Set<JunctionPoint> junctionPoints = new HashSet<>();
	private JunctionPoint parent;
	private int pathId;
	
	public Set<Integer> getJunctionPointIds() {
		return junctionPointIds;
	}

	public void setJunctionPointIds(Set<Integer> junctionPointIds) {
		this.junctionPointIds = junctionPointIds;
	}

	public int getPathId() {
		return pathId;
	}

	public void setPathId(int pathId) {
		this.pathId = pathId;
	}

	public JunctionPoint(JunctionPoint parent) {
		this.parent = parent;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public Set<Integer> getDirectChildren() {
		return directChildren;
	}

	public Set<Integer> getAllChildren() {
		return allChildren;
	}

	public Set<JunctionPoint> getJunctionPoints() {
		return junctionPoints;
	}

	public void setJunctionPoints(Set<JunctionPoint> junctionPoints) {
		this.junctionPoints = junctionPoints;
	}

	public JunctionPoint getParent() {
		return parent;
	}

	public void setParent(JunctionPoint parent) {
		this.parent = parent;
	}

	// To add direct children
	public void addDirectChildren(Set<Integer> locations) {
		if (directChildren == null)
			directChildren = new HashSet<Integer>();
		System.out.println("CURRENT SET OF DIRECT CHILDREN FOR"+ locationId);
		System.out.println(this.directChildren);
		this.directChildren.addAll(locations);
		System.out.println("MODIFIED SET OF DIRECT CHILDREN FOR"+ locationId);
		System.out.println(this.directChildren);
		addAllChildren(locations);

	}

	// To add all children
	public void addAllChildren(Set<Integer> locations) {

		if (this.allChildren == null)
			this.allChildren = new HashSet<Integer>();
		System.out.println("CURRENT SET OF ALL CHILDREN FOR"+ locationId);
		System.out.println(this.allChildren);
		this.allChildren.addAll(locations);
		System.out.println("MODIFIED SET OF ALL CHILDREN FOR"+ locationId);
		System.out.println(this.directChildren);
		if (parent != null)
			parent.addAllChildren(locations);
	}
}
