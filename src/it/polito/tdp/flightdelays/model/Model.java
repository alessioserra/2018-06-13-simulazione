package it.polito.tdp.flightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import it.polito.tdp.flightdelays.db.FlightDelaysDAO;

public class Model {

	private FlightDelaysDAO dao;
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<String, Airport> idMap;
	private List<Tratta> tratte;
	private List<Airport> aereoporti;
	private List<Tratta> tratte2;
	
	public Model() {
		dao = new FlightDelaysDAO();
		idMap = new HashMap<>();
		tratte = new ArrayList<>();
		aereoporti = dao.loadAllAirports(idMap);
	}
	
	public List<Airline> getAirlines() {
		return dao.loadAllAirlines();
	}
	
	public void creaGrafo(Airline airline) {
		
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		
		//Aggiungo vertici
		Graphs.addAllVertices(this.grafo, aereoporti);
		
		//Aggiungo archi
		tratte = dao.getArchi(airline, idMap);
		tratte2 = new LinkedList<>(tratte);
		
		for (Tratta t : tratte) {
			
			//Il grafo deve contenere quei vertici		
			Airport a1 = t.getId1();
			Airport a2 = t.getId2();
			double peso = t.getPeso();
			
			if ( this.grafo.containsVertex(a1) && this.grafo.containsVertex(a2)) Graphs.addEdge(this.grafo, a1, a2, peso);
			else tratte2.remove(t);
		}
		
		System.out.println("GRAFO CREATO!");
		System.out.println("#NODI: "+this.grafo.vertexSet().size());
		System.out.println("#ARCHI: "+this.grafo.edgeSet().size()+"\n");
	}
	
	public List<Tratta> getPesoMassimo(){
		
		List<Tratta> result = new ArrayList<>();	
		Collections.sort(tratte2);
		result = tratte2.subList(0, 10);
		
		return result;
	}
	
}
