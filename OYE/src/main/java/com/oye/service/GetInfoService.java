package com.oye.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class GetInfoService {


	public ArrayList<String> getTwInfoLogic() throws IOException{
		//jsoupでhtml取得・文字列化
		Document document = Jsoup.connect("https://twittrend.jp/japan/").get();
		String twText = document.body().toString();
		//5位まで取得・リスト化
		ArrayList<String> twList = new ArrayList<>();
		for(int i = 1; i <= 5; i++){
			Pattern p = Pattern.compile("(" + i + "\\.<.+>)(.+)</a");
			Matcher m = p.matcher(twText);
			m.find();
			twList.add(m.group(2));
		}
		return twList;
	}
}
