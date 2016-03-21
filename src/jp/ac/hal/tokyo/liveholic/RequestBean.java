package jp.ac.hal.tokyo.liveholic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestBean {

	// パラメータ
	//顧客ID
	private int customer_id;
	//ライブ名
	private String live_name;
	//登録日付
	private String addDateTime;
	//リクエストカウント数
	private int reqCount;
	
	private boolean err;
	private String msg;
	
	
	
	
	private final int SUCCESS = 0;
	//live_name最大文字数
	private final int LIVENAME_MAX_LENGTH = 40;

	private final int LIVENAME_INPT_ERR = -11;
	private final int LIVENAME_MAX_LENGTH_ERR = -12;
	
	private final String LIVENAME_INPT_ERR_MSG = "ライブ名を入力してください。";
	private final String LIVENAME_MAX_LENGTH_ERR_MSG ="ライブ名は40文字以内で入力してください。";
	
	/*
	 * setter
	 */
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public void setLive_name(String live_name) {
		if(live_name == null || live_name.isEmpty())
		{
			this.setMsg(LIVENAME_INPT_ERR);
			this.setErr(true);
		}
		else if(live_name.length() > LIVENAME_MAX_LENGTH)
		{
			this.setMsg(LIVENAME_MAX_LENGTH_ERR);
			this.setErr(true);
		}
		else
		{
			this.live_name = live_name;
			this.setMsg(SUCCESS);
			this.setErr(false);
		}
		this.live_name = live_name;
	}
	
	public void setAddDateTime(Date addDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.addDateTime = sdf.format(addDate);
	}
	private void setErr(boolean err) {
		if(err == true)
		{
			this.err = err;
		}
	}
	
	private void setMsg(int errno)
	{
		this.msg="";
		if(errno == LIVENAME_INPT_ERR)
		{
			this.msg = LIVENAME_INPT_ERR_MSG;
		}
		else if(errno == LIVENAME_MAX_LENGTH_ERR)
		{
			this.msg = LIVENAME_MAX_LENGTH_ERR_MSG;
		}
	}
	
	public void setReqCount(int reqCount) {
		this.reqCount = reqCount;
	}

	/*
	 * getter
	 */
	public int getCustomer_id() {
		return customer_id;
	}

	public String getLive_name() {
		return live_name;
	}
	
	public String getAddDateTime()
	{
		return this.addDateTime;
	}
	
	public boolean getErr()
	{
		return this.err;
	}
	
	public String getMsg()
	{
		return this.msg;
	}
	
	public int getReqCount()
	{
		return this.reqCount;
	}
	
}