/**
 * チェックボックの全選択/全選択加除
 */

var flg = true;

function checkBox() {
	if (flg) {
		var check = document.form1.cart.checked;

		for (var count = 0; count < document.form1.cart.length; count++) {
			document.form1.cart[count].checked = true;
		}
		flg = false;
	} else {
		for (var count = 0; count < document.form1.cart.length; count++) {
			document.form1.cart[count].checked = false;
		}
		flg = true;
	}
}
