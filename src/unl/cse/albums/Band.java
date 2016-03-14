package unl.cse.albums;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Band {

	private Integer bandId;
	private String name;
	private List<String> members = new ArrayList<String>();
	
	public Band(Integer bandId, String name) {
		super();
		this.bandId = bandId;
		this.name = name;
	}
	
	public Band(String name) {
		this(null, name);
	}

	public Integer getBandId() {
		return bandId;
	}

	public String getName() {
		return name;
	}

	public List<String> getMembers() {
		return members;
	}
	
	public void addMember(String name) {
		this.members.add(name);
	}
	
	/**
	 * This method returns a {@link #Band} instance loaded from the 
	 * database corresponding to the given <code>bandId</code>.  
	 * Throws an {@link IllegalStateException} upon an invalid <code>bandId</code>.
	 * All fields are loaded with this method.
	 * 
	 * @param bandId
	 * @return
	 */
	public static Band getBand(int bandId) {
		Band b = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.username, DatabaseInfo.password);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		

		String query = "SELECT name FROM Band where bandId = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, bandId);
			rs = ps.executeQuery();
			if(rs.next()) {
				b = new Band(bandId, rs.getString("name"));
			} else {
				throw new IllegalStateException("no such band with bandId = " + bandId);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		query = "SELECT firstName, lastName, country FROM BandMember bm JOIN Musician m ON bm.musicianId = m.musicianId WHERE bm.bandId = ?";
		
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, bandId);
			rs = ps.executeQuery();
			while(rs.next()) {
				b.addMember(rs.getString("lastName") + ", " + rs.getString("firstName"));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		try {
			if(rs != null && !rs.isClosed())
				rs.close();
			if(ps != null && !ps.isClosed())
				ps.close();
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return b;
	}

	@Override
	public String toString() {
		return "Band [bandId=" + bandId + ", name=" + name + ", members="
				+ members + "]";
	}
	
	
}
