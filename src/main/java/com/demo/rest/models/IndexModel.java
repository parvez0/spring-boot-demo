package com.demo.rest.models;

import com.demo.rest.pojos.DataRequest;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IndexModel {
    // create a logger instance for indexModel class
    private final static Logger logger = LoggerFactory.getLogger(IndexModel.class.getName());

    // RecordData handles writing the data to file, which will be aggregated every 5 minutes
    public void WriteData(DataRequest data){
        try{
            FileWriter writer = new FileWriter("/Users/parvez/Private/spring-boot-demo/src/main/java/com/demo/rest/data/recorded_data.csv", true);
            writer.write(data.toString() + "\n");
            writer.close();
        }catch (FileNotFoundException e){
            // Handling exception for file not found
            logger.error("file not found for writing data - {} - error - {}", data.toString(), e.getMessage());
        }catch (IOException e){
            // Handles exception for writing to file
            logger.error("failed to write data - {} - error - {}", data.toString(), e.getMessage());
        }catch (Exception e){
            // Handles all other errors
            logger.error("failed to write data with exception - {} - error - {}", data.toString(), e.getMessage());
        }
    }

    // AggregateData will aggregate every min creating 1 min aggregated metrics
    public void AggregateData(){
        try{
            logger.info("starting the aggregation for this min");
            Reader r = new FileReader("/Users/parvez/Private/spring-boot-demo/src/main/java/com/demo/rest/data/recorded_data.csv");
            BufferedReader reader = new BufferedReader(r);
            long start = System.currentTimeMillis() - (60 * 1000);
            long end = System.currentTimeMillis();
            int[] aggVals = new int[5];
            String line = null;
            while ((line = reader.readLine()) != null){
                try{
                    String[] values = line.split(",");
                    long rtime = Long.parseLong(values[0]);
                    if(rtime >= start && rtime <= end){
                        for(int i=1; i < values.length; i++){
                            aggVals[i] += Integer.parseInt(values[i]);
                        }
                    }
                }catch (Exception e){
                    logger.error("failed to format values - {}", e.getMessage());
                }
            }
            FileWriter writer = new FileWriter("/Users/parvez/Private/spring-boot-demo/src/main/java/com/demo/rest/data/aggregated_data.csv");
            writer.write(Stream.of(aggVals).map(String::valueOf).collect(Collectors.joining(",")));
            writer.close();
            reader.close();
        }catch (FileNotFoundException e){
            // Handling exception for file not found
            logger.error("file not found for aggregating data - error - {}",e.getMessage());
        }catch (IOException e){
            // Handles exception for writing to file
            logger.error("failed to read data - error - {}", e.getMessage());
        }catch (Exception e){
            // Handles all other errors
            logger.error("failed to aggregate data with exception - error - {}", e.getMessage());
        }
    }

    // FetchData will return the aggregated data for specified time
    public int[] FetchData(int interval){
        try{
            logger.info("fetching the data for interval - {}", interval);
            File file = new File("../data/recorded_data.csv");
            ReversedLinesFileReader reader = new ReversedLinesFileReader(file, Charset.forName("utf8"));
            return null;
        }catch (FileNotFoundException e){
            // Handling exception for file not found
            logger.error("file not found for aggregating data - error - {}",e.getMessage());
            return null;
        }catch (IOException e){
            // Handles exception for writing to file
            logger.error("failed to read data - error - {}", e.getMessage());
            return null;
        }catch (Exception e){
            // Handles all other errors
            logger.error("failed to aggregate data with exception - error - {}", e.getMessage());
            return null;
        }
    }

}
