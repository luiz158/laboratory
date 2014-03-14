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
		label : "ID"
	    }, {
		key : "description",
		label : "Descrição",
		formatter : "<a href='bookmark-edit.html?id={id}'>{value}</a>"
	    }, {
		key : "link",
		label : "Link",
		formatter : "<a href='{value}'>{value}</a>"
	    } ],
	    data : data
	});

	table.render("#resultList");
    });
}

function removeOk(data) {
    $.each(data, function(index, value) {
	table.removeRow(value);
    });
}
