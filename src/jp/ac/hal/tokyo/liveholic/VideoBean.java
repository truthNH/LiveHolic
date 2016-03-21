package jp.ac.hal.tokyo.liveholic;

public class VideoBean {

	// パラメータ
	private int id;
	private float sec;
	private String comment;
	private String product_id;
	private boolean err;

	// コメント最大文字数
	private final int COMMENT_MAX_LENGTH = 50;

	/*
	 * setter
	 */
	public void setId(int id) {
		this.id = id;
	}

	public void setSec(float sec) {
		this.sec = sec;
	}

	public void setComment(String comment) {
		if (comment.length() > COMMENT_MAX_LENGTH || comment.equals("")) {
			setErr(true);
		} else {
			this.comment = comment;
		}
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	private void setErr(boolean err) {
		this.err = err;
	}

	/*
	 * getter
	 */
	public int getId() {
		return id;
	}

	public float getSec() {
		return sec;
	}

	public String getComment() {
		return comment;
	}

	public String getProduct_id() {
		return product_id;
	}

	public boolean isErr() {
		return err;
	}
}
