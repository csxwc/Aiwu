package com.aiwu.utils.Recommender;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Reducer1 extends Reducer<Text, Text, Text, Text>{

    private Text outKey = new Text();
    private Text outValue = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String houseId = key.toString();

        Map<String, Integer> map = new HashMap<>();

        for (Text value : values) {

            String[] split = value.toString().split("_");
            String userId = split[0];
            String score = split[1];

            if (map.get(userId) == null) {
                map.put(userId, Integer.parseInt(score));
            } else {
                Integer preScore = map.get(userId);
                map.put(userId, preScore + Integer.parseInt(score));
            }

        }

        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {

            String userId = entry.getKey();
            String score  = String.valueOf(entry.getValue());
            sb.append(userId).append("_").append(score).append(",");

        }

        String line = null;
        if (sb.toString().endsWith(",")) {
            line = sb.substring(0, sb.length()-1);
        }

        outKey.set(houseId);
        outValue.set(line);

        context.write(outKey, outValue);

    }
}
