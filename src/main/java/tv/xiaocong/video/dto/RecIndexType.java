package tv.xiaocong.video.dto;

import java.io.Serializable;


public class RecIndexType  implements Serializable {
	
	private static final long serialVersionUID = -6139748470933227996L;
	
	public static final String ID = "id";
	public static final String SORT = "sort";
	public static final String NAME = "name";
	public static final String IDENTIFIER = "identifier";
	public static final String SERVER_ID = "serverId";
	public static final String STATUS = "status";
	public static final String TYPE_LEVEL = "typeLevel";
	public static final String PARENT_ID = "parentId";

	private Integer id;
	
	private String name;

    private Integer sort;

    private Byte status;

    private Integer serverId;

    private String description;
    
    private Integer parentId;
    
    private Integer typeLevel;
    
    private String bigpic;

    private String littlepic;
    
    private String identifier;

    private Integer version;
    
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

	public Integer getTypeLevel() {
		return typeLevel;
	}

	public void setTypeLevel(Integer typeLevel) {
		this.typeLevel = typeLevel;
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}