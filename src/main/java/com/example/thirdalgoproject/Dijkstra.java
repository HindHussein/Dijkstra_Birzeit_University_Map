package com.example.thirdalgoproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	static ArrayList<Building> Buildings = new ArrayList<Building>();
	static HashMap<String, Building> allNodes = new HashMap<>();
	private Building source;
	private Building destination;
	private PriorityQueue<Building> heap;

	public Dijkstra() {
		// TODO Auto-generated constructor stub
	}

	public Dijkstra(ArrayList<Building> Buildings, Building source, Building destination) {// for each path between two buildings
		heap = new PriorityQueue<>(Buildings.size());// make a heap for the building's number size
		this.destination = destination;
		this.Buildings = Buildings;
		for (Building Building : Buildings) {// reset all buildings cost
			Building.resetTemps();// make all with max integer cost
			if (Building == source) {
				Building.setTempCost(0);// make the source Building cost to zero
			}
			heap.add(Building);// add all Buildings to the heap
		}
	}
    
	public void generateDijkstra() {
		while (!heap.isEmpty() && heap.contains(destination)) {
			Building min = heap.poll();
			LinkedList<Edge> adjacentsList = min.getAdjacentsList();

			
			for (Edge edge : adjacentsList) {
			
				Building adjacentBuilding = edge.getAdjacentBuilding();

				
					if ((min.getTempCost() + edge.getDistance()) < adjacentBuilding.getTempCost()) {//to compare which is the least distance
						heap.remove(adjacentBuilding);//make the building known
						adjacentBuilding.setTempCost(min.getTempCost() + edge.getDistance());//put the new distance
						adjacentBuilding.setTempPrev(min);
						heap.add(adjacentBuilding);
					}
	
			}
				}
		}


	private String pathString;
	 String distanceString;

	public Building[] pathTo(Building destination) {
		LinkedList<Building> Buildings = new LinkedList<>();
		Building iterator = destination;
		distanceString = String.format("%.1f", destination.getTempCost());
		while (iterator != source) {
			Buildings.addFirst(iterator);
			pathString = iterator.getFullName() + ": " + String.format("%.1f", iterator.getTempCost()) + " meter(s)" + "\n"
					+ "\t⇒  " +pathString;
			
			iterator = iterator.getTempPrev();
		}

		return Buildings.toArray(new Building[0]);
		
	}

	public String getPathString() {
		if (countOccurrences(pathString, '\n') <= 1) {//if ther are no path if the countoccurrences less than 0r equale  1
			pathString = "No Path";
			distanceString = "\t\t\t-----------------------------";
			return pathString;
		}

		pathString = pathString;//conctaion

		int truncateIndex = pathString.lastIndexOf('\n');
		//System.out.println(truncateIndex);
		pathString = pathString.substring(0, truncateIndex);
       System.out.println(pathString);
		return pathString;
	}

	private static int countOccurrences(String haystack, char needle) {
		int count = 0;
		
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle) {
				count++;
			}
		}
		System.out.println(count);
		return count;
	}

	public static ArrayList<Building> readFile() throws FileNotFoundException {// method whose read the file
		String line = null;// to read each line
		int numberOfbuldings, numberOfEdges;
		File file = new File("/Users/hindsuleiman/demo/ThirdAlgoProject/da-2.txt");// the data file
		Scanner scan = new Scanner(file);
		line = scan.nextLine();// read the first line only
		String[] str = line.split(" ");
		numberOfbuldings = Integer.parseInt(str[0]);// first integer is number of buldings
		numberOfEdges = Integer.parseInt(str[1]);// second integer is number of edges
		for (int i = 0; i < numberOfbuldings; i++) {// to read all buldings data
			float x, y;
			line = scan.nextLine();// each line is bulding
			String[] strN = line.split(" ");
			x = (float) Double.parseDouble(strN[1]);
			y = (float) Double.parseDouble(strN[2]);
			Building newbulding = new Building(strN[0], x, y);// make an new bulding
			Buildings.add(newbulding);// add it to the array list
			allNodes.putIfAbsent(strN[0], newbulding);// add it to the hash map

		}
		for (int i = 0; i < numberOfEdges; i++) {// to read all edges
			line = scan.nextLine();// each line is bulding
			String[] strN = line.split(" ");
			String frombuldingName = strN[0], tobuldingName = strN[1];
			double distance=Double.parseDouble(strN[2]);// name of source and destination bulding
			Building frombulding = allNodes.get(frombuldingName), toBuilding = allNodes.get(tobuldingName);
			frombulding.addAdjacentBuilding(toBuilding, distance); // add an path between them
		}

		return Buildings;

	}
	



}
