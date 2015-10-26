package unl.cse.albums;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlbumAdderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		String albumTitle  = request.getParameter("albumTitle");
		String bandName    = request.getParameter("bandName");  
		String albumYear   = request.getParameter("albumYear");
		String albumNumber = request.getParameter("albumNumber");

		boolean result = AlbumAdder.addAlbumToDatabase(albumTitle, bandName, albumYear, albumNumber);

		try {
			if(result) {
				response.sendRedirect("index.html");
			} else {
				response.sendRedirect("error.html");
			}
		} catch (Exception e) {
			
		}
	}
	
}
