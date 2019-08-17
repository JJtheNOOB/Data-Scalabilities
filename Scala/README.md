# __Apache Spark__

## __It is Scalable__
- A fast and general engine for large scale data processing. 

![spark_1](https://user-images.githubusercontent.com/41119352/62815688-fe69be80-bae9-11e9-974d-5ec25bc7f450.png)

## __It is Fast__
- It runs 100 times faster compared to MapReduce
- DAG (Directed Ayclic graph) engine optimizes workflow
- Like tensorflow, spark does not execute until all the results is combined

- You can code it in Java, Python or Scala
- Build around main concept: RDD (Resilient distributed dataset)

## __Components of Spark__
![spark_2](https://user-images.githubusercontent.com/41119352/62815771-4f2de700-baeb-11e9-82ba-c0660a8cc6e0.png)


## Running with Spark-submit command in the command line
- Make sure there are no paths to your local filesystem used in your script! That is what HDFS, S3, etc are for (Make sure that every node on your cluster would be able to access your files)
- Pakage up your Scala project into a JAR file (Using export in the IDE, can run on any machine that has java as well)
- You can know use spark-submit to execute your driver script outside of the IDE
- spark-submit -class <class object that contains your main function>
   - jars <paths to any dependencies>
   - files <files you want placed alongside your application>
   <your JAR file>
     



