$(function() {
	$("#new").focus();

	$(document).ready(function() {
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

function findAllOk(data) {
	var oTable = $('#resultList').dataTable(
			{
				"aoColumns" : [
						{
							"aTargets" : [ 0 ],
							"mData" : "id",
							"mRender" : function(data) {
								return '<input id="remove-' + data
										+ '" type="checkbox" value="' + data
										+ '">';
							}
						}, {
							"aTargets" : [ 1 ],
							"mData" : "description"
						}, {
							"aTargets" : [ 2 ],
							"mData" : "link"
						} ],
				// "aaData" : data,
				// "bSort" : true,
				"oLanguage" : {
					"sInfo" : "Mostrando _START_ a _END_ de _TOTAL_ registros",
				},
				"bRetrieve" : true,
				//"bDestroy" : true,
				"bServerSide" : true,
				//"sPaginationType" : "full_numbers",
				"sPaginationType": "bs_normal",
				"sAjaxSource" : 'api/bookmark/datatables',
				"bSort" : false
			});
	oTable.fnClearTable();
}

function removeOk(data) {
	$.each(data, function(index, value) {
		// table.removeRow(value);
		findAllOk();
	});
}
