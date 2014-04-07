$(function() {
	$("form").submit(function(event) {
		var form = {
			username : $("#username").val(),
			password : $("#password").val()
		};

		AuthProxy.login(form);

		return false;
	});
});
