package com.tiger.test.TestSpark;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class MySparkTest {

	public static void main(String[] args) {
		JavaSparkContext ctx = new JavaSparkContext("spark://echqdevhadoop3:7077", "JavaWordCount", System.getenv("SPARK_HOME"),
				JavaSparkContext.jarOfClass(MySparkTest.class));
//		JavaSparkContext ctx = new JavaSparkContext();
		
		System.out.println("spark home:" + ctx.getSparkHome());
		JavaRDD<String> lines = ctx.textFile(args[0], 1);
		lines.cache();
		List<String> line = lines.collect();

		print(line);

		JavaRDD<String> contaninsE = lines.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String s) throws Exception {

				return (s.contains("they"));
			}
		});
		
		System.out.println("--------------next filter's  result------------------");  
        line = contaninsE.collect();  
        print(line);  
        
        JavaRDD<String> sampletest = lines.sample(false,0.1,5);  
        System.out.println("-------------next sample-------------------");  
        line = sampletest.collect();  
        print(line);
	}
	
	private static void print(List<String> line){
		for(String val:line)  
            System.out.println(val);
	}
}
