/**
 * @Copyright@xiaocong.tv 2014
 */
package tv.xiaocong.video.service.impl;

import java.util.List;

import org.fastser.dal.core.BaseDAL;
import org.fastser.dal.criteria.QueryCriteria;
import org.fastser.dal.descriptor.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tv.xiaocong.video.dto.FilmOrder;
import tv.xiaocong.video.service.IFilmOrderService;

/**
 * @author: xianlai.zhang
 * @date: 2014年12月16日
 * @version: 1.0.0 <a href="mailto:zxl@xiaocong.tv">联系邮箱:zxl@xiaocong.tv</a>
 */
@Service
public class FilmOrderService implements IFilmOrderService {
	
	@Autowired
    private BaseDAL baseDAL;
	
	@Override
	public Integer updateByPrimaryKey(FilmOrder filmOrder) {
		return baseDAL.updateByPrimaryKey(filmOrder);
	}

	@Override
	public Integer updateByCriteria(FilmOrder filmOrder) {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setTable(FilmOrder.class);
		QueryCriteria.Criteria criteria = queryCriteria.createCriteria();
		criteria.andColumnEqualTo(FilmOrder.PAY_TIME, filmOrder.getPay());
		criteria.andColumnEqualTo(FilmOrder.ORDER_NUM, filmOrder.getOrdernum());
		criteria.andColumnEqualTo(FilmOrder.USER_ID, filmOrder.getUserid());
		criteria.andColumnEqualTo(FilmOrder.RSYNC_TIME, filmOrder.getRsynctime());
		criteria.andColumnEqualTo(FilmOrder.START_TIME, filmOrder.getStarttime());
		criteria.andColumnEqualTo(FilmOrder.END_TIME, filmOrder.getEndtime());
		FilmOrder model = new FilmOrder();
		model.setRsynctime(filmOrder.getRsynctime());
		model.setStarttime(filmOrder.getStarttime());
		model.setEndtime(filmOrder.getEndtime());
		return baseDAL.updateByCriteria(model, queryCriteria);
	}

	@Override
	public FilmOrder selectByNoAndType(String orderNum,Integer orderType,Integer userId) {
		QueryCriteria queryCriteria = new QueryCriteria();
		queryCriteria.setTable(FilmOrder.class);
		QueryCriteria.Criteria criteria = queryCriteria.createCriteria();
		criteria.andColumnEqualTo(FilmOrder.ORDER_NUM, orderNum);
		criteria.andColumnEqualTo(FilmOrder.ORDER_TYPE, orderType);
		criteria.andColumnEqualTo(FilmOrder.USER_ID, userId);
		QueryResult result = baseDAL.selectByCriteria(queryCriteria,BaseDAL.NO_CACHE);
		if (result != null) {
			List<FilmOrder> filmOrders = result.asList(FilmOrder.class);
			return (filmOrders != null && !filmOrders.isEmpty()) ? filmOrders.get(0) : null;
		}
		return null;
	}

}
