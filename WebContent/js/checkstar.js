/**
 * 5段階評価(star)の選択
 */
$(function() {
	var star00 = '<img src="./images/star-no.png" alt="" width="24" height="24"/>';
    //var star00 = '☆';
    var star01 = '<img src="./images/star.png" alt="" width="24" height="24"/>';
    //var star01 = '★';
    var rank = 0;
    writeStar();
    $('#stars a').live('click', function() {
	rank = $('#stars a').index(this) + 1;
	writeStar();
    });
    function writeStar() {
	$('#stars').empty();
	for (var i = 0; i < 5; i++) {
	    var star = (i < rank) ? star01 : star00;
	    $('<a href="#"></a>').html(star).appendTo($('#stars'));
	}
	$("#star").val(rank);
    }
});

