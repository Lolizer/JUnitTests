
import javafx.util.Pair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class TestMain {
    static String uri;
    static HttpClient httpclient = HttpClients.createDefault();

    public TestMain(){

    }

    public static void main(String[] args){
        toRegistrate();
        toDoGetReq();
    }

    public static void toRegistrate(){
        uri = "http://localhost:8080/register?user=Jon&company=100";
        toDoReq();
    }

    public static void toDoGetReq(){
        uri = "http://localhost:8080/company/100/users?name=Max";
        toDoReq();
    }

    public static Pair<Integer,String> toDoReq(){
        HttpGet request = new HttpGet(uri);

        try {

            HttpResponse response = httpclient.execute(request);

            HttpEntity he = response.getEntity();

            String buff = EntityUtils.toString(he);

            System.out.println(buff);

            return new Pair<Integer,String>(response.getStatusLine().getStatusCode(),buff);
        }catch (ClientProtocolException cpe){
            cpe.printStackTrace();
        }catch (IOException ie){
            ie.printStackTrace();
        }
        return new Pair<>(null,null);
    }
}
