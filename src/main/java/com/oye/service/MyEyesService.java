package com.oye.service;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oye.entity.MyEyesEntity;
import com.oye.entity.MyEyesEntityRepository;

@Service
public class MyEyesService {
	@Autowired
	MyEyesEntityRepository meeRepository;

	public boolean isOrderValid(int google, int tw, int nhk, int mai, int asahi) {
		int[] order = { google, tw, nhk, mai, asahi};
		//重複チェック
		for (int i = 0; i < order.length; i++) {
			for (int j = 0; j < order.length; j++) {
				if (i == j) {
					continue;
				}
				if (order[i] == order[j] && (!(order[i] == 0))) {
					return false;
				}
			}
		}
		return true;
	}

	public void registerMyEyes(String username, int google, int twitter, int nhk, int mai, int asahi) {
		MyEyesEntity entity = new MyEyesEntity(username, google, twitter, nhk, mai, asahi);
		meeRepository.save(entity);
	}

	public ArrayList<String> loadMyEyes(Principal principal) {
		MyEyesEntity entity = meeRepository.findByUsername(principal.getName());
		int google;
		int twitter;
		int nhk;
		int mainichi;
		int asahi;

		//新規ユーザー用デフォルトレイアウト読み込み
		try{
			google = entity.getGoogle();
			twitter = entity.getTwitter();
			nhk = entity.getNhk();
			mainichi = entity.getMainichi();
			asahi = entity.getAsahi();
		}catch(NullPointerException e){
			ArrayList<String> list = new ArrayList<>();
			list.add("frag_google");
			list.add("frag_twitter");
			list.add("frag_nhk");
			list.add("frag_mainichi");
			list.add("frag_asahi");
			return list;
		}


		int[] orderI = { google, twitter, nhk, mainichi, asahi};
		String[] orderS = {"frag_google", "frag_twitter", "frag_nhk", "frag_mainichi", "frag_asahi", "frag_dummy" };

		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < orderS.length; i++) {
			for (int j = 0; j < orderI.length; j++) {

				if (orderI[j] == i) {
					//「表示しない」の値は0 空のdummyfragmentをリストの最初に加える。
					if(i == 0){
						list.add(orderS[orderS.length-1]);
						continue;
					}
					//値の順番に対応するフラグメントをリストに加える
					switch(j){
					case 0: list.add(orderS[0]);break;
					case 1: list.add(orderS[1]);break;
					case 2: list.add(orderS[2]);break;
					case 3: list.add(orderS[3]);break;
					case 4: list.add(orderS[4]);break;
					}
				}
			}
		}
		return list;
	}

}
