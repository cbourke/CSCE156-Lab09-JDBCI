package unl.cse.albums;

import java.util.List;

public class Test {

	public static void main(String args[]) {

		List<Album> disc = Album.getAlbumSummaries();

		for(Album a : disc) {
			System.out.println(a.getTitle() + " by " + a.getBand().getName() + ", " + a.getYear());
		}
		
	}
}
