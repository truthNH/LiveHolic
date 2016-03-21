//入力されたキーワードチェック
function checkForm() {
	var val = document.keywordSearch.keyword.value;
	if (val === "" || val.length < 2) {
		return false;
	} else {
		if (val.indexOf('<script>') != -1) {
			return false;
		} else {
			return true;
		}
	}
}