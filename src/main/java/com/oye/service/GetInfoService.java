package com.oye.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class GetInfoService {

	private ArrayList<String> commonGetMatcher(String url, String regex, int time, int groupN) throws IOException {
		Document document = Jsoup.connect(url).get();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(document.toString());
		ArrayList<String> list = new ArrayList<>();
		for (int i = 1; i <= time; i++) {
			m.find();
			list.add(m.group(groupN));
		}
		return list;
	}

	private ArrayList<String> commonGetByTag(String url, String tag, int item) throws IOException {
		Element element = Jsoup.connect(url).get();
		Elements elements = element.getElementsByTag(tag);
		ArrayList<String> list = new ArrayList<>();
		for (int i = 1; i <= item; i++) {
			list.add(elements.get(i).text());
		}
		return list;
	}

	// コラム数により試行回数増減
	private ArrayList<String> couldNotConnect(String msg) {
		ArrayList<String> failedList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			failedList.add(msg);
		}
		return failedList;
	}

	public String getTime() {
		LocalDateTime now = LocalDateTime.now();
		return now.getYear() + "年" + now.getMonthValue() + "月" + now.getDayOfMonth() + ("日") + now.getHour() + "時"
				+ now.getMinute() + "分取得";
	}

	// twitter
	public ArrayList<String> getTwInfoLogic() {
		try {
			return commonGetMatcher("https://twittrend.jp/japan/", "(\\d\\.<.+>)(.+)</a", 10, 2);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	// google
	public ArrayList<String> getGoogleInfoLogic() {
		try {
			return commonGetMatcher("https://trends.google.co.jp/trends/hottrends/atom/hourly?pn=p4", "(<a.+>)(.+)</a>",
					10, 2);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	// nhk
	public ArrayList<String> getNHKInfoLogic() {
		try {
			return commonGetByTag("http://www3.nhk.or.jp/rss/news/cat0.xml", "title", 6);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	public ArrayList<String> getMainichiInfoLogic() {
		try {
			return commonGetByTag("https://mainichi.jp/rss/etc/mainichi-flash.rss", "title", 10);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}

	}

	public ArrayList<String> getMainichiLinkLogic() {
		try {
			return commonGetByTag("https://mainichi.jp/rss/etc/mainichi-flash.rss", "Link", 10);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	public ArrayList<String> getAsahiInfoLogic() {
		try {
			return commonGetByTag("http://rss.asahi.com/rss/asahi/newsheadlines.rdf", "title", 10);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	public ArrayList<String> getAsahiLinkLogic() {
		try {
			return commonGetByTag("http://rss.asahi.com/rss/asahi/newsheadlines.rdf", "Link", 10);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	public ArrayList<String> getYomiuriInfoLogic() {
		try {
			return commonGetMatcher("http://www.yomiuri.co.jp/latestnews/",
					"<span class=\"headline\">(.+?)<span class=\"update\">", 10, 1);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	public ArrayList<String> getYomiuriLinkLogic() {
		try {
			return commonGetMatcher("http://www.yomiuri.co.jp/latestnews/", "\".+\"> <a href=\"(.+?)\">", 10, 1);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	public ArrayList<String> getSankeiInfoLogic() {
		try {
			return commonGetMatcher("https://www.sankei.com/flash/newslist/flash-n1.html", "<li> <a href.+?>(.+?)</a>",
					10, 1);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	public ArrayList<String> getSankeiLinkLogic() {
		try {
			return commonGetMatcher("https://www.sankei.com/flash/newslist/flash-n1.html",
					"<li> <a href=\"\\.\\./\\.\\./(.+?)\"", 10, 1);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	public ArrayList<String> getNikkeiInfoLogic() {
		try {
			ArrayList<String> list = commonGetMatcher("https://www.nikkei.com/news/category/",
					"<a href=\"/article.+?><span.+?>(.+?)</span>", 5, 1);
			list.addAll(commonGetMatcher("https://www.nikkei.com/news/category/",
					"Text\"> <a href=\"/article.+?>(.+?)</a>", 5, 1));
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

	public ArrayList<String> getNikkeiLinkLogic() {
		try {
			ArrayList<String> list = commonGetMatcher("https://www.nikkei.com/news/category/",
					"<a href=\"/article(.+?)\"><span", 5, 1);
			list.addAll(commonGetMatcher("https://www.nikkei.com/news/category/",
					"Text\"> <a href=\"/article(.+?)\">", 5, 1));
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect("情報を取得できませんでした。");
		} catch (Exception e) {
			e.printStackTrace();
			return couldNotConnect("参照先のページ構成が変更されています。");
		}
	}

}
