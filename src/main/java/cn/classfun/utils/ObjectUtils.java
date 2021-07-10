package cn.classfun.utils;
/**
 * 对象工具
 */
@SuppressWarnings({"unused","RedundantSuppression"})
public final class ObjectUtils{
	private ObjectUtils(){throw new RuntimeException();}

	/**
	 * 如果obj为空，返回atNull，否则返回notNull
	 * @param obj 输入对象
	 * @param atNull 当obj为空的时候返回对象
	 * @param notNull 当obj为不为空的时候返回对象
	 * @param <T> 任意类型
	 * @return 返回对象
	 */
	public static <T>T ifNull(Object obj,T atNull,T notNull){return obj==null?atNull:notNull;}

	/**
	 * 如果obj为空，返回atNull，否则返回obj本身
	 * @param obj 输入对象
	 * @param atNull 当obj为空的时候返回值
	 * @param <T> 任意类型
	 * @return 返回对象
	 */
	public static <T>T whenNull(T obj,T atNull){return ifNull(obj,atNull,obj);}

	/**
	 * 当obj为空时抛出NullPointerException
	 * @see java.util.Objects#requireNonNull(Object)
	 * @param obj 输入对象
	 * @param <T> 任意类型
	 * @return obj
	 */
	public static <T>T requireNonNull(T obj){
		if(obj==null)throw new NullPointerException();
		return obj;
	}

	/**
	 * 当obj为空时抛出NullPointerException
	 * @see java.util.Objects#requireNonNull(Object,String)
	 * @param obj 输入对象
	 * @param msg 抛出的错误信息
	 * @param <T> 任意类型
	 * @return obj
	 */
	public static <T>T requireNonNull(T obj,String msg){
		if(obj==null)throw new NullPointerException(msg);
		return obj;
	}
}
