package tv.xiaocong.video.dto;

import java.io.Serializable;
import java.util.Date;

public class FilmOrder implements Serializable  {
	
	private static final long serialVersionUID = 6650265567868558463L;
	public static final String ID = "id";
	public static final String USER_ID = "userid";
	public static final String FILM_NO = "filmno";
	public static final String FILM_NAME = "filmname";
	public static final String START_TIME = "starttime";
	public static final String END_TIME = "endtime";
	public static final String RSYNC_TIME = "rsynctime";
	public static final String ORDER_TYPE = "ordertype";
	public static final String ORDER_NUM = "ordernum";
	public static final String PRICE = "price";
	public static final String PAY_TYPE = "paytype";
	public static final String PAY_TIME = "pay";
	
	private Integer id;
	
    private String ordernum;

    private String filmno;

    private String filmname;

    private Date starttime;

    private Date endtime;

    private Integer userid;

    private Integer ordertype;

    private Date pay;

    private Integer price;

    private Integer paytype;

    private Date rsynctime;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getFilmno() {
        return filmno;
    }

    public void setFilmno(String filmno) {
        this.filmno = filmno;
    }

    public String getFilmname() {
        return filmname;
    }

    public void setFilmname(String filmname) {
        this.filmname = filmname;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public Date getPay() {
        return pay;
    }

    public void setPay(Date pay) {
        this.pay = pay;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Date getRsynctime() {
        return rsynctime;
    }

    public void setRsynctime(Date rsynctime) {
        this.rsynctime = rsynctime;
    }
}