package cn.classfun.utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
/**
 * 输入输出流工具
 */
@SuppressWarnings({"unused","RedundantSuppression"})
public final class StreamUtils {

	/**
	 * 将输入流复制到输出流中
	 * @param in 输入流（复制的来源）
	 * @param out 输出流（复制的目标）
	 * @param buffer_size 单次读取以及写入的大小
	 * @param close 是否在复制完成后自动关闭
	 * @throws IOException 读取或者写入失败
	 */
	public static void stream2stream(InputStream in,OutputStream out,int buffer_size,boolean close)throws IOException{
		byte[]b=new byte[buffer_size];
		int len;
		while((len=in.read(b))>=0)//小于0时说明到达了结尾
			out.write(b,0,len);//有时候读到结尾的时候b后面会是空的，所以要指定成功读入的大小
		out.flush();//清空缓冲区
		if(close){//关闭流以节省资源
			in.close();
			out.close();
		}
	}

	/**
	 * 将输入流复制到输出流中
	 * 实际调用：{@link #stream2stream(InputStream,OutputStream,int,boolean)}
	 * （注：未指定close时默认为true）
	 * @param in 输入流（复制的来源）
	 * @param out 输出流（复制的目标）
	 * @param buffer_size 单次读取以及写入的大小
	 * @throws IOException 读取或者写入失败
	 */
	public static void stream2stream(InputStream in,OutputStream out,int buffer_size)throws IOException{
		stream2stream(in,out,buffer_size,true);
	}

	/**
	 * 将输入流读取成二进制数组
	 * @param in 输入流（读取来源）
	 * @param size 读取的大小
	 * @param close 是否自动关闭
	 * @return 二进制数组（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static byte[] stream2bytes(InputStream in,int size,boolean close)throws IOException{
		byte[]b=new byte[size];
		int l=in.read(b);
		byte[]c=new byte[l];//可能无法读取到预期的大小
		System.arraycopy(b,0,c,0,l);//裁减byte数组
		if(close)in.close();//关闭流以节省资源
		return c;
	}

	/**
	 * 将输入流读取成二进制数组
	 * 实际调用{@link #stream2bytes(InputStream,boolean)}
	 * （注：未指定close时默认为true）
	 * @param in 输入流（读取来源）
	 * @return 二进制数组（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static byte[] stream2bytes(InputStream in)throws IOException{
		return stream2bytes(in,true);
	}

	/**
	 * 将输入流读取成二进制数组
	 * 实际调用{@link #stream2bytes(InputStream,int,boolean)}
	 * （注：未指定close时默认为true）
	 * @param in 输入流（读取来源）
	 * @param size 读取的大小
	 * @return 二进制数组（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static byte[] stream2bytes(InputStream in,int size)throws IOException{
		return stream2bytes(in,size,true);
	}

	/**
	 * 将输入流读取成二进制数组
	 * 实际调用{@link #stream2bytes(InputStream,int,boolean)}
	 * （注：默认读取大小为输入流中可读的长度{@link InputStream#available()}
	 * @param in 输入流（读取来源）
	 * @param close 是否自动关闭
	 * @return 二进制数组（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static byte[] stream2bytes(InputStream in,boolean close)throws IOException{
		return stream2bytes(in,in.available(),close);
	}

	/**
	 * 将输入流读取成字符串
	 * 实际调用{@link #stream2bytes(InputStream,boolean)}
	 * @param in 输入流（读取来源）
	 * @param close 是否自动关闭
	 * @return 字符串（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static String stream2string(InputStream in,boolean close)throws IOException{
		return new String(stream2bytes(in,close));//二进制转字符串
	}

	/**
	 * 将输入流读取成字符串
	 * 实际调用{@link #stream2bytes(InputStream,int,boolean)}
	 * @param in 输入流（读取来源）
	 * @param close 是否自动关闭
	 * @param size 读取的大小
	 * @return 字符串（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static String stream2string(InputStream in,int size,boolean close)throws IOException{
		return new String(stream2bytes(in,size,close));//二进制转字符串
	}

	/**
	 * 将输入流读取成字符串（指定字符集）
	 * 实际调用{@link #stream2bytes(InputStream,boolean)}
	 * @param in 输入流（读取来源）
	 * @param close 是否自动关闭
	 * @param charset 使用的字符集
	 * @return 字符串（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static String stream2string(InputStream in,Charset charset,boolean close)throws IOException{
		return new String(stream2bytes(in,close),charset);//二进制转字符串（指定字符集）
	}

	/**
	 * 将输入流读取成字符串（指定字符集）
	 * 实际调用{@link #stream2bytes(InputStream,int,boolean)}
	 * @param in 输入流（读取来源）
	 * @param size 读取的大小
	 * @param close 是否自动关闭
	 * @param charset 使用的字符集
	 * @return 字符串（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static String stream2string(InputStream in,int size,Charset charset,boolean close)throws IOException{
		return new String(stream2bytes(in,size,close),charset);//二进制转字符串（指定字符集）
	}

	/**
	 * 将输入流读取成字符串（指定字符集）
	 * 实际调用{@link #stream2string(InputStream,int,Charset,boolean)}
	 * （注：未指定close时默认为true）
	 * @param in 输入流（读取来源）
	 * @param charset 使用的字符集
	 * @return 字符串（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static String stream2string(InputStream in,int size,Charset charset)throws IOException{
		return stream2string(in,size,charset,true);
	}

	/**
	 * 将输入流读取成字符串（指定字符集）
	 * 实际调用{@link #stream2string(InputStream,Charset,boolean)}
	 * （注：未指定close时默认为true）
	 * @param in 输入流（读取来源）
	 * @param charset 使用的字符集
	 * @return 字符串（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static String stream2string(InputStream in,Charset charset)throws IOException{
		return stream2string(in,charset,true);
	}

	/**
	 * 将输入流读取成字符串
	 * 实际调用{@link #stream2string(InputStream,boolean)}
	 * （注：未指定close时默认为true）
	 * @param in 输入流（读取来源）
	 * @return 字符串（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static String stream2string(InputStream in)throws IOException{
		return stream2string(in,true);
	}

	/**
	 * 将输入流读取成字符串
	 * 实际调用{@link #stream2string(InputStream,int,boolean)}
	 * （注：未指定close时默认为true）
	 * @param in 输入流（读取来源）
	 * @param size 读取的大小
	 * @return 字符串（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static String stream2string(InputStream in,int size)throws IOException{
		return stream2string(in,size,true);
	}
}
