package elisvaldocoelho.com.br.resultadoloteria;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by elisvaldo on 12/12/15.
 */
public class WebServiceHandler {

    public WebServiceHandler() {
    }

    public String getJASJONData(String url) {
        String response = null;

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;

        try {

            httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return response;
    }
}
