package tv.xiaocong.video.restful;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.fastser.dal.descriptor.QueryResult;
import org.fastser.util.XmlUtil;
import org.fastser.web.context.RestRequest;
import org.fastser.web.context.RestResponse;
import org.fastser.web.exception.ValidateException;
import org.fastser.web.rest.annotation.RestMapping;
import org.fastser.web.rest.annotation.RestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tv.xiaocong.video.dto.FilmOrder;
import tv.xiaocong.video.dto.RecIndexType;
import tv.xiaocong.video.dto.ServerFilm;
import tv.xiaocong.video.message.Messages;
import tv.xiaocong.video.service.IFilmAuthorizeService;
import tv.xiaocong.video.service.IFilmOrderService;
import tv.xiaocong.video.service.IFilmService;

@Component
@RestMapping("film")
public class FilmRestMapping  {
	

	@Autowired
    private IFilmService filmService;
	
	@Autowired
	private IFilmOrderService filmOrderService;
	
	@Autowired
	private IFilmAuthorizeService filmAuthorizeService;

	@RestValidator(value={"selectRecomme","selectAll","select"}, method = {"get"} , version = {"1"})
    public void validateRecommend(RestRequest request, RestResponse response) {
		
		int serverId = request.getParameterInt(RecIndexType.SERVER_ID);
		if(serverId < 1){
			throw new ValidateException(Messages.getString("ValidateError.1", RecIndexType.SERVER_ID));
		}
		response.put(RecIndexType.SERVER_ID, serverId);
    }
	
	@RestValidator(value = { "select" }, method = { "get" }, version = { "1" }, beforeName="validateRecommend")
	public void validateSelect(RestRequest request, RestResponse response) {

		String filmNO = request.getParameterString(ServerFilm.FILM_NO);
		if (StringUtils.isEmpty(filmNO)) {
			throw new ValidateException(Messages.getString("ValidateError.1", ServerFilm.FILM_NO));
		}
		response.put(ServerFilm.FILM_NO, filmNO);
	}
	
    @RestMapping(value = "selectRecomme", method = {"get"} , version = {"1"})
    public void getRecommend(RestRequest request, RestResponse response) {
    	Map<String, Object> queryResult = filmService.getRecommendedFilm(response.getInt(RecIndexType.SERVER_ID), request.getPage());
    	response.putResult(queryResult);
    }
    
    @RestMapping(value = "selectAll", method = {"get"} , version = {"1"})
    public void getSelectAll(RestRequest request, RestResponse response) {
    	QueryResult queryResult = filmService.getServerFilmList(response.getInt(ServerFilm.SERVER_ID), 
    			request.getParameterString("titlepy"), request.getParameterString("produceYear"),
    			request.getParameterString("clime"), request.getParameterString("mtype"), request.getPage());
    	response.putResult(queryResult);
    	response.hiddenDisplayFields(new String[]{"series","rightEnd","filmid","rightStart","seriesIndex",
    			"id","serverId","pubDate","status","frunTime","debutnational","seriesName","addTime"});
    }
    
    @RestMapping(value = "select", method = {"get"} , version = {"1"})
    public void getSelect(RestRequest request, RestResponse response) {
    	int userId = request.getParameterInt("userId");
    	QueryResult queryResult = filmService.getServerFilm(response.getInt(ServerFilm.SERVER_ID), 
    			response.getString(ServerFilm.FILM_NO), userId);
    	response.putResult(queryResult.get());
    	response.hiddenDisplayFields(new String[]{"relate.series","relate.rightEnd","relate.filmid","relate.rightStart","relate.seriesIndex",
    			"relate.id","relate.serverId","relate.pubDate","relate.status","relate.frunTime","relate.debutnational","relate.seriesName","relate.addTime"});
    	response.hiddenDisplayFields(new String[]{"film.series","film.rightEnd","film.filmid","film.rightStart","film.seriesIndex",
    			"film.id","film.serverId","film.pubDate","film.status","film.frunTime","film.debutnational","film.seriesName","film.addTime"});
    }

    @RestValidator(value = { "playReq" }, method = { "post" }, version = { "1" })
	public void validatePlayReq(RestRequest request, RestResponse response) {
    	Integer userId = request.getParameterInt("userId");
    	if (userId == null) {
    		throw new ValidateException(Messages.getString("ValidateError.1", "userId"));
		}
    	String orderNum = request.getParameterString("orderNum");
    	if (StringUtils.isBlank(orderNum)) {
    		throw new ValidateException(Messages.getString("ValidateError.1", "orderNum"));
		}
    	int orderType = request.getParameterInt("orderType");
    	String filmNo = request.getParameterString("filmNo");
    	if (orderType == 0 ) {
			throw new ValidateException(Messages.getString("ValidateError.1", "orderType"));
		}else if(orderType == 2) {
			if (StringUtils.isEmpty(filmNo)) {
				throw new ValidateException(Messages.getString("ValidateError.1", "filmNo"));
			}
		}
    	
		if (StringUtils.isEmpty(filmNo)) {
			throw new ValidateException(Messages.getString("ValidateError.1", "filmNo"));
		}

	}
    @RestMapping(value = "playReq",method = {"post"} ,version = {"1"})
    public void playReq(RestRequest request,RestResponse response){
    	Integer userId = request.getParameterInt("userId");
    	String orderNum = request.getParameterString("orderNum");
    	Integer orderType = request.getParameterInt("orderType");
    	String filmNo = request.getParameterString("filmNo");
    	FilmOrder filmOrder = filmOrderService.selectByNoAndType(orderNum, orderType, userId);
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		//判断订单是否已经同步
    		if (filmOrder.getRsynctime() == null) {
    			Calendar calendar = Calendar.getInstance();
				filmOrder.setRsynctime(calendar.getTime());
				filmOrder.setStarttime(calendar.getTime());
				if (filmOrder.getOrdertype() == 1) {
					calendar.add(Calendar.DATE, 2);
					filmOrder.setEndtime(calendar.getTime());
				}
				if (filmOrder.getOrdertype() == 2) {
					calendar.add(Calendar.DATE, 31);
					filmOrder.setEndtime(calendar.getTime());
				}
				Object obj = filmAuthorizeService.rsynsOrderReq(filmOrder);
				if (obj != null && filmOrder.getOrdertype() == 1) {
					if (filmOrder.getOrdertype() == 1 && "success".equalsIgnoreCase(obj.toString())) {
						filmOrderService.updateByPrimaryKey(filmOrder);
					} else {
						Map<String, Object> result = XmlUtil.getMapFromXmlString(obj.toString());
						Object message = result.get("message");
						if (message != null && "success".equals(message)) {
							filmOrderService.updateByPrimaryKey(filmOrder);
						}
					}
				}
			}
			
			if (orderType == 1) {
				Object res = filmAuthorizeService.playVideoReq(filmOrder, filmOrder.getFilmno());
				map = XmlUtil.getMapFromXmlString(res.toString());
				response.putResult(map);
			} else {
				Object res = filmAuthorizeService.playVideoReq(filmOrder, filmNo);
				map = XmlUtil.getMapFromXmlString(res.toString());
				response.putResult(map);
			}
		} catch (Exception e) {
			map.put("code", 104);
			map.put("message", "fail");
			response.putResult(map);
		}
    }

}
