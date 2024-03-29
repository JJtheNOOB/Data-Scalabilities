import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/** Find the superhero with the most co-appearances. */
object MostPopularSuperhero {
  
  // Function to extract the hero ID and number of connections from each line
  def countCoOccurences(line: String) = {
    var elements = line.split("\\s+")  //regex split up on white space
    ( elements(0).toInt, elements.length - 1 )
  }
  
  // Function to extract hero ID -> hero name tuples (or None in case of failure) just like is NULL in python
  def parseNames(line: String) : Option[(Int, String)] = {
    var fields = line.split('\"')
    if (fields.length > 1) {
      return Some(fields(0).trim().toInt, fields(1))  // trim the first field to extract id and second field as the hero name
    } else {
      return None // flatmap will just discard None results, and extract data from Some results.
    }
  }
 
  /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
     // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "MostPopularSuperhero")   
    
    // Build up a hero ID -> name RDD
    val names = sc.textFile("../SparkScala/marvel-names.txt")
    val namesRdd = names.flatMap(parseNames)
    
    // Load up the superhero co-apperarance data
    val lines = sc.textFile("../SparkScala/marvel-graph.txt")
    
    // Convert to (heroID, number of connections) RDD
    val pairings = lines.map(countCoOccurences)
    
    // Combine entries that span more than one line
    val totalFriendsByCharacter = pairings.reduceByKey( (x,y) => x + y )
    
    // Flip it to # of connections, hero ID
    val flipped = totalFriendsByCharacter.map( x => (x._2, x._1) )
    
    // Find the max # of connections
    // val mostPopular = flipped.max()
    
    // Look up the name (lookup returns an array of results, so we need to access the first result with (0)).
    // sortBy(_._2) means sort the data by all the tuples' second value. The "-" at the beginning just negates the effect, meaning reverses the order of the sorted data.
    totalFriendsByCharacter.collect().sortBy(-_._2).take(10).foreach(ch => println(namesRdd.lookup(ch._1)(0)))
    
  }
  
}
