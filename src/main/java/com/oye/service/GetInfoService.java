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

	private ArrayList<String> commonGetMatcher(String url, String regex) throws IOException {
		Document document = Jsoup.connect(url).get();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(document.body().toString());
		ArrayList<String> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			m.find();
			list.add(m.group(2));
		}
		return list;
	}

	// コラム数により試行回数増減
	private ArrayList<String> couldNotConnect() {
		ArrayList<String> failedList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			failedList.add("情報を取得できませんでした。");
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
			return commonGetMatcher("https://twittrend.jp/japan/", "(\\d\\.<.+>)(.+)</a");
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect();
		}
	}

	// google
	public ArrayList<String> getGoogleInfoLogic() {
		try {
			return commonGetMatcher("https://trends.google.co.jp/trends/hottrends/atom/hourly?pn=p4",
					"(<a.+>)(.+)</a>");
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect();
		}
	}

	// nhk
	public ArrayList<String> getNHKInfoLogic() {
		try {
			Element element = Jsoup.connect("http://www3.nhk.or.jp/rss/news/cat0.xml").get();
			Elements elements = element.getElementsByTag("title");
			ArrayList<String> nhkList = new ArrayList<>();
			for (int i = 1; i <= 7; i++) {
				nhkList.add(elements.get(i).text());
			}
			return nhkList;
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect();
		}
	}

	public ArrayList<String> getITmediaInfoLogic(String regex) {
		try {
			return commonGetMatcher("http://www.itmedia.co.jp/news/subtop/archive/", regex);
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect();
		}
	}
}
