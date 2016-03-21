/*******************************************************************
 move-comment.js
 動画内でコメントを描写し移動させる
 *******************************************************************/

/** グロバール変数 **/
var commentData = [];
var tID = [];
var timer = 0;
var timerID = 0;
var delTimerID = 0;
var startY = 0;
var commentY = 0;
var commentR = 600;
var commentOffset = 30;
var commentBottom = 240;
var comment = "";
var video = "";

/** init() イニシャル処理 **/
function init() {
	video = document.getElementById("video");
	//コメントデータ読み込み
	commentData = textD;
	//タイマークリア
	if (timerID) {
		clearTimer(timerID);
	}
	if (delTimerID) {
		clearTimer(delTimerID);
	}
	tID = [];
	//telopのエレメント格納
	var tDiv = document.getElementById("telop");
	//telop内のdiv要素数をtListに格納
	var tList = tDiv.getElementsByTagName("div");
	//telop内の子ノードを全て消去
	while (tDiv.firstChild) {
		tDiv.removeChild(tDiv.firstChild);
	}
	//コメントのスタート座標をクリア
	commentY = startY;
	//ビデオの秒数リセット
	video.currentTime = 0;
}

/** playVodeo() ビデオ再生 **/
function playVideo() {
	var video = document.getElementById("video");
	if (video.paused) {
		video.play();
		document.getElementById("play").value = 'PAUSE';
	} else {
		video.pause();
		document.getElementById("play").value = 'PLAY';
		clearInterval(timerID);
	}
	//再生スタート
	//0.1秒毎コメントを動かす処理
	if (! video.paused) {
		timerID = setInterval(moveComment, 100);
	}
	//10秒毎コメント削除処理
	delTimerID = setInterval(delComment, 10000);
}

/** moveComment() コメントを動画内で動かす **/
function moveComment() {
	//コメント移動処理
	for (var i = 0 ; i < tID.length; i++) {
		//文字列除去
		var x = parseInt(document.getElementById(tID[i]).style.left);
		//xの座標が-1000以上ならコメントを移動させる
		if (x > -1000) {	
			document.getElementById(tID[i]).style.left = (x - 11) + "px";
			//コメント文字数によって流すスピードを可変させる
			if (document.getElementById(tID[i]).innerHTML.length <= 5) {
				document.getElementById(tID[i]).style.left = (x - 9) + "px";
			} else if (document.getElementById(tID[i]).innerHTML.length > 5 || document.getElementById(tID[i]).innerHTML.length < 10) {
				document.getElementById(tID[i]).style.left = (x - 11) + "px";
			} else {
				document.getElementById(tID[i]).style.left = (x - 13) + "px";
			}
		}
	}
	
	//コメントを追加するか確認
	for (var j = 0 ; j < commentData.length ; j++) {
		//既にコメントが追加されている場合ループ先頭へ
		if (checkComment(commentData[j].id) === true) {
			continue;
		}
		//コメントデータのsecがビデオ再生時間と同じになったとき
		if (getVideoTime() === commentData[j].sec) {
			//新しいdeiv要素生成
			var ele = document.createElement("div");
			//コメントのテキストを生成したdiv要素のinnerHTMLに追加
			ele.innerHTML = commentData[j].comment;
			//エレメントにクラス属性追加
			ele.className = "comment";
			//エレメント初期位置の設定
			ele.style.left = commentR + "px";
			ele.style.top = commentY + "px";
			//配列tIDに格納
			tID.push(ele.id = commentData[j].id);
			//idtelopにエレメントを追加
			document.getElementById("telop").appendChild(ele);
			//コメントのY座標をずらす
			commentY = commentY + commentOffset;
			//Y座標がbottomに到達した場合初期座標を初期値に
			if (commentY > commentBottom) {
				commentY = startY;
			}
		}	
	}
}

/** checkComment() コメントがtID配列に存在するかチェック　存在する場合trueを返す **/
function checkComment(checkID) {
	for (var i = 0 ; i < tID.length ; i++) {
		if (tID[i] == checkID) {
			return true;
		}
		return false;
	}
}

/** delComment() 流れ終わったコメントを削除する **/
function delComment() {
	var domObj = document.getElementById("telop");
	if (tID.length > 0) {
		for (var i = 0 ; i < tID.length; i++) {
			if (parseInt(document.getElementById(tID[i]).style.left) < -700) {
				domObj.removeChild(document.getElementById(tID[i]));
				tID.splice(i, 1);
			}
		}
	}
}

/** getText() 入力された値を受け取り動画内に流す **/
function getText() {
	//入力がないもしくはビデオが再生されていない場合処理を抜ける
	if (document.form.textArea.value === "" || document.getElementById("video").currentTime === 0 || document.form.textArea.value.indexOf('<script>') != -1) {
		return;
	} else {
		clearTimer(timer);
		comment = document.form.textArea.value;
	}
	//サーブレット送信メソッド呼び出し
	toServlet();
	document.getElementById("currentTelop").innerHTML = comment;
	document.getElementById("currentTelop").style.left = commentR + "px";
	document.getElementById("currentTelop").style.top = "270px";
	document.form.textArea.value = "";
	timer = setInterval(function(){
		var x = parseInt(document.getElementById("currentTelop").style.left);
		if (x > -500) {
			document.getElementById("currentTelop").style.left = (x - 10) + "px";
		} else {
			clearTimer(timer);
		}
	}, 100);
}

/** clearTimer(timer) 引数として受け取ったsetInterval()をクリア **/
function clearTimer(timer) {
	clearInterval(timer);
}

function reload() {
	init();
}

/** 現在の再生時間を小数第一位で返す **/
function getVideoTime() {
	var val = video.currentTime * 10;
	val = Math.floor(val);
	val /= 10;
	return val;
}

function jumpTitle(second) {
	tID = [];
	//telopのエレメント格納
	var tDiv = document.getElementById("telop");
	//telop内のdiv要素数をtListに格納
	var tList = tDiv.getElementsByTagName("div");
	//telop内の子ノードを全て消去
	while (tDiv.firstChild) {
		tDiv.removeChild(tDiv.firstChild);
	}
	video.currentTime = second;
}

/** 非同期でサーブレットにパラメータを送る処理 **/
function toServlet() {
	//コメントオブジェクト
	var cObj = {};
	//入力されたコメントをオブジェクトに格納
	cObj.comment = comment;
	//入力された時間をオブジェクトに格納
	cObj.sec = getVideoTime();
	//product_idをオブジェクトに格納
	cObj.product_id = product_id;
	
	jsonObj = $.toJSON(cObj);
	//サーブレットに送信
	$.post("/LIVE_HOLIC/VideoControllerServlet", {
		json : jsonObj
	});
}

window.onload = init;
