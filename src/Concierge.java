import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Concierge {
	public static void main(String[] args) throws IOException {
		int
			f = 0,
			day = 0;
		URL url = new URL("http://weather.yahoo.co.jp/weather/jp/13/4410.html");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStream in = httpConn.getInputStream();
		BufferedReader r = new BufferedReader(new InputStreamReader(in,"utf-8"));
		
		for (String line = r.readLine(); line != null; line = r.readLine()) {
			if (line.indexOf("forecastCity") > -1)	f = 1;
			if (line.indexOf("forecastToday") > -1) f = 0;
			
			if (f == 0) continue;
			
			if(line.indexOf("date") > -1)
			{
				day++;
				String[] str = line.split("<p class=\"date\">");
				String s = str[1].split("</p>")[0];
				if (day == 1)System.out.println("本日(" + s + ")の東京の天気は");
				if (day == 2)System.out.println("\n明日(" + s + ")の東京の天気は");
			}
			
//			/*
			if(line.indexOf("pict") > -1)
			{
				for (int i = line.indexOf("alt=") +5; line.charAt(i) != '"'; i++)
				{
					System.out.print(line.charAt(i));
				}
			}
			if(line.indexOf("high") > -1)
			{
				System.out.print("\n最高気温は");
				String[] str = line.split("<em>"); 
				String s = str[1].split("</em>")[0];
				System.out.print(s);
				System.out.println("℃");
			}
			if(line.indexOf("low") > -1)
			{
				System.out.print("最低気温は");
				String s = line.split("<em>")[1].split("</em>")[0];
				System.out.print(s);
				System.out.println("℃です");
			}
//			*/
//			System.out.println(line);
		}
	}
}