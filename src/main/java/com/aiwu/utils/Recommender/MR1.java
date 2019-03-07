package com.aiwu.utils.Recommender;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileOutputStream;
import java.io.IOException;

public class MR1 {

    private static String inputPath = "/input/actionList.txt";
    private static String outputPath = "/output/step1_output";
    private static String hdfs = "hdfs://192.168.101.18:9000";

    public int run() {
        try {

            System.setProperty("hadoop.home.dir", "/home/hong/Downloads/hadoop-2.7.7");
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", hdfs);
            Job job = Job.getInstance(conf, "step1");
            job.setJarByClass(MR1.class);
            job.setJar("/home/hong/IdeaProjects/aiwu/out/artifacts/aiwu_jar");

            job.setMapperClass(Mapper1.class);
            job.setReducerClass(Reducer1.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Text.class);

            FileSystem fs = FileSystem.get(conf);
            Path inpath = new Path(inputPath);
            if(fs.exists(inpath)){
                FileInputFormat.addInputPath(job,inpath);
            }else{
                System.out.println(inpath);
                System.out.println("不存在");
            }

            Path outpath = new Path(outputPath);
            fs.delete(outpath, true);
            FileOutputFormat.setOutputPath(job, outpath);

            return job.waitForCompletion(true) ? 1 : -1;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {

        int result = -1;
        result = new MR1().run();
        if (result == 1)
            System.out.println("step1运行成功");
        else if (result == -1)
            System.out.println("step1运行失败");

    }

}
