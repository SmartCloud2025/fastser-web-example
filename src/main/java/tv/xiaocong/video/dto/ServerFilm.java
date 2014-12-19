package tv.xiaocong.video.dto;

import java.io.Serializable;
import java.util.Date;

public class ServerFilm implements Serializable {
	
	private static final long serialVersionUID = 125234295310967052L;
	
	public static final String SERVER_ID = "serverId";
	public static final String TITLEPY = "titlepy";
	public static final String PRODUCE_YEAR = "produceYear";
	public static final String CLIME = "clime";
	public static final String TYPE = "type";
	public static final String STATUS = "status";
	public static final String FILM_NO = "filmno";
	public static final String PUB_DATE = "pubDate";

	private Integer id;
	
    private Integer filmid;

    private String filmno;

    private String title;

    private String titlepy;

    private Integer series;

    private Integer seriesIndex;

    private String seriesName;

    private Date rightStart;

    private Date rightEnd;

    private String thumb;

    private Float grade;

    private String description;

    private String tags;

    private String type;

    private Date pubDate;

    private String direct;

    private String starring;

    private String produceYear;

    private Date frunTime;

    private String clime;

    private String debutnational;

    private Date addTime;

    private Date updateTime;

    private Integer duration;

    private Integer price;

    private Integer serverId;

    private Integer status;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFilmid() {
        return filmid;
    }

    public void setFilmid(Integer filmid) {
        this.filmid = filmid;
    }

    public String getFilmno() {
        return filmno;
    }

    public void setFilmno(String filmno) {
        this.filmno = filmno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlepy() {
        return titlepy;
    }

    public void setTitlepy(String titlepy) {
        this.titlepy = titlepy;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getSeriesIndex() {
        return seriesIndex;
    }

    public void setSeriesIndex(Integer seriesIndex) {
        this.seriesIndex = seriesIndex;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Date getRightStart() {
        return rightStart;
    }

    public void setRightStart(Date rightStart) {
        this.rightStart = rightStart;
    }

    public Date getRightEnd() {
        return rightEnd;
    }

    public void setRightEnd(Date rightEnd) {
        this.rightEnd = rightEnd;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getProduceYear() {
        return produceYear;
    }

    public void setProduceYear(String produceYear) {
        this.produceYear = produceYear;
    }

    public Date getFrunTime() {
        return frunTime;
    }

    public void setFrunTime(Date frunTime) {
        this.frunTime = frunTime;
    }

    public String getClime() {
        return clime;
    }

    public void setClime(String clime) {
        this.clime = clime;
    }

    public String getDebutnational() {
        return debutnational;
    }

    public void setDebutnational(String debutnational) {
        this.debutnational = debutnational;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
}