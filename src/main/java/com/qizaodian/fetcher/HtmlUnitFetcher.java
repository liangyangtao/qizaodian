package com.qizaodian.fetcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/***
 * 
 * @ClassName: HtmlUnitFetcher
 * @Description: HtmlUnit 示例
 * @author: Administrator
 * 
 */
public class HtmlUnitFetcher {

	private static Log logger = LogFactory.getLog(HtmlUnitFetcher.class);
	private WebClient webClient;

	public HtmlUnitFetcher() {
		webClient = new WebClient(BrowserVersion.FIREFOX_45);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setTimeout(10 * 1000);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.waitForBackgroundJavaScript(10 * 1000);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
	}

	public String get(String url) {
		HtmlPage page = null;
		try {
			page = webClient.getPage(url);
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String html = page.asXml();
		return html;
	}

	public String get(String url, String charset) {
		return get(url);
	}

	public void setProxy(String proxyIp, String proxyPort) {
		ProxyConfig proxyConfig = new ProxyConfig(proxyIp,
				Integer.parseInt(proxyPort));
		webClient.getOptions().setProxyConfig(proxyConfig);
	}

	public void closeWebClient() {
		if (webClient != null) {
			webClient.close();
		}
	}

}
