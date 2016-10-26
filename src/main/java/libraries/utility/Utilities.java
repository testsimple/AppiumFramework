package libraries.utility;

import java.io.FileReader;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utilities {
	/**
	 * Sorting List
	 */
	public static List<String> sortListAscending(List<String> list) {
		Collections.sort(list);
		return list;
	}

	public static List<String> sortListDescending(List<String> list) {
		sortListAscending(list);
		Collections.reverse(list);
		return list;
	}

	public static String minList(List<String> list) {
		return Collections.min(list).toString();
	}

	public static String maxList(List<String> list) {
		return Collections.max(list).toString();
	}

	/**
	 * Sorting Map
	 */
	public static Map<String, Integer> sortMap(Map<String, Integer> map) {

		// Map -> List -> Sorting list -> toMap
		List<Map.Entry<String, Integer>> tmpList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(tmpList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> o : tmpList) {
			sortedMap.put(o.getKey(), o.getValue());
		}
		return sortedMap;
	}

	/**
	 * Read JSON Object from file use org.json.simple.JSONObject;
	 */
	public static String readJsonFile(String pathName) {
		JSONObject jsonObject = new JSONObject();
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(pathName));
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {

		}
		return jsonObject.toString();
	}
}
