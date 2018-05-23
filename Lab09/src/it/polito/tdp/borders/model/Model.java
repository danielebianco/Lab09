package it.polito.tdp.borders.model;

import java.util.*;
import org.jgrapht.*;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	private Graph<Country,DefaultEdge> graph;
	private BordersDAO bdao;
	private List<Country> countries;
	private CountryIdMap countryIdMap;
	private int anno;
	
	public Model() {
		this.bdao = new BordersDAO();
		this.countryIdMap = new CountryIdMap();
		this.countries = bdao.loadAllCountries(countryIdMap);
		this.anno = 0;
	}
	
	public List<Country> getCountry() {
		if(this.countries == null) {
			return new ArrayList<Country>();
		}
		return this.countries;
	}
	
	public List<Country> createGraph(int anno) {
		this.anno = anno;
		this.graph = new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(this.graph, countries);
		addEdges(anno);
		getGrado();
		return countries;
	}

	private void addEdges(int anno) {
		BordersDAO dao = new BordersDAO();
		List<Border> borders = dao.getCountryPairs(anno);
		for (Border b : borders) {
			Country c1 = this.countryIdMap.get(b.getSt1());
			Country c2 = this.countryIdMap.get(b.getSt2());
			graph.addEdge(c1, c2);
		}
	}
	
	public int getNumberOfConnectedComponents() {
		if(graph!=null) {
			this.createGraph(anno);
		}
		ConnectivityInspector<Country,DefaultEdge> ci = new ConnectivityInspector<Country,DefaultEdge>(graph);
		return ci.connectedSets().size();
	}
	
	public void getGrado() {
		for(Country c : countries) {
			c.setNumConf(graph.degreeOf(c));	
		}
	}
	
	public Set<Country> trovaViciniDepthFirstIterator(Country country) {

		// trova il vertice di partenza
		Country start = countryIdMap.get(country);

		// visita il grafo
		Set<Country> visitati = new HashSet<>();
		DepthFirstIterator<Country, DefaultEdge> dfv = new DepthFirstIterator<>(this.graph, start);
		while (dfv.hasNext())
			visitati.add(dfv.next());

		return visitati;
	}
		
	public List<Country> getCountries() {
		return countries;
	}
	
}
