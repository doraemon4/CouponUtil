package com.cn.stephen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * 生成优惠码工具类
 * 根据uuid生成的32位随机码
 * 取模生成结果返回16位字符串
 * @author Administrator
 *
 */
public class CouponUtil {
	public static String[] chars={
		"a","b","c","d","e","f","g","h","i","j",
		"k","l","m","n","o","p","q","r","s","t",
		"u","v","w","x","y","z","0","1","2","3",
		"4","5","6","7","8","9","A","B","C","D",
		"E","F","G","H","I","J","K","L","M","N",
		"O","P","Q","R","S","T","U","V","W","X",
		"Y","Z"
	};
	
	/**list去重每次检测花费大量时间
	 * 
	 * @param couponSize
	 * @return
	 */
	public static List<String> createCoupon(int couponSize){
		List<String> couponList=new ArrayList<String>();
		while(couponList.size()<couponSize){
			String uuid=UUID.randomUUID().toString().replaceAll("-","");
			StringBuffer buffer=new StringBuffer();
			for(int i=0;i<16;i++){
				String str = uuid.substring(i*2,i*2+2);
				int code=Integer.parseInt(str,16);
				buffer.append(chars[code%0x3E]);
			}
			if(!couponList.contains(buffer)){
				System.out.println(buffer.toString());
				couponList.add(buffer.toString());
			}
		}
		System.out.println(couponList.size());
		return couponList;
	}
	
	/**
	 * 使用HastSet存储(不需要校验重复)
	 * @param couponSize
	 * @return
	 */
	public static List<String> createCoupon2(int couponSize){
		HashSet<String> couponSet=new HashSet<String>();
		while(couponSet.size()<couponSize){
			String uuid=UUID.randomUUID().toString().replaceAll("-","");
			StringBuffer buffer=new StringBuffer();
			for(int i=0;i<16;i++){
				String str = uuid.substring(i*2,i*2+2);
				int code=Integer.parseInt(str,16);
				buffer.append(chars[code%0x3E]);
			}
			couponSet.add(buffer.toString());
		}
		System.out.println(couponSet.size());
		return new ArrayList<String>(couponSet);
	}
	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		CouponUtil.createCoupon2(100000);
		long end=System.currentTimeMillis();
		System.out.println("total cost:"+(end-start)/1000f);
	}
}
