package tv.xiaocong.video.service.impl;

import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.fastser.dal.core.BaseDAL;
import org.fastser.dal.criteria.QueryCriteria;
import org.fastser.dal.criteria.QueryCriteria.Criteria;
import org.fastser.dal.descriptor.QueryResult;
import org.fastser.util.es.ElasticSearchFactory;
import org.fastser.web.context.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.List;

import tv.xiaocong.video.dto.FilmOrder;
import tv.xiaocong.video.dto.RecGame;
import tv.xiaocong.video.dto.RecIndexType;
import tv.xiaocong.video.dto.ServerFilm;
import tv.xiaocong.video.service.IFilmService;

@Service
public class ESFilmService implements IFilmService{
	
	@Autowired
    private BaseDAL baseDAL;

	public Map<String, Object> getRecommendedFilm(int serverId, Page page) {
		Map<String, Object> rt = new HashMap<String, Object>();
		QueryCriteria queryCriteria = new QueryCriteria();
        queryCriteria.setTable(RecIndexType.class);
        Criteria critera = queryCriteria.createCriteria();
        critera.andColumnEqualTo(RecIndexType.IDENTIFIER, IFilmService.SHOUYTTUIJIAN_IDENTIFIER);
        critera.andColumnEqualTo(RecIndexType.SERVER_ID, serverId);
        critera.andColumnEqualTo(RecIndexType.STATUS, 1);
        queryCriteria.setSelectOne(true);
        QueryResult result =  baseDAL.selectByCriteria(queryCriteria);
        RecIndexType recIndexType = result.as(RecIndexType.class);
        if(null != recIndexType){
        	queryCriteria = new QueryCriteria();
            queryCriteria.setTable(RecGame.class);
            critera = queryCriteria.createCriteria();
            critera.andColumnEqualTo(RecGame.REC_ID, recIndexType.getId());
            critera.andColumnEqualTo(RecGame.STATUS, 1);
            queryCriteria.setOrderByClause(RecGame.SORT+" desc");
            if(null != page){
            	queryCriteria.setPageIndex(page.getPageIndex());
            	queryCriteria.setPageSize(page.getPageSize());
            	result =  baseDAL.selectPageByCriteria(queryCriteria);
            }else{
            	result =  baseDAL.selectByCriteria(queryCriteria);
            }
            rt.put("category", result.getList());
            return rt;
        }
		return null;
	}

	public QueryResult getServerFilmList(int serverId, String titlepy, String produceYear, String clime, String mtype, Page page) {
		
		
		String query = "{\"query\": { \"match_all\": {} }}";
		 
		 SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		 searchSourceBuilder.query(QueryBuilders.matchQuery(ServerFilm.STATUS, 1));
		 if(StringUtils.isNotEmpty(titlepy)){
			 searchSourceBuilder.query(QueryBuilders.moreLikeThisFieldQuery(ServerFilm.PRODUCE_YEAR)
					 .likeText(titlepy).minTermFreq(1).maxQueryTerms(page.getPageSize()));
	     }
		 if(StringUtils.isNotEmpty(produceYear)){
			 searchSourceBuilder.query(QueryBuilders.matchQuery(ServerFilm.PRODUCE_YEAR, produceYear));
	     }
		 if(StringUtils.isNotEmpty(titlepy)){
			 searchSourceBuilder.query(QueryBuilders.moreLikeThisFieldQuery(ServerFilm.PRODUCE_YEAR)
					 .likeText(titlepy).minTermFreq(1).maxQueryTerms(page.getPageSize()));
	     }
		 if(StringUtils.isNotEmpty(clime)){
			 searchSourceBuilder.query(QueryBuilders.matchQuery(ServerFilm.CLIME, clime));
	     }
		 if(StringUtils.isNotEmpty(mtype)){
			 searchSourceBuilder.query(QueryBuilders.moreLikeThisFieldQuery(ServerFilm.PRODUCE_YEAR)
					 .likeText(titlepy).minTermFreq(1).maxQueryTerms(page.getPageSize()));
	     }
		 



		Search search = new Search.Builder(searchSourceBuilder.toString())
		                .addIndex("serverfilm")
		                .addIndex("889")
		                .build();

		try {
			SearchResult result = ElasticSearchFactory.getJestClient().execute(search);
			result.getSourceAsObject(List.class);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		QueryCriteria queryCriteria = new QueryCriteria();
        queryCriteria.setTable(ServerFilm.class);
        Criteria critera = queryCriteria.createCriteria();
        critera.andColumnEqualTo(ServerFilm.SERVER_ID, serverId);
        critera.andColumnEqualTo(ServerFilm.STATUS, 1);
        if(StringUtils.isNotEmpty(titlepy)){
        	critera.andColumnLike(ServerFilm.TITLEPY, titlepy.toUpperCase()+"%");
        }
        if(StringUtils.isNotEmpty(produceYear)){
        	critera.andColumnEqualTo(ServerFilm.PRODUCE_YEAR, produceYear);
        }
        if(StringUtils.isNotEmpty(clime)){
        	critera.andColumnEqualTo(ServerFilm.CLIME, clime);
        }
        if(StringUtils.isNotEmpty(mtype)){
        	critera.andColumnLike(ServerFilm.TYPE, "%"+mtype+"%");
        }
        queryCriteria.setOrderByClause(ServerFilm.PUB_DATE+" desc");
        QueryResult result =  null;
        if(null != page){
        	queryCriteria.setPageIndex(page.getPageIndex());
        	queryCriteria.setPageSize(page.getPageSize());
        	result =  baseDAL.selectPageByCriteria(queryCriteria);
        }else{
        	result =  baseDAL.selectByCriteria(queryCriteria);
        }
		return result;
	}

	@Override
	public QueryResult getServerFilm(int serverId, String filmNO, int userId) {
		QueryCriteria queryCriteria = new QueryCriteria();
        queryCriteria.setTable(ServerFilm.class);
        Criteria critera = queryCriteria.createCriteria();
        critera.andColumnEqualTo(ServerFilm.SERVER_ID, serverId);
        critera.andColumnEqualTo(ServerFilm.STATUS, 1);
        critera.andColumnEqualTo(ServerFilm.FILM_NO, filmNO);
        queryCriteria.setSelectOne(true);
        QueryResult result =  baseDAL.selectByCriteria(queryCriteria);
        ServerFilm film = result.as(ServerFilm.class);
        if(null != film){
        	Page page = new Page(1, 6);
        	result = getServerFilmList(serverId, null, film.getProduceYear(), film.getClime(), null, page);
        	Map<String, Object> rt = new HashMap<String, Object>();
        	rt.put("film", film);
        	rt.put("relate", result.getList());
        	
        	if(userId > 0){
            	Date now = new Date();
            	queryCriteria = new QueryCriteria();
                queryCriteria.setTable(FilmOrder.class);
                critera = queryCriteria.createCriteria();
                critera.andColumnEqualTo(FilmOrder.FILM_NO, filmNO);
                critera.andColumnEqualTo(FilmOrder.USER_ID, userId);
                critera.andColumnEqualTo(FilmOrder.ORDER_TYPE, 1);
                critera.andColumnIsNotNull(FilmOrder.RSYNC_TIME);
                critera.andColumnGreaterThanOrEqualTo(FilmOrder.START_TIME, now);
                critera.andColumnLessThanOrEqualTo(FilmOrder.END_TIME, now);
                int count = baseDAL.countByCriteria(queryCriteria);
                if(count == 0){
                	queryCriteria = new QueryCriteria();
                    queryCriteria.setTable(FilmOrder.class);
                    critera = queryCriteria.createCriteria();
                    critera.andColumnEqualTo(FilmOrder.FILM_NO, filmNO);
                    critera.andColumnEqualTo(FilmOrder.USER_ID, userId);
                    critera.andColumnEqualTo(FilmOrder.ORDER_TYPE, 1);
                    critera.andColumnIsNull(FilmOrder.RSYNC_TIME);
                    count = baseDAL.countByCriteria(queryCriteria);
                }
                if(count == 0){
                	queryCriteria = new QueryCriteria();
                    queryCriteria.setTable(FilmOrder.class);
                    critera = queryCriteria.createCriteria();
                    critera.andColumnEqualTo(FilmOrder.USER_ID, userId);
                    critera.andColumnEqualTo(FilmOrder.ORDER_TYPE, 2);
                    critera.andColumnIsNotNull(FilmOrder.RSYNC_TIME);
                    critera.andColumnGreaterThanOrEqualTo(FilmOrder.START_TIME, now);
                    critera.andColumnLessThanOrEqualTo(FilmOrder.END_TIME, now);
                    count = baseDAL.countByCriteria(queryCriteria);
                }
                if(count == 0){
                	queryCriteria = new QueryCriteria();
                    queryCriteria.setTable(FilmOrder.class);
                    critera = queryCriteria.createCriteria();
                    critera.andColumnEqualTo(FilmOrder.USER_ID, userId);
                    critera.andColumnEqualTo(FilmOrder.ORDER_TYPE, 2);
                    critera.andColumnIsNull(FilmOrder.RSYNC_TIME);
                    count = baseDAL.countByCriteria(queryCriteria);
                }
                if(count > 0){
                	rt.put("payStatus", true);
                }else{
                	rt.put("payStatus", false);
                }
            }
        	
        	result.setResultMap(rt);
        }
        
        
		return result;
	}

}
