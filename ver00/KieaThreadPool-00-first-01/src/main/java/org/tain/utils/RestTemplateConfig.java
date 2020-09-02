package org.tain.utils;

import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.tain.utils.enums.RestTemplateType;

public class RestTemplateConfig {

	//public static RestTemplate get(int switchNumber) throws Exception {
	public static RestTemplate get(RestTemplateType type) throws Exception {
		
		RestTemplate restTemplate = null;
		
		switch(type) {
		case NORMAL: // normal http
			restTemplate = new RestTemplate();
			break;
		case SSL01: // ssl-1 https
			skip();
			restTemplate = new RestTemplate();
			break;
		case SSL02: // ssl-2 https
			restTemplate = _getRestTemplate();
			break;
		case SETENV: // normal set the env
			restTemplate = _getCustomRestTemplate();
			break;
		}
		return restTemplate;
	}
	
	private static void skip() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[] {
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}
					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}
				}
		};
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}
	
	private static RestTemplate _getRestTemplate() throws Exception {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
			.loadTrustMaterial(null, acceptingTrustStrategy)
			.build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom()
			.setSSLSocketFactory(csf)
			.build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		
		return new RestTemplate(requestFactory);
	}
	
	private static RestTemplate _getCustomRestTemplate() {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectTimeout(30 * 1000);
		httpRequestFactory.setReadTimeout(30 * 1000);
		HttpClient httpClient = HttpClientBuilder.create()
				.setMaxConnTotal(200)
				.setMaxConnPerRoute(20)
				.build();
		httpRequestFactory.setHttpClient(httpClient);
		return new RestTemplate(httpRequestFactory);
	}
}

/*
// 2020-09-02
TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
	public X509Certificate[] getAcceptedIssuers(){return new X509Certificate[0];}
	public void checkClientTrusted(X509Certificate[] certs, String authType){}
	public void checkServerTrusted(X509Certificate[] certs, String authType){}
}};

SSLContext sc = SSLContext.getInstance("TLS");
sc.init(null, trustAllCerts, new SecureRandom());
HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

--------------------------------------------
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            new Main().crawler("http://trandent.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crawler(String domain) throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
            public X509Certificate[] getAcceptedIssuers(){return new X509Certificate[0];}
            public void checkClientTrusted(X509Certificate[] certs, String authType){}
            public void checkServerTrusted(X509Certificate[] certs, String authType){}
        }};
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        Document doc = null;
        try{
            doc = Jsoup.connect(domain).get();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

--------------------------------------------
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
    }

    public void crawler(String domain) extends Thread{
        try{
            Document doc = Jsoup.connect(domain).get();
            Elements img = doc.getElementsByTag("img");
            for (Element el : img) {
                String src = el.absUrl("src");
                System.out.println("Image Found!");
                System.out.println("src attribute is : "+src);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

--------------------------------------------
public static void sendPost(String parameters) throws Exception {
    TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
        public X509Certificate[] getAcceptedIssuers(){return new X509Certificate[0];}
        public void checkClientTrusted(X509Certificate[] certs, String authType){}
        public void checkServerTrusted(X509Certificate[] certs, String authType){}
    }};
    SSLContext sc = SSLContext.getInstance("TLS");
    sc.init(null, trustAllCerts, new SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

    String url = "https://url";
    URL obj = new URL(url);
    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

    //add reuqest header
    con.setRequestMethod("POST");

    String urlParameters = parameters;

    // Send post request
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.write(urlParameters.getBytes("UTF-8"));
    wr.flush();
    wr.close();

    int responseCode = con.getResponseCode();
    System.out.println("\nSending 'POST' request to URL : " + url);
    System.out.println("Post parameters : " + urlParameters);
    System.out.println("Response Code : " + responseCode);

    BufferedReader in = new BufferedReader(
      new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
    }
    in.close();

    //print result
    System.out.println(response.toString());
}







*/

