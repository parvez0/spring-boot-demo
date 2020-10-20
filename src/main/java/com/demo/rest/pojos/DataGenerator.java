//import java.net.http.HttpResponse;
//import com.
//import org.apache.tomcat.jni.Time;
//
//public class DataGenerator {
//    public static void main(String args[]){
//        while (true){
//            int[] data = new int[4];
//            for(int i=0; i<4; i++){
//                data[i] = (int) Math.random();
//            }
//            HttpResponse<String> response = Unire.post("localhost:8080/")
//                    .header("Content-Type", "application/json")
//                    .body("{\n    \"data\": "+ data.toString() +"\n}")
//                    .asString();
//            System.out.println("Got response from metric collector - status - " + response.statusCode() + " - body - " + response.body().toString());
//            Time.sleep(60000);
//        }
//    }
//}
