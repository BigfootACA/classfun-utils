package cn.classfun.utils;
import static cn.classfun.utils.ObjectUtils.requireNonNull;
import static cn.classfun.utils.StringUtils.*;
/**
 * 数组工具
 */
@SuppressWarnings({"unused","RedundantSuppression"})
public final class ArrayUtils{
	private ArrayUtils(){throw new RuntimeException();}

	/**
	 * 在一个字符串数组找出最长的一个字符串的长度
	 * <p>示例:</p>
	 * <p>  arrayStringMaxLen(new String[]{"123","str","abc"}) = 3</p>
	 * <p>  arrayStringMaxLen(new String[]{"123","string","abc"}) = 6</p>
	 * <p>  arrayStringMaxLen(new String[]{"123","string","a_string"}) = 8</p>
	 * @param arr 字符串数组
	 * @return 最长字符串的长度
	 */
	public static long arrayStringMaxLen(String[]arr){
		long cur=0;
		for(String s:requireNonNull(arr))cur=Math.max(cur,s.length());
		return cur;
	}

	/**
	 * 判断数组的每个字符串的每个字符是否为十进制的有效值(是否在0-9之间)
	 * <p>示例:</p>
	 * <p>  IsArrayDec(new String[]{"123","456","789") = true</p>
	 * <p>  IsArrayDec(new String[]{"2d1","222","111") = false</p>
	 * @see StringUtils#isCharDec(char)
	 * @see StringUtils#isStringDec(String)
	 * @param s 输入字符串数组
	 * @return 数组的每个字符串的每个字符是否在0-9之间
	 */
	public static boolean isArrayDec(String[]s){
		if(s==null||s.length<=0)return false;
		for(String ss:s)if(!isStringDec(ss))return false;
		return true;
	}

	/**
	 * 判断数组的每个字符串的每个字符是否为二进制的有效值(是否在0-1之间)
	 * <p>示例:</p>
	 * <p>  IsArrayBin(new String[]{"1111","0000","0101") = true</p>
	 * <p>  IsArrayBin(new String[]{"123","456","789") = false</p>
	 * @see StringUtils#isCharBin(char)
	 * @see StringUtils#isStringBin(String)
	 * @param s 输入字符串数组
	 * @return 数组的每个字符串的每个字符是否在0-1之间
	 */
	public static boolean isArrayBin(String[]s){
		if(s==null||s.length<=0)return false;
		for(String ss:s)if(!isStringBin(ss))return false;
		return true;
	}

	/**
	 * 判断数组的每个字符串的每个字符是否为十进制的有效值(是否在0-9之间)
	 * <p>示例:</p>
	 * <p>  IsArrayDec(new String[]{"123","456","789") = true</p>
	 * <p>  IsArrayDec(new String[]{"2d1","222","111") = false</p>
	 * @see StringUtils#isCharDec(char)
	 * @see StringUtils#isStringDec(String)
	 * @param s 输入字符串数组
	 * @return 数组的每个字符串的每个字符是否在0-9之间
	 */
	public static boolean isArrayHex(String[]s){
		if(s==null||s.length<=0)return false;
		for(String ss:s)if(!isStringHex(ss))return false;
		return true;
	}
}
