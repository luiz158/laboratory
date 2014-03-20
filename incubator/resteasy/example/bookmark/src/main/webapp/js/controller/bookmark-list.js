$(function() {
    $("#new").focus();

    $(document).ready(function() {
	// BookmarkProxy.findAll(findAllOk);
	findAllOk();
    });

    $("form").submit(function(event) {
	event.preventDefault();
    });

    $("#new").click(function() {
	location.href = "bookmark-edit.html";
    });

    $("#delete").click(function() {
	var ids = [];

	$("input:checked").each(function(index, value) {
	    ids.push($(value).val());
	});

	if (ids.length == 0) {
	    alert('Nenhum registro selecionado');
	} else if (confirm('Tem certeza que deseja apagar?')) {
	    BookmarkProxy.remove(ids, removeOk);
	}
    });
});

var table;

function findAllOk(data) {
    $('#resultList').dataTable({
	"aoColumns" : [ {
	    "mDataProp" : "id"
	}, {
	    "mDataProp" : "description"
	}, {
	    "mDataProp" : "link"
	} ],
	// "aaData" : data,
	"bServerSide" : true,
	"sAjaxSource" : 'api/bookmark/datatables'
    // ,
    // "fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
    // oSettings.jqXHR = $.ajax({
    // "dataType" : 'json',
    // "type" : "GET",
    // "url" : sSource,
    // "data" : aoData,
    // "success" : fnCallback
    // });
    // }
    });
}

function removeOk(data) {
    $.each(data, function(index, value) {
	table.removeRow(value);
    });
}
