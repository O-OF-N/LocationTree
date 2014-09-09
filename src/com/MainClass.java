package com;

import java.util.HashSet;
import java.util.Set;

import model.Data;
import model.JunctionPoint;

public class MainClass {

	MainClass mc = new MainClass();

	public static void main(String[] args) {
		Data.populateData();

		PopulateJunctionPoints pj = new PopulateJunctionPoints();
		JunctionPoint commonJunction = pj.buildCommonJunctionPoint(10);
		System.out.println("?????????????");
		System.out.println(commonJunction);
		for (JunctionPoint jp10 : commonJunction.getJunctionPoints()) {
			System.out.println("JP10>>>>>>>>>>>>>");
			System.out.println(jp10);
			for (JunctionPoint jp11 : jp10.getJunctionPoints()) {
				System.out.println("JP11>>>>>>>>>>>>>");
				System.out.println(jp11);
				for (JunctionPoint jp12 : jp11.getJunctionPoints()) {
					System.out.println("JP12>>>>>>>>>>>>>");
					System.out.println(jp12);
				}
			}
		}
		Set<JunctionPoint> jps = new HashSet<JunctionPoint>();
		jps.add(commonJunction);
		System.out.println("finding Nemo >>>>>>>>>>>>>>");
		System.out.println(pj.findLocation(29, jps));

	}
}
