package cn.classfun.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static cn.classfun.utils.StreamUtils.stream2bytes;
import static cn.classfun.utils.StreamUtils.stream2string;
/**
 * 文件工具
 */
@SuppressWarnings({"unused","RedundantSuppression"})
public final class FileUtils {

	/**
	 * 文件转成文件流（打开文件流）
	 * @param file 文件（不能是文件夹）
	 * @return 文件输入流
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static InputStream file2stream(File file)throws IOException{
		return new FileInputStream(file);
	}

	/**
	 * 文件转成二进制数组（读取文件为二进制数组）
	 * 打开文件流并读取然后关闭
	 * （注：该函数隐式包含了输入流的关闭）
	 * （注：由于int类型的限制，该函数最大支持2GB的文件）
	 * 实际调用：打开文件流{@link #file2stream(File)}
	 * 实际调用：将输入流读取为二进制数组然后关闭{@link StreamUtils#stream2bytes(InputStream,int)}
	 * @param file 文件（不能是文件夹）
	 * @return 二进制数组（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static byte[] file2bytes(File file)throws IOException{
		return stream2bytes(file2stream(file),(int)file.length());
	}

	/**
	 * 文件转成字符串（读取文件为字符串）
	 * 打开文件流并读取然后关闭
	 * （注：该函数隐式包含了输入流的关闭）
	 * （注：由于int类型的限制，该函数最大支持2GB的文件）
	 * 实际调用：打开文件流{@link #file2stream(File)}
	 * 实际调用：将输入流读取为二进制数组然后关闭{@link StreamUtils#stream2string(InputStream,int)}
	 * @param file 文件（不能是文件夹）
	 * @return 字符串（读取文件成功后的数据）
	 * @throws IOException 找不到文件或者读取失败等
	 */
	public static String file2string(File file)throws IOException{
		return stream2string(file2stream(file),(int)file.length());
	}
}
