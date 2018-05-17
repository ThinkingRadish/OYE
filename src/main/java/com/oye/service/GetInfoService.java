package com.oye.service;

import java.io.IOException;
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

	public ArrayList<String> couldNotConnect() {
		ArrayList<String> failedList = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			failedList.add("情報を取得できませんでした。");
		}
		return failedList;
	}

	// twitter
	public ArrayList<String> getTwInfoLogic() {
		// jsoupでhtml取得・文字列化
		Document document;
		try {
			document = Jsoup.connect("https://twittrend.jp/japan/").get();
			String twText = document.body().toString();
			ArrayList<String> twList = new ArrayList<>();
			for (int i = 1; i <= 5; i++) {
				Pattern p = Pattern.compile("(" + i + "\\.<.+>)(.+)</a");
				Matcher m = p.matcher(twText);
				m.find();
				twList.add(m.group(2));
			}
			return twList;
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect();
		}
	}

	// google
	public ArrayList<String> getGoogleInfoLogic() {
		Document document;
		try {
			document = Jsoup.connect("https://trends.google.co.jp/trends/hottrends/atom/hourly?pn=p4").get();
			String googleText = document.body().toString();
			Pattern p = Pattern.compile(";!\\[CDATA\\[(.+),");
			Matcher m = p.matcher(googleText);
			m.find();
			googleText = m.group(1);
			String[] googles = googleText.split(", ", 0);
			ArrayList<String> googleList = new ArrayList<>();
			for (int i = 0; i < googles.length - 1; i++) {
				googleList.add(googles[i]);
			}
			return googleList;
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect();
		}
	}

	//nhk
	public ArrayList<String> getNHKInfoLogic(){
		Element element;
		try {
			element = Jsoup.connect("http://www3.nhk.or.jp/rss/news/cat0.xml").get();
			Elements elements = element.getElementsByTag("title");
			ArrayList<String> nhkList = new ArrayList<>();
			for(int i = 1; i <= 7; i++){
				nhkList.add(elements.get(i).text());
			}
			return nhkList;
		} catch (IOException e) {
			e.printStackTrace();
			return couldNotConnect();
		}
	}
}
