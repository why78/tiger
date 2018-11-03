package com.tiger.test.TestSpark;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import com.tiger.test.SparkRefTest.ReferenceTest;
import com.tiger.test.SparkRefTest.utils.ConfigurationManager;

public class MySparkTest {

	public static void main(String[] args) {
		ConfigurationManager.loadConfig("setup.cfg");
		String key1 = ConfigurationManager.getProperty("key1");
		System.out.println(String.format("--------------Key1[%s] in config!------------------", key1));
		JavaSparkContext ctx = new JavaSparkContext("spark://echqdevhadoop3:7077", "JavaWordCount", System.getenv("SPARK_HOME"),
				JavaSparkContext.jarOfClass(MySparkTest.class));
//		JavaSparkContext ctx = new JavaSparkContext();
		
		System.out.println("spark home:" + ctx.getSparkHome());
		String path = "D:/work/github/tiger/TestSpark/words.txt";
		JavaRDD<String> lines = ctx.textFile(args[0], 1);
		lines.cache();
		List<String> line = lines.collect();

		ReferenceTest.print(line);

		JavaRDD<String> contaninsE = lines.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String s) throws Exception {

				return (s.contains("they"));
			}
		});
		
		System.out.println("--------------next filter's  result------------------");  
        line = contaninsE.collect();  
        ReferenceTest.print(line);  
        
        JavaRDD<String> sampletest = lines.sample(false,0.1,5);  
        System.out.println("-------------next sample-------------------");  
        line = sampletest.collect();  
        ReferenceTest.print(line);
	}
	
	
}
