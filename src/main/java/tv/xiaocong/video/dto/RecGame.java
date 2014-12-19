package tv.xiaocong.video.dto;

import java.io.Serializable;


public class RecGame  implements Serializable {
	
	private static final long serialVersionUID = 2223120523459041421L;
	
	public static final String REC_ID = "recId";
	public static final String STATUS = "status";
	public static final String SORT = "sort";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String GAME_ID = "gameId";
	public static final String BIGPIC = "bigpic";
	public static final String LITTLEPIC = "littlepic";
	public static final String VIDEOLINK = "videoLink";
	public static final String PKGNAME = "pkgName";
	public static final String TYPE = "type";
	
	private Integer id;
	
    private String name;

    private Integer sort;

    private Integer recId;

    private Integer gameId;

    private Byte status;

    private String bigpic;

    private String littlepic;

    private String videoLink;
    
    private String pkgName;
    
    private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getRecId() {
		return recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getBigpic() {
		return bigpic;
	}

	public void setBigpic(String bigpic) {
		this.bigpic = bigpic;
	}

	public String getLittlepic() {
		return littlepic;
	}

	public void setLittlepic(String littlepic) {
		this.littlepic = littlepic;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
}