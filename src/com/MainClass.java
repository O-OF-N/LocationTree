package com;

import java.util.HashSet;
import java.util.Set;

import model.Data;
import model.JunctionPoint;

public class MainClass {

	MainClass mc = new MainClass();

	public static void main(String[] args) {
		Data.populateData();
		int level = 0;
		int levelMax = 2;
		Set<Integer> jpsCovered = new HashSet<>();
		PopulateJunctionPoints pj = new PopulateJunctionPoints();
		Set<Integer> pathIds = pj.pathThroughJunction(10);
		System.out.println(">>>>>>>>>>>>>>>" + pathIds);
		for (Integer pathId : pathIds) {
			JunctionPoint jp = new JunctionPoint(null);
			System.out.println(">>>>>>>>>" + pathId);
			jp.setLocationId(10);
			jp.setPathId(pathId);
			jp.addDirectChildren(pj.locationsOnPath(pathId));
			jp.setJunctionPointIds(pj.junctionsOnPath(pathId));
			pj.buildJunctionPoints(jp.getJunctionPointIds(), jp);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(jp.getDirectChildren());
			System.out.println(jp.getAllChildren());
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
			level = 1;
			JunctionPoint currentParent = jp;
			jpsCovered.add(10);
			while (level < levelMax) {
				System.out.println("LEVEL =" + level);
				/*if (level == 1) {
					pj.buildJunctionPoints(jp.getJunctionPointIds(), jp);
					currentParent = jp;
				} else {*/
					System.out.println("junction points at this level are = "
							+ currentParent.getJunctionPointIds());
					System.out.println("junction points covered = "+ jpsCovered);
					for (JunctionPoint jp1 : currentParent.getJunctionPoints()) {
						System.out.println("current junction point = " + jp1.getLocationId());
						if (!jpsCovered.contains(jp1.getLocationId())) {
							System.out.println("inside>>");
							pj.buildJunctionPoints(jp1.getJunctionPointIds(),
									jp1);
							System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!"
									+ jp1.getLocationId());
							System.out.println(jp1.getDirectChildren());
							System.out.println(jp1.getAllChildren());
							System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
							currentParent = jp1;
							jpsCovered.add(currentParent.getLocationId());
						}
					//}
				}
				level++;
			}
			System.out.println("?????????????");
			System.out.println(jp);
			for(JunctionPoint jp10 : jp.getJunctionPoints()){
				System.out.println("JP10>>>>>>>>>>>>>");
				System.out.println(jp10);
				for(JunctionPoint jp11 : jp10.getJunctionPoints()){
					System.out.println("JP11>>>>>>>>>>>>>");
					System.out.println(jp11);					
				}
			}
				
			System.out.println(currentParent);
			System.out.println(jpsCovered);
			
		}

	}
}
