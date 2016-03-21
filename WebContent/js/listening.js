/**
 * listening.js
 */


var tmp;

function init() {
	if (tmp !== undefined) {
		tmp.pause();
	}
}


function play(id) {
	init();
	var target = document.getElementById(id);
	tmp = target;
	target.volume = 0.9;
	target.play();
	timeupdate(id);
}

function timeupdate(id) {
	var target = document.getElementById(id);
	target.addEventListener("timeupdate", function() {
		var now = Math.floor(target.currentTime);
		if (now === 5) {
			target.volume = 0.7;
		}
		if (now === 6) {
			target.volume = 0.5;
		}
		if (now === 7) {
			target.volume = 0.3;
		}
		if (now === 8) {
			target.volume = 0.2;
		}
		if (now === 9) {
			target.volue = 0.1;
		}
		if (now === 10) {
			target.pause();
			target.currentTime = 0;
		}

	}, true);
}