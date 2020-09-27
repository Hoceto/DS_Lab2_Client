package Request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.Builder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Builder
public class RequestService {
    private final HttpUriRequest type;
    private final Object body;
    private final Class response;



    public Object send() throws IOException {
        String result = "";

        if (type instanceof HttpPost)
            ((HttpPost)type).setEntity(new StringEntity(makeJson()));
        else if (type instanceof HttpPut)
            ((HttpPut)type).setEntity(new StringEntity(makeJson()));

        type.addHeader("content-type", "application/json");
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(type)) {
            HttpEntity entity = (HttpEntity) response.getEntity();
            if (entity != null){
                result = EntityUtils.toString(entity);
            }

        }

        return (!result.equals("")) ? makeObject(result) : null;
    }

    private String makeJson(){ Gson gson = new Gson(); return gson.toJson(body); }



    private Object makeObject(String json){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.fromJson(json, response);
    }
}
