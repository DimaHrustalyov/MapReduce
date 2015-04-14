package com.hrustalyov.map;

import com.hrustalyov.model.TextFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Map extends Mapper<Object, Text, Text, Text> {

	@Override
	protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] tuple = line.split("\\n");

		List<TextFile> textFiles = getTextFiles(tuple);

		for (TextFile textFile : textFiles) {
			String fileName = textFile.getName();
			StringTokenizer tokenizer = new StringTokenizer(textFile.getText());

			while (tokenizer.hasMoreTokens()) {
				String word = tokenizer.nextToken();

				context.write(new Text(word), new Text(fileName));
			}
		}
	}

	private List<TextFile> getTextFiles(String[] tuple) {

		List<TextFile> textFiles = new ArrayList<TextFile>();

		try {
			for (int i = 0; i < tuple.length; i++) {
				JSONObject jsonObject = new JSONObject(tuple[i]);

				TextFile textFile = new TextFile();
				textFile.setName(jsonObject.getString("file"));
				textFile.setText(jsonObject.getString("text"));

				textFiles.add(textFile);
			}
			return textFiles;

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

}
