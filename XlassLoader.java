package week01;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class XlassLoader extends ClassLoader {

	public static void main(String[] args) throws Exception {

		final String classNmae = "Hello";
		final String methodNmae = "hello";

		ClassLoader classLoader = new XlassLoader();

		Class<?> loadClass = classLoader.loadClass(classNmae);

		Object instance = loadClass.getDeclaredConstructor().newInstance();

		Method method = loadClass.getMethod(methodNmae);
		method.invoke(instance);
	}

	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String resourcePath = name.replace(".", "/");
		final String suffix = ".xlass";
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath + suffix);

		try {
			int length = inputStream.available();
			byte[] biteArray = new byte[length];
			inputStream.read(biteArray);
			byte[] classBytes = decode(biteArray);

			return defineClass(name,classBytes,0,classBytes.length);
			
		} catch (IOException e) {
			throw new ClassNotFoundException(name,e);
		}finally {
			close(inputStream);
		}

		

	}

	private static byte[] decode(byte[] byteArray) {
		byte[] array = new byte[byteArray.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = (byte) (255 - byteArray[i]);

		}
		return array;
	}

	private static void close(Closeable res) {
		if (null != res) {
			try {
				res.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
