package TrialAndError;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CreateJSONObject {

	public static void generateJsonFile(String pathName, JSONObject json) throws IOException {
		FileWriter file = new FileWriter(pathName);
		try {
			file.write(json.toString());
			System.out.println("Write JSON Ojects to File Successfully");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			file.flush();
			file.close();
		}
	}

	public static JSONObject readJsonFile(String pathName) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(pathName));
		JSONObject jsonObject = (JSONObject) obj;
		return jsonObject;
	}

	public static void main(String[] args) throws IOException, ParseException {
		Map<String, Integer> lMucKhac = new HashMap<String, Integer>();
		// Mục khác
		lMucKhac.put("(.*)m[áa]yt[ií]nhb[ảa]n(.*)", 0);
		lMucKhac.put("ch[oợ][oô]t[oô]", 1);
		lMucKhac.put("ch[oợ]c[oô]ngngh[eệ]", 2);
		lMucKhac.put("ch[oợ]thi[eế]tb[iị][dđ]i[eệ]n[-][dđ]i[eệ]nt[uử]", 3);
		lMucKhac.put("ch[oợ]xem[aá]y[&]xe[dđ][aạ]p", 4);
		lMucKhac.put("ch[oợ]gi[aá]od[uụ]c[-]tuy[eể]nsinh", 5);
		lMucKhac.put("duh[oọ]c", 6);
		lMucKhac.put("ch[oợ]d[iị]chv[uụ][-]s[uử]ach[uữ]a", 7);
		lMucKhac.put("l[aắ]pm[aạ]nginternet", 8);
		lMucKhac.put("ch[oợ]x[aâ]yd[uự]ng", 9);
		lMucKhac.put("ch[oợ]ki[eế]ntr[uú]c[-]n[oộ]ith[aấ]t", 10);
		lMucKhac.put("thi[eế]tk[eế]web[-]hosting[-]t[eê]nmi[eề]nph[aầ]nm[eề]m", 11);
		lMucKhac.put("th[uự]cph[aẩ]m[-][dđ][oồ]u[oố]ng[-]thu[oố]cch[uữ]ab[eệ]nh", 12);
		lMucKhac.put("nh[aà]h[aà]ng[-]c[aà]f[eê][-]bar[-]karaoke", 13);
		lMucKhac.put("m[eẹ]v[aà]b[eé]", 14);
		lMucKhac.put("kinhdoanh[-][dđ][aầ]ut[uư]", 15);
		lMucKhac.put("vay[&]chovayti[eề]n", 16);
		lMucKhac.put("b[aả]ohi[eể]m", 17);
		lMucKhac.put("ki[eế]mti[eề]ntr[eê]nm[aạ]ng", 18);
		lMucKhac.put("dul[iị]chx[uứ]ngh[eệ]", 19);
		lMucKhac.put("gi[oớ]ithi[eệ]udoanhnghi[eệ]p", 20);
		lMucKhac.put("game", 21);
		lMucKhac.put("raov[aặ]t", 22);
		lMucKhac.put("h[oỏ]i[-]đ[aá]p", 23);

		// Việc làm
		Map<String, Integer> lViecLam = new HashMap<String, Integer>();
		lViecLam.put("tuy[eể]nd[uụ]ng", 0);
		lViecLam.put("t[iì]mvi[eệ]c", 1);
		lViecLam.put("xu[aấ]tkh[aẩ]ulao[dđ][oộ]ng", 2);
		lViecLam.put("m[oô]igi[oớ]ivi[eệ]cl[aà]m", 3);

		// Điện thoại
		Map<String, Integer> lDienThoai = new HashMap<String, Integer>();
		lDienThoai.put("[---]", 0);
		lDienThoai.put("linhki[eệ]nph[uụ]ki[eệ]n", 1);
		lDienThoai.put("s[uử]ach[uữ]ac[aà]i[dđ][aặ]t", 2);

		// Máy tính
		Map<String, Integer> lMayTinh = new HashMap<String, Integer>();
		lMayTinh.put("[---]", 0);
		lMayTinh.put("laptop", 1);
		lMayTinh.put("linhki[eệ]nph[uụ]ki[eệ]nm[aá]yt[aí]nh", 2);

		// Bất động sản
		Map<String, Integer> lBatDongSan = new HashMap<String, Integer>();
		lBatDongSan.put("b[aá]nnh[aà][dđ][aấ]t", 0);
		lBatDongSan.put("muanh[aà][dđ][aấ]t", 1);
		lBatDongSan.put("chothu[eê]nh[aà][dđ][aấ]t", 2);
		lBatDongSan.put("c[aầ]nthu[eê]nh[aà][dđ][aấ]t", 3);

		// Thời trang
		Map<String, Integer> lThoiTrang = new HashMap<String, Integer>();
		lThoiTrang.put("---", 0);
		lThoiTrang.put("th[oờ]itrangnam", 1);

		// Duyệt Map
		Collection<String> values = lMucKhac.keySet();
		String test = "hkh máy tính bảng";
		for (String value : values) {
			if (test.replaceAll("\\s", "").toLowerCase().matches(value)) {
				System.out.println("Muc tron danh sach regex: " + lMucKhac.get(value));
			}
		}

		// json
		 JSONObject jsonThoiTrang = new JSONObject(lThoiTrang);
		 JSONObject jsonBatDongSan = new JSONObject(lBatDongSan);
		 JSONObject jsonMayTinh = new JSONObject(lMayTinh);
		 JSONObject jsonDienThoai = new JSONObject(lDienThoai);
		 JSONObject jsonViecLam = new JSONObject(lViecLam);
		 JSONObject jsonMucKhac = new JSONObject(lMucKhac);

		// Gôm thành 1 Json tổng
		 JSONObject jsonCha = new JSONObject();
		 jsonCha.put("vieclam", jsonViecLam);
		 jsonCha.put("dienthoai", jsonDienThoai);
		 jsonCha.put("maytinh", jsonMayTinh);
		 jsonCha.put("thoitrang", jsonThoiTrang);
		 jsonCha.put("batdongsan", jsonBatDongSan);
		 jsonCha.put("cacmuckhac", jsonMucKhac);

		 System.out.println(jsonCha);
		 System.out.println("---------------");
		 System.out.println("Số Object trong Json: " + jsonCha.length());
		
		 System.out.println("---------------");
		
		 System.out.println(jsonCha.get("thoitrang"));
		 System.out.println("---------------");
		 System.out.println(jsonCha.get("vieclam"));
		 System.out.println("---------------");
		 System.out.println(jsonCha.get("dienthoai"));
		 System.out.println("---------------");
		 System.out.println(jsonCha.get("maytinh"));
		 System.out.println("---------------");
		 System.out.println(jsonCha.get("batdongsan"));
		 System.out.println("---------------");
		 System.out.println(jsonCha.get("cacmuckhac"));
		 System.out.println("---------------");
		 System.out.println(" ks 9 9 0 lj - lk j".replaceAll("\\s","").replaceAll("\\W", ""));

		 generateJsonFile("D:/danhmucphu.json", jsonCha);

	}

}
