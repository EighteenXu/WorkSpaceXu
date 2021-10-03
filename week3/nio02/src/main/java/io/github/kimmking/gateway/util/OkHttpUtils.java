package io.github.kimmking.gateway.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
	// 缓存客戶端实例
	public static OkHttpClient client = new OkHttpClient();

	// get调用
	public static String getAsString(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();

		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}

	}

	public static void main(String[] args) throws Exception {
		//String url = "https://square.github.io/okhttp";
    	String url = "http://localhost:8802";
		String text = OkHttpUtils.getAsString(url);
		System.out.println("url:" + url + ";response" + text);
		// 清理资源
		OkHttpUtils.client = null;
	}

}
