package jp.ac.hal.tokyo.admin;

public class AdminProductBean {
	
	//パラメータ設定
	private String live_id;
	private String live_name;
	private String arthist_name;
	private String genre_id;
	private String genre_name;
	private int venue_id;
	private String total_length;;
	private String live_date;
	private int label_id;
	private String product_introduction;
	private int total_track;
	private String venue_name;
	private String label_name;
	private String product_name;
	private String product_id;
	private String product_category;
	private String format;
	private String price;
	private String time;
	private String track_number;
	private String release_data;
	private int comment_num;
	private boolean err;
	private String errMsg;
	
	//定数宣言
	private final int LIVEID_LENGTH = 5;
	private final int LIVENAME_MAXLENGTH = 40;
	private final int VENUEID_MAXNUMBER = 9999;
	private final int TOTALTRACK_MAXNUMBER = 99;
	private final int VENUENAME_MAXLENGTH = 50;
	private final int LABELNAME_MAXLENGTH = 40;
	private final int ARTHISTNAME_MAXLENGTH = 40;
	private final int TOTALLENGTH_LENGTH = 8;
	private final int LIVEDATE_LENGTH = 10;
	
	//エラーメッセージ
	private final String LIVEID_ERRMSG = "ライブIDの入力形式が間違っています。";
	private final String LIVENAME_ERRMSG = "ライブ名の入力形式が間違っています。";
	private final String VENUEID_ERRMSG = "ライブ会場IDの入力形式が間違っています。";
	private final String VENUENAME_ERRMSG = "ライブ会場名の入力形式が間違っています。";
	private final String ARTHISTNAME_ERRMSG = "アーティスト名の入力形式が間違っています。";
	private final String LABELNAME_ERRMSG = "レコード会社の入力形式が間違っています。";
	private final String GENREID_ERRMSG = "ジャンルが選択されていません。";
	private final String TOTALLENGTH_ERRMSG = "ライブ時間の入力形式が間違っています。";
	private final String LIVEDATE_ERRMSG = "ライブ日付の入力形式が間違っています。";
	private final String PRODUCTINTRODUCTION_ERRMSG = "商品紹介が入力されていません。";
	private final String TOTALTRACK_ERRMSG = "曲数の入力形式が間違っています。";
	
	/*****************
	 * setter()メソッド
	 *****************/
	public void setLive_id(String live_id) {
		if (live_id.length() > LIVEID_LENGTH || live_id.isEmpty() || live_id == null) {
			setErr(true);
			setErrMsg(LIVEID_ERRMSG);
		} else {
			setErr(false);
			this.live_id = live_id;
		}
	}
	
	public void setLive_name(String live_name) {
		if (live_name.length() > LIVENAME_MAXLENGTH || live_name.isEmpty() || live_name == null) {
			setErr(true);
			setErrMsg(LIVENAME_ERRMSG);
		} else {
			setErr(false);
			this.live_name = live_name;
		}
	}
	
	public void setArthist_name(String arthist_name) {
		if (arthist_name.length() > ARTHISTNAME_MAXLENGTH || arthist_name.isEmpty() || arthist_name == null) {
			setErr(true);
			setErrMsg(ARTHISTNAME_ERRMSG);
		} else {
			setErr(false);
			this.arthist_name = arthist_name;
		}
	}

	public void setGenre_id(String genre_id) {
		if (genre_id.isEmpty() || genre_id == null) {
			setErr(true);
			setErrMsg(GENREID_ERRMSG);
		} else {
			setErr(false);
			this.genre_id = genre_id;
		}
	}
	
	public void setGenre_name(String genre_name) {
		setErr(false);
		this.genre_name = genre_name;
	}
	
	public void setVenue_id(int venue_id) {
		if (venue_id > VENUEID_MAXNUMBER) {
			setErr(true);
			setErrMsg(VENUEID_ERRMSG);
		} else {
			setErr(false);
			this.venue_id = venue_id;
		}
	}
	
	public void setTotal_length(String total_length) {
		if (total_length.length() != TOTALLENGTH_LENGTH || total_length.isEmpty() || total_length == null) {
			setErr(true);
			setErrMsg(TOTALLENGTH_ERRMSG);
		} else {
			setErr(false);
			this.total_length = total_length;
		}
	}
	
	public void setLabel_id(int label_id) {
		setErr(false);
		this.label_id = label_id;
	}
	
	public void setProduct_introduction(String product_introduction) {
		if (product_introduction.isEmpty() || product_introduction == null) {
			setErr(true);
			setErrMsg(PRODUCTINTRODUCTION_ERRMSG);
		} else {
			setErr(false);
			this.product_introduction = product_introduction;
		}
	}
	
	public void setTotal_track(int total_track) {
		if (total_track > TOTALTRACK_MAXNUMBER) {
			setErr(true);
			setErrMsg(TOTALTRACK_ERRMSG);
		} else {
			setErr(false);
			this.total_track = total_track;
		}
	}
	
	public void setVenue_name(String venue_name) {
		if (venue_name.length() > VENUENAME_MAXLENGTH || venue_name.isEmpty() || venue_name == null) {
			setErr(true);
			setErrMsg(VENUENAME_ERRMSG);
		} else {
			setErr(false);
			this.venue_name = venue_name;
		}
	}
	
	public void setLabel_name(String label_name) {
		if (label_name.length() > LABELNAME_MAXLENGTH || label_name.isEmpty() || label_name == null) {
			setErr(true);
			setErrMsg(LABELNAME_ERRMSG);
		} else {
			setErr(false);
			this.label_name = label_name;
		}
	}
	
	public void setLive_date(String live_date) {
		if (live_date.length() != LIVEDATE_LENGTH || live_date.isEmpty() || live_date == null) {
			setErr(true);
			setErrMsg(LIVEDATE_ERRMSG);
		} else {
			setErr(false);
			this.live_date = live_date;
		}
	}
	
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	
	private void setErr(boolean err) {
		this.err = err;
	}
	
	private void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	/*****************
	 * getter()メソッド
	 *****************/
	public String getLive_id() {
		return this.live_id;
	}
	
	public String getLive_name() {
		return this.live_name;
	}
	
	public String getArthist_name() {
		return this.arthist_name;
	}
	
	public String getGenre_id() {
		return this.genre_id;
	}
	
	public String getGenre_name() {
		return this.genre_name;
	}
	
	public int getVenue_id() {
		return this.venue_id;
	}
	
	public String getTotal_length() {
		return this.total_length;
	}
	
	public int getLabel_id() {
		return this.label_id;
	}
	
	public String getProduct_introduction() {
		return this.product_introduction;
	}
	
	public int getTotal_track() {
		return this.total_track;
	}
	
	public String getVenue_name() {
		return this.venue_name;
	}
	
	public String getLabel_name() {
		return this.label_name;
	}
	
	public String getLive_date() {
		return this.live_date;
	}
	
	public String getProduct_name() {
		return this.product_name;
	}
	
	public String getProduct_id() {
		return this.product_id;
	}
	
	public int getComment_num() {
		return comment_num;
	}

	public boolean isErr() {
		return this.err;
	}
	
	public String getErrMsg() {
		return this.errMsg;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	
	/*********
	 * 追記
	 *********/

	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTrack_number() {
		return track_number;
	}

	public void setTrack_number(String track_number) {
		this.track_number = track_number;
	}

	public String getRelease_data() {
		return release_data;
	}

	public void setRelease_data(String release_data) {
		this.release_data = release_data;
	}
}
