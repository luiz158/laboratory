$(function() {
    $(document).ready(function() {
	BookmarkProxy.findAll(findAllOk);
    });
});

function findAllOk(data) {
    $('#resultList').dataTable({
	"aaData" : data,
	"aoColumns" : [ {
	    "mDataProp" : "id"
	}, {
	    "mDataProp" : "description"
	}, {
	    "mDataProp" : "link"
	} ]
    });
}
