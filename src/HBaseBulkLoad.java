
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;

public class HBaseBulkLoad {	
    /**
     * doBulkLoad.
     *
     * @param pathToHFile path to hfile
     * @param tableName 
     */
    public static void main(String[] args) {
        try {		
            Configuration configuration = new Configuration();
            configuration.addResource(new Path("/etc/hadoop/conf/core-site.xml"));
            configuration.addResource(new Path("/etc/hadoop/conf/hdfs-site.xml"));
            configuration.set("mapreduce.child.java.opts", "-Xmx1g");
            HBaseConfiguration.addHbaseResources(configuration);
            LoadIncrementalHFiles loadFfiles = new LoadIncrementalHFiles(configuration);
            HTable hTable = new HTable(configuration, "user1");
            loadFfiles.doBulkLoad(new Path("/tmp/output"), hTable);
            System.out.println("Bulk Load Completed..");
        } catch(Exception exception) {
            exception.printStackTrace();
        }		
    }	
}
