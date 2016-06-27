package com.gyz.maintest.hadoops;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * 描述:
 * <p>
 * Created by 828477[JAX] on 2016/5/30 19:22.
 */
public class MyMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, LongWritable> {

    public void map(LongWritable longWritable, Text text, OutputCollector<Text, LongWritable> outputCollector, Reporter reporter) throws IOException {



    }
}
