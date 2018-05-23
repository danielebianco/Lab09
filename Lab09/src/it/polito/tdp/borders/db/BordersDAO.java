package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryIdMap;

public class BordersDAO {

	public List<Country> loadAllCountries(CountryIdMap countryIdMap) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
			//	System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				Country c = new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				result.add(countryIdMap.get(c));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		String sql = "SELECT state1no, state2no " + 
				"FROM contiguity AS c " + 
				"WHERE year <= ? " +
				"AND c.conttype = 1 " +
				"AND c.state1no < c.state2no";
		List<Border> result = new ArrayList<Border>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
			//	System.out.format("%d - %d\n", rs.getInt("state1no"), rs.getInt("state2no"));
				Border border = new Border(rs.getInt("state1no"), rs.getInt("state2no"));
				result.add(border);
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	//	System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
	}
	
}
