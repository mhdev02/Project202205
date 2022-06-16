if (hasJwtExpired == 'true' || userId.length == 0) {
	document.cookie = "jwt" + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	token = "";
}

if (token.length > 0) {
	document.getElementById('item').setAttribute("style", "display: block !important;");
	document.getElementById('youritem').setAttribute("style", "display: block !important;");
	document.getElementById('logout').setAttribute("style", "display: block !important;");
} else {
	document.getElementById('signIn').removeAttribute("style");
	document.getElementById('signUp').removeAttribute("style");
}

function sendReq(method, itemId, redirectUrl) {

	let name = $("#name").val();
	let price = $("#price").val();
	let quantity = $("#quantity").val();
	let description = $("#description").val();
	let param = { name, price, "stock": quantity, description }

	if (isNaN(parseInt(price))) {
		$('#warn').text("Price should be number");
		return;
	}
	if (isNaN(parseInt(quantity))) {
		$('#warn').text("Quantity should be number");
		return;
	}

	$.ajax({
		type: method,
		data: JSON.stringify(param),
		url: `/items/${itemId}`,
		contentType: "application/json",
	}).done(function(res) {

		itemId = res.itemId;
		let data = new FormData();
		data.append("file", $("input[name=file]")[0].files[0]);

		if (data.get("file") == "undefined") {
			window.location.href = redirectUrl;
			return;
		}

		if (method == "PUT") {
			if (!imageId) saveImage("POST", data, itemId, "/seller");
			else saveImage("PUT", data, imageId, "/seller");
		} else saveImage("POST", data, itemId, "/");

	})
}

function saveImage(method, data, id, redirectUrl) {

	$.ajax({
		type: method,
		enctype: 'multipart/form-data',
		data: data,
		url: `/images/${id}`,
		processData: false,
		contentType: false,
		cache: false,
	}).done(function(output, status, xhr) {
		window.location.href = redirectUrl;
	})
}