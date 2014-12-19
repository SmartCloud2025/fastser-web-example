/**
 * @Copyright@xiaocong.tv 2014
 */
package tv.xiaocong.video.service;

import tv.xiaocong.video.dto.FilmOrder;

/**
 * @author: xianlai.zhang
 * @date: 2014年12月16日
 * @version: 1.0.0 <a href="mailto:zxl@xiaocong.tv">联系邮箱:zxl@xiaocong.tv</a>
 */
public interface IFilmOrderService {
	
	public Integer updateByPrimaryKey(FilmOrder filmOrder);
	
	public Integer updateByCriteria(FilmOrder filmOrder);
	
	public FilmOrder selectByNoAndType(String orderNum,Integer orderType,Integer userId);
}
