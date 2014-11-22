var AuthProxy = {};

AuthProxy.url = "api/auth";

AuthProxy.login = function($form, $success, $error) {
	// $.cookie("token", null);

	// $.removeCookie('token');

	$.ajax({
		type : "POST",
		url : this.url,
		headers : {
//			"Authorization" : "Basic " + btoa($form.username + ":" + $form.password)
			"Authorization" : "x"
		},
		data : JSON.stringify($form),
		contentType : "application/json",
		success : $success,
		error : $error
	});
};
