var i = 0;

$(function() {
	console.log('carregado');

	var form = {
		description : 'teste ' + i,
		link : 'http://xxxxx_' + i
	};

	BookmarkProxy.insert(form, insertOk);
});

function insertOk(data) {
	console.log(data);
}
