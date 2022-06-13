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