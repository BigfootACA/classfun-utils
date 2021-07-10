package cn.classfun.utils;
import static java.lang.String.format;
/**
 * 字符串工具
 */
@SuppressWarnings({"unused","RedundantSuppression"})
public final class StringUtils {
	private StringUtils(){throw new RuntimeException();}
	public static final String valid="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";//a-z,A-Z,0-9,-,_
	public static final String[]units=new String[]{"B","KB","MB","GB","TB","PB","EB"};//存储容量单位

	/**
	 * 检测check之中只能出现valid中指定的字符
	 * 用于判断是否出现非法字符。
	 * <p>示例:</p>
	 * <p>  validString("123","1234567890")=true</p>
	 * <p>  validString("1234567890","1234567890")=true</p>
	 * <p>  validString("1 23","1234567890")=false</p>
	 * <p>  validString("abc","1234567890")=false</p>
	 * <p>  validString("","1234567890")=true</p>
	 * @param check 需要检测的字符
	 * @param valid 指定只能出现的字符
	 * @return 是否有效
	 */
	public static boolean validString(String check,String valid){
		for(char c:check.toCharArray())if(!valid.contains(String.valueOf(c)))return false;
		return true;
	}

	/**
	 * 检测check之中只能出现{@link #valid}中指定的字符
	 * 用于判断是否出现非法字符。
	 * <p>示例:</p>
	 * <p>  validString("abc123")=true</p>
	 * <p>  validString("ab_-12")=true</p>
	 * <p>  validString("ab;123")=false</p>
	 * <p>  validString("ab 123")=false</p>
	 * <p>  validString("")=true</p>
	 * @see #validString(String,String)
	 * @param check 需要检测的字符
	 * @return 是否有效
	 */
	public static boolean validString(String check){
		return validString(check,valid);
	}

	/**
	 * 判断string是否为空
	 * （判断string是否为null或者{@link String#trim}后的长度为0）
	 * <p>示例:</p>
	 * <p>  stringIsNull(null)=true</p>
	 * <p>  stringIsNull("")=true</p>
	 * <p>  stringIsNull(" ")=true</p>
	 * <p>  stringIsNull("\n")=true</p>
	 * <p>  stringIsNull("abc")=false</p>
	 * <p>  stringIsNull(" abc ")=false</p>
	 * @param string 需要判断的字符串
	 * @return 是否为空
	 */
	public static boolean stringIsNull(String string){
		return string==null||string.trim().length()<=0;
	}

	/**
	 * 按长度切割字符串为数组
	 * <p>示例:</p>
	 * <p>  spiltLength("1234567890",2)={"12","34","56","78","90"}</p>
	 * <p>  spiltLength("abcdefghijklmnopqrstuvwxyz",3)={"abc","def","ghi","jkl","mno","pqr","stu","vwx","yz"}</p>
	 * @param source 原字符串
	 * @param length 每一段的长度
	 * @return 切割后的数组
	 */
	public static String[] spiltLength(String source,int length){
		int len=source.length();
		String[] arr=new String[(len+length-1)/length];
		for(int i=0;i<len;i+=length){
			int n=len-i;
			if(n>length)n=length;
			arr[i/length]=source.substring(i,i+n);
		}
		return arr;
	}

	/**
	 * 获取两个字符串之间的字符串。
	 * 在source中获取从start开始以end结束的字符串，
	 * 如果tag为true，则将start和end包含在返回结果中
	 * <p>示例:</p>
	 * <p>  getBetween("abc<def>ghi","<",">",false)="ghi"</p>
	 * <p>  getBetween("12(2345678)90","(",")",true)="(2345678)"</p>
	 * @param source 原字符串
	 * @param start 截取的开始
	 * @param end 截取的结束
	 * @param tag 返回值是否包含start和end
	 * @return 两个字符串之间的字符串
	 */
	public static String getBetween(String source,String start,String end,boolean tag){
		return source.substring(
			source.indexOf(start)+(tag?0:start.length()),
			source.indexOf(end)-(tag?end.length():0)
		);
	}
	/**
	 * 将字节数转成可读的带单位字符串
	 * @param size 原字节数（长整数）
	 * @param step 每一级单位的大小（可以为1024或者1000）
	 * @param dot 保留小数点后的位数 （大于0，如果为零则不保留小数）
	 * @param units 单位名称
	 * @return 可读的字符串
	 */
	public static String formatSize(long size,int step,int dot,String[] units){
		double value=size;
		String unit=units[0];
		if(dot<0)dot=0;
		for(String s:units){
			unit=s;
			if(value<step)break;
			value/=step;
		}
		return format(format("%%.%df%%s",dot),value,unit);
	}
	/**
	 * 将字节数转成可读的带单位字符串
	 * @see #formatSize(long,int)
	 * @see #formatSize(long,int)
	 * @param size 原字节数（长整数）
	 * @return 可读的字符串
	 */
	public static String formatSize(long size){
		return formatSize(size,2);
	}

	/**
	 * 将字节数转成可读的带单位字符串
	 * （注：使用默认单位{@link #units}
	 * @see #formatSize(long,int,int,String[])
	 * @param size 原字节数（长整数）
	 * @param step 每一级单位的大小（可以为1024或者1000）
	 * @param dot 保留小数点后的位数 （大于0，如果为零则不保留小数）
	 * @return 可读的字符串
	 */
	public static String formatSize(long size,int step,int dot){
		return formatSize(size,step,dot,units);
	}

	/**
	 * 将字节数转成可读的带单位字符串
	 * (当step未指定时，使用默认1024)
	 * <p>示例:</p>
	 * <p>  formatSize(16,0)="16B"</p>
	 * <p>  formatSize(1024,2)="1.0KB"</p>
	 * <p>  formatSize(1048576,2)="1.00MB"</p>
	 * @see #formatSize(long,int,int)
	 * @param size 原字节数（长整数）
	 * @param dot 保留小数点后的位数 （大于0，如果为零则不保留小数）
	 * @return 可读的字符串
	 */
	public static String formatSize(long size,int dot){
		return formatSize(size,1024,dot);
	}

	/**
	 * 将字节数转成可读的带单位字符串
	 * (当step未指定时，使用默认1024)
	 * @see #formatSize(long,int,int,String[])}
	 * @param size 原字节数（长整数）
	 * @param dot 保留小数点后的位数 （大于0，如果为零则不保留小数）
	 * @param units 单位名称
	 * @return 可读的字符串
	 */
	public static String formatSize(long size,int dot,String[]units){
		return formatSize(size,1024,dot,units);
	}

	/**
	 * 将字节数转成可读的带单位字符串
	 * (当dot未指定时，使用默认2)
	 * @see #formatSize(long,int,String[])}
	 * @param size 原字节数（长整数）
	 * @param units 单位名称
	 * @return 可读的字符串
	 */
	public static String formatSize(long size,String[]units){
		return formatSize(size,2,units);
	}


	/**
	 * 判断字符是否为十进制的有效值(是否在0-9之间)
	 * <p>示例:</p>
	 * <p>  IsCharDec('1') = true</p>
	 * <p>  IsCharDec('5') = true</p>
	 * <p>  IsCharDec('B') = false</p>
	 * @param c 输入字符
	 * @return 字符是否在0-9之间
	 */
	public static boolean isCharDec(char c){return c>='0'&&c<='9';}

	/**
	 * 判断字符是否为二进制的有效值(是否在0-1之间)
	 * <p>示例:</p>
	 * <p>  IsCharBin('0') = true</p>
	 * <p>  IsCharBin('1') = true</p>
	 * <p>  IsCharBin('2') = false</p>
	 * @param c 输入字符
	 * @return 字符是否在0-1之间
	 */
	public static boolean isCharBin(char c){return c=='0'||c=='1';}

	/**
	 * 判断字符是否为十六进制的有效值(是否在0-9,a-f,A-F之间)
	 * <p>示例:</p>
	 * <p>  IsCharHex('1') = true</p>
	 * <p>  IsCharHex('B') = true</p>
	 * <p>  IsCharHex('x') = false</p>
	 * @param c 输入字符
	 * @return 字符是否在0-9,a-f,A-F之间
	 */
	public static boolean isCharHex(char c){return isCharDec(c)||(c>='a'&&c<='f')||(c>='A'&&c<='F');}

	/**
	 * 判断字符串的每个字符是否为十进制的有效值(是否在0-9之间)
	 * <p>示例:</p>
	 * <p>  IsStringDec("1234") = true</p>
	 * <p>  IsStringDec("12CD") = false</p>
	 * <p>  IsStringDec("-16") = false</p>
	 * <p>  IsStringDec("30X") = false</p>
	 * @see #isCharDec(char)
	 * @param s 输入字符
	 * @return 字符串的每个字符是否在0-9之间
	 */
	public static boolean isStringDec(String s){
		if(s==null||s.length()<=0)return false;
		for(char c:s.toCharArray())if(!isCharDec(c))return false;
		return true;
	}

	/**
	 * 判断字符串的每个字符是否为二进制的有效值(是否在0-1之间)
	 * <p>示例:</p>
	 * <p>  IsStringBin("0101") = true</p>
	 * <p>  IsStringBin("1234") = false</p>
	 * <p>  IsStringBin("12cd") = false</p>
	 * <p>  IsStringBin("30X") = false</p>
	 * @see #isCharBin(char)
	 * @param s 输入字符
	 * @return 字符串的每个字符是否在0-1之间
	 */
	public static boolean isStringBin(String s){
		if(s==null||s.length()<=0)return false;
		for(char c:s.toCharArray())if(!isCharBin(c))return false;
		return true;
	}

	/**
	 * 判断字符串是否为十进制的有效值(是否在0-9之间)
	 * <p>示例:</p>
	 * <p>  IsStringDec("1234") = true</p>
	 * <p>  IsStringDec("12CD") = true</p>
	 * <p>  IsStringDec("-16") = false</p>
	 * <p>  IsStringDec("30X") = false</p>
	 * @see #isCharHex(char)
	 * @param s 输入字符
	 * @return 字符串是否在0-9之间
	 */
	public static boolean isStringHex(String s){
		if(s==null||s.length()<=0)return false;
		for(char c:s.toCharArray())if(!isCharHex(c))return false;
		return true;
	}

	/**
	 * 如果输入字符串长度没有达到要求，则在左侧插入字符
	 * <p>示例:</p>
	 * <p>  PadLeft("1234",'0',8) = "00001234"</p>
	 * <p>  PadLeft("abc",' ',6) = "   abc"</p>
	 * @param src 输入字符串
	 * @param pad 添加字符
	 * @param len 要求的长度
	 * @return 处理后的字符串
	 */
	public static String padLeft(String src,char pad,int len){
		if(src==null||pad==0||src.length()==len||src.length()==0)return src;
		StringBuilder sb=new StringBuilder(src);
		while(sb.length()<len)sb.insert(0,pad);
		return sb.toString();
	}

	/**
	 * 如果输入字符串长度没有达到要求，则在右侧追加字符
	 * <p>示例:</p>
	 * <p>  PadRight("1111",'0',8) = "11110000"</p>
	 * <p>  PadRight("abc",' ',6) = "abc   "</p>
	 * @param src 输入字符串
	 * @param pad 添加字符
	 * @param len 要求的长度
	 * @return 处理后的字符串
	 */
	public static String padRight(String src,char pad,int len){
		if(src==null||pad==0||src.length()==len||src.length()==0)return src;
		StringBuilder sb=new StringBuilder(src);
		while(sb.length()<len)sb.append(pad);
		return sb.toString();
	}
}
