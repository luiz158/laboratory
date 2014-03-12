var BookmarkProxy = {};

BookmarkProxy.url = "api/bookmark";

BookmarkProxy.findAll = function($success, $error) {
    $.ajax({
	type : "GET",
	url : this.url,
	success : function(data) {
	    if ($success) {
		$success(data);
	    }
	},
	error : function(request) {
	    if ($error) {
		$error(request);
	    }
	}
    });
};

BookmarkProxy.load = function($id, $success, $error) {
    $.ajax({
	type : "GET",
	url : this.url + "/" + $id,
	success : function(data) {
	    if ($success) {
		$success(data);
	    }
	},
	error : function(request) {
	    if ($error) {
		$error(request);
	    }
	}
    });
};

BookmarkProxy.insert = function($form, $success, $error) {
    $.ajax({
	type : "POST",
	url : this.url,
	data : JSON.stringify($form),
	contentType : "application/json",
	success : function(data) {
	    if ($success) {
		$success(data);
	    }
	},
	error : function(request) {
	    if ($error) {
		$error(request);
	    }
	}
    });
};

BookmarkProxy.update = function($id, $form, $success, $error) {
    $.ajax({
	type : "POST",
	url : this.url + "/" + $id,
	data : JSON.stringify($form),
	contentType : "application/json",
	success : function(data) {
	    if ($success) {
		$success(data);
	    }
	},
	error : function(request) {
	    if ($error) {
		$error(request);
	    }
	}
    });
};

BookmarkProxy.remove = function($id, $success, $error) {
    $.ajax({
	type : "DELETE",
	url : this.url + "/" + $id,
	success : function(data) {
	    if ($success) {
		$success(data);
	    }
	},
	error : function(request) {
	    if ($error) {
		$error(request);
	    }
	}
    });
};
