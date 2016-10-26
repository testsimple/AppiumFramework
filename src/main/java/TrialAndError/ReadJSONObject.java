package TrialAndError;

import java.util.Set;
import org.json.JSONObject;
import libraries.utility.Utilities;

public class ReadJSONObject {

	public static void main(String[] args) {

		///src/main/java/PageObjects/config/danhmucphu.json
		
		String path = System.getProperty("user.dir") + "/src/main/java/PageObjects/config/danhmucphu.json";

		String obj = Utilities.readJsonFile(path);

		JSONObject objTong = new JSONObject(obj);
		JSONObject objDienThoai = (JSONObject) objTong.getJSONObject("dienthoai");
		
		String test = "chọn linh kiện phụkiện";
		
		// Regex Danh Muc Phu
		Set<String> keyset= objDienThoai.keySet();
		for(String l: keyset){
			if (test.replaceAll("\\s", "").toLowerCase().matches(l)) {
				System.out.println(l);
				System.out.println("Muc tron danh sach regex: " + objDienThoai.getInt(l));
			}
		}
		
		
		
		
		
		
//		String test = "hkh máy tính bảng";

//		Map<String, JSONObject> map = new HashMap<String, JSONObject>();



		// for (String value : values) {
		// if (test.replaceAll("\\s", "").toLowerCase().matches(value)) {
		// System.out.println("Muc tron danh sach regex: " +
		// objDienThoai.get(value));
		// System.out.println(value.toString());
		// }
		// }
		// System.out.println(objDienThoai.);

	}
}
