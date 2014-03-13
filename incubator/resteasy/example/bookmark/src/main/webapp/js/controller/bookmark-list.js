$(function() {
    $(document).ready(function() {
	BookmarkProxy.findAll(findAllOk);
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

	BookmarkProxy.remove(ids, removeOk);
    });
});

var table;

function findAllOk(data) {
    YUI().use("datatable", function(Y) {

	table = new Y.DataTable({
	    columns : [ {
		label : " ",
		formatter : "<input type='checkbox' value='{id}'>",
	    }, {
		key : "id",
		label : "ID",
		sortable : true
	    }, {
		key : "description",
		label : "Descrição",
		formatter : "<a href='bookmark-edit.html?id={id}'>{value}</a>",
		sortable : true
	    }, {
		key : "link",
		label : "Link",
		formatter : "<a href='{value}'>{value}</a>",
		sortable : true
	    } ],
	    data : data
	});

	table.render("#resultList");
    });
}

function removeOk(data) {
    console.log(data);
    table.removeRow(1);
}
