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

	private String commonGetText(String url) {
		Document document;
		try {
			document = Jsoup.connect(url).get();
			return document.body().toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "nothing";
		}

	}

	// コラム数により試行回数増減
	public ArrayList<String> couldNotConnect() {
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
		String text = commonGetText("https://twittrend.jp/japan/");
		if(text.equals("nothing")){
			return couldNotConnect();
		}
		ArrayList<String> twList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Pattern p = Pattern.compile("(" + i + "\\.<.+>)(.+)</a");
			Matcher m = p.matcher(text);
			m.find();
			twList.add(m.group(2));
		}
		return twList;

	}

	// google
	public ArrayList<String> getGoogleInfoLogic() {
		Document document;
		try {
			document = Jsoup.connect("https://trends.google.co.jp/trends/hottrends/atom/hourly?pn=p4").get();
			String googleText = document.body().toString();
			String text = commonGetText("https://trends.google.co.jp/trends/hottrends/atom/hourly?pn=p4");
			Pattern p = Pattern.compile(";!\\[CDATA\\[(.+),");
			Matcher m = p.matcher(text);
			m.find();
			googleText = m.group(1);
			String[] googles = googleText.split(", ", 0);
			ArrayList<String> googleList = new ArrayList<>();
			for (int i = 0; i < googles.length; i++) {
				googleList.add(googles[i]);
			}
			return googleList;
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect();
		}
	}

	// nhk
	public ArrayList<String> getNHKInfoLogic() {
		Element element;
		try {
			element = Jsoup.connect("http://www3.nhk.or.jp/rss/news/cat0.xml").get();
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
			Document doc = Jsoup.connect("http://www.itmedia.co.jp/news/subtop/archive/").get();
			String text = doc.body().toString();
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(text);
			ArrayList<String> list = new ArrayList<>();
			for (int i = 1; i <= 10; i++) {
				m.find();
				list.add(m.group(2));
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect();
		}
	}
}
