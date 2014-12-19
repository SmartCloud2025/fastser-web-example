/**
 * @Copyright@xiaocong.tv 2014
 */
package tv.xiaocong.video.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.fastser.util.HttpClientUtil;
import org.fastser.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tv.xiaocong.video.dto.FilmOrder;
import tv.xiaocong.video.service.IFilmAuthorizeService;

/**
 * @author: xianlai.zhang
 * @date: 2014年12月16日
 * @version: 1.0.0 <a href="mailto:zxl@xiaocong.tv">联系邮箱:zxl@xiaocong.tv</a>
 */
@Service
public class FilmAuthorizeService implements IFilmAuthorizeService {
	
	private static final Logger logger = LoggerFactory.getLogger(FilmAuthorizeService.class);
	
	private static final HttpClientUtil client = new HttpClientUtil();

	private static final String PARTNERID = "m1905hudong";
	
	private static final String PARTNER = "m1905";

	private static final String KEY = "16b04e300a1200fec9605c5da0cff7b4";

	// 点播影片信息同步接口
	private static final String ORDERBUY = "http://mutest.m1905.com/index.php/Index/orderBuy";

	// 包月影片信息同步接口
	private static final String MONTH = "http://mutest.m1905.com/index.php/Index/Monthlyreq";

	// 鉴权请求接口
	private static final String PLAY = "http://mutest.m1905.com/index.php/Index/Playreq";
	
	@Override
	public Object rsynsOrderReq(FilmOrder filmOrder) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String url = "";
		if (filmOrder.getOrdertype() == 2) {
			url = FilmAuthorizeService.MONTH;
			buildMonthReq(filmOrder, params);//构建包月订单同步的参数信息
		}else if (filmOrder.getOrdertype() == 1) {
			url = FilmAuthorizeService.ORDERBUY;
			buildPlayReq(filmOrder, params);//构建点播订单同步的参数信息
		} else {
			return null;
		}
		return client.sendPostRequest(url, params);
	}
	
	/**
	 * 构建同步点播订单信息的参数
	 * @param order
	 * @param map
	 * @throws Exception
	 */
	private void buildPlayReq(FilmOrder order,Map<String, Object> map) throws Exception{
		String partner = FilmAuthorizeService.PARTNER;
		String partnerid = FilmAuthorizeService.PARTNERID;
		String itemid = order.getFilmno();
		String itemname = order.getFilmname();
		String orderid = order.getOrdernum();
		String paidtime = String.valueOf(order.getStarttime().getTime());
		String userid = String.valueOf(order.getUserid());
		String price = String.valueOf(order.getPrice());
		String status = "2";//同步订单到m1905的订单默认都是成功的
		String paychannel = String.valueOf(order.getPaytype());
		StringBuffer sb = new StringBuffer();
		sb.append("itemid=").append(itemid);
		sb.append("&itemname=").append(itemname);
		sb.append("&orderid=").append(orderid);
		sb.append("&paidtime=").append(paidtime);
		sb.append("&partner=").append(partner);
		sb.append("&partnerid=").append(partnerid);
		sb.append("&paychannel=").append(paychannel);
		sb.append("&price=").append(price);
		sb.append("&status=").append(status);
		sb.append("&userid=").append(userid).append(FilmAuthorizeService.KEY);
		logger.error("准备同步单点订单请求参数:"+sb.toString());
		String sign = MD5Util.getMD5(sb.toString());
		logger.error("准备同步单点订单,签名的字符串为:"+sign);
		map.put("itemid", itemid);
		map.put("itemname", itemname);
		map.put("orderid", orderid);
		map.put("partnerid", partnerid);
		map.put("paychannel", paychannel);
		map.put("partner", partner);
		map.put("paidtime", paidtime);
		map.put("price", price);
		map.put("status", status);
		map.put("userid", userid);
		map.put("sign", sign);
	}
	
	/**
	 * 构建同步包月订单信息的参数
	 * @param order
	 * @param map
	 * @throws Exception
	 */
	private void buildMonthReq(FilmOrder order,Map<String, Object> map) throws Exception{
		String endtime = String.valueOf(order.getEndtime().getTime());
		String orderid = order.getOrdernum();
		String paidtime = String.valueOf(order.getStarttime().getTime());
		String partner = FilmAuthorizeService.PARTNER;
		String partnerid = FilmAuthorizeService.PARTNERID;
		String paychannel = String.valueOf(order.getPaytype());
		String price = String.valueOf(order.getPrice());
		String starttime = String.valueOf(order.getStarttime().getTime());
		String status = "2";//设置默认成功为2
		String userid = String.valueOf(order.getUserid());
		StringBuilder sb = new StringBuilder();
		sb.append("endtime=").append(endtime);
		sb.append("&orderid=").append(orderid);
		sb.append("&paidtime=").append(paidtime);
		sb.append("&partner=").append(partner);
		sb.append("&partnerid=").append(partnerid);
		sb.append("&paychannel=").append(paychannel);
		sb.append("&price=").append(price);
		sb.append("&starttime=").append(starttime);
		sb.append("&status=").append(status);
		sb.append("&userid=").append(userid).append(FilmAuthorizeService.KEY);
		logger.error("准备同步包月订单请求参数:"+sb.toString());
		String sign = MD5Util.getMD5(sb.toString());
		logger.error("准备同步包月订单,签名的字符串为:"+sign);
		map.put("endtime", endtime);
		map.put("orderid", orderid);
		map.put("paidtime", paidtime);
		map.put("partner", partner);
		map.put("partnerid", partnerid);
		map.put("paychannel", paychannel);
		map.put("price", price);
		map.put("sign", sign);
		map.put("starttime", starttime);
		map.put("status", status);
		map.put("userid", userid);
	}

	@Override
	public Object playVideoReq(FilmOrder filmOrder,String filmNo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String itemid = filmNo;
		String orderid = filmOrder.getOrdernum();
		String playtype = String.valueOf(filmOrder.getOrdertype());
		StringBuilder sb = new StringBuilder();
		sb.append("itemid=").append(itemid);
		sb.append("&orderid=").append(orderid);
		sb.append("&partnerid=").append(FilmAuthorizeService.PARTNERID);
		sb.append("&playtype=").append(playtype).append(FilmAuthorizeService.KEY);
		params.put("itemid", itemid);
		params.put("orderid",orderid);
		params.put("partnerid", FilmAuthorizeService.PARTNERID);
		params.put("playtype", playtype);
		logger.error("准备鉴权的播放请求参数:"+sb.toString());
		String sign = MD5Util.getMD5(sb.toString());
		params.put("sign", sign);
		logger.error("准备鉴权的播放请求,签名的字符串为:"+sign);
		return client.sendPostRequest(FilmAuthorizeService.PLAY, params);
	}
}
