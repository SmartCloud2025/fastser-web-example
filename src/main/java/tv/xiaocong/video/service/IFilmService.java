package tv.xiaocong.video.service;

import java.util.Map;

import org.fastser.dal.descriptor.QueryResult;
import org.fastser.web.context.Page;

public interface IFilmService {
	
	String SHOUYTTUIJIAN_IDENTIFIER = "shouyetuijian";
	
	Map<String, Object> getRecommendedFilm(int serverId, Page page);
	
	
	QueryResult getServerFilmList(int serverId, String titlepy, String produceYear, String clime, String mtype, Page page);
	
	
	QueryResult getServerFilm(int serverId, String filmNO, int userId);

}
