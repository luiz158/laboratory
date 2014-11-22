$(function() {
	$("#new").focus();

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

		if (ids.length == 0) {
			alert('Nenhum registro selecionado');
		} else if (confirm('Tem certeza que deseja apagar?')) {
			BookmarkProxy.remove(ids, removeOk);
		}
	});
});

var table;

function findAllOk(data) {
	YUI().use("datatable", "datatable-paginator", function(Y) {

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
				formatter : "<a href='{value}' target='_blank'>{value}</a>"
			} ],
			data : data,
			rowsPerPage : 10
		});

		table.render("#resultList");
	});
}

function checkUncheckAll(sender) {
	$("input[type=checkbox]").each(function(index, value) {
		$(this).prop('checked', sender.checked);
	});
}

function removeOk(data) {
	$.each(data, function(index, value) {
		table.removeRow(value);
	});
}
