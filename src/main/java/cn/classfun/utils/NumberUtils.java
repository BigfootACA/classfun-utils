package cn.classfun.utils;
/**
 * 数字工具
 */
@SuppressWarnings({"unused","RedundantSuppression"})
public final class NumberUtils{
	private NumberUtils(){throw new RuntimeException();}

	/**
	 * 在指定范围生成随机数
	 * <p>示例:</p>
	 * <p>  random(10,0) = 3</p>
	 * <p>  random(10,0) = 8</p>
	 * <p>  random(100,1) = 40</p>
	 * @see Math#random()
	 * @param max 最大值
	 * @param min 最小值
	 * @return 随机数
	 */
	public static long random(long max,long min){return Math.round(Math.random()*(max-min))+min;}

	/**
	 * 在0到指定最大值之间生成随机数
	 * @see #random(long)
	 * @param max 最大值
	 * @return 随机数
	 */
	public static long random(long max){return random(max,0);}
}
