

$(document).ready(function() {
	
    $.ajax({
        type: 'GET',
        url: 'GetAlbums',
        contentType: "application/json",
        success: function(json) {
        	json.albums.forEach(function(album) {
        		var row = "<tr>" + 
        		          "<td><a href='albumDetail.jsp?albumId="+album.albumId+"'>" + album.title + "</a></td>" +
        		          "<td><a href='bandDetail.jsp?bandId="+album.band.bandId+"'>" + album.band.name + "</a></td>" +
        		          "<td>" + album.year + "</td>" +
        		          "</tr>";
        		$("#albumTable tbody").append(row);
        	});
        	$("#loading").empty();
        	$("#albumTable").hide().fadeIn("slow");
        },
        error: function(e) {
          $("#loading").empty();
          raiseError("There was a problem connecting to the server!");
        }
      });
	
});

function raiseError(msg) {
  var errorDiv = '<div class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><strong>ERROR!</strong> '+msg+'</div>';
  $('#errMsgArea').empty();
  $('#errMsgArea').append(errorDiv);
  $('#errMsgArea').hide().fadeIn("slow");
}
