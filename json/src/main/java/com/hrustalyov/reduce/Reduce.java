package com.hrustalyov.reduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reduce extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		List<String> fileNames = new ArrayList<String>();
		for (Text text : values) {
			if (!fileNames.contains(text.toString())) {
				fileNames.add(text.toString());
			}
		}
		context.write(key, new Text(fileNames.toString()));
	}
}
