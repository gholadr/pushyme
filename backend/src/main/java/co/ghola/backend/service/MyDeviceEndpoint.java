package co.ghola.backend.service;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.ServiceUnavailableException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;
import javax.inject.Named;


/**
 * An endpoint class we are exposing
 */
@Api(
        name = "deviceApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.ghola.co",
                ownerName = "backend.ghola.co",
                packagePath = ""
        )
)
public class MyDeviceEndpoint {

    private static final Logger logger = Logger.getLogger(MyDeviceEndpoint.class.getName());

    @ApiMethod(name = "sendPush")
    public PushyPushResponse sendPush(@Named("id") String id) throws Exception{
        // TODO: Implement this function
        logger.info("Calling sendPush method");

        // Prepare registration IDs array
        List<String> registrationIDs = new ArrayList<String>();

        // Add your registration IDs here
        registrationIDs.add(id);

        // Set payload (any object, it will be serialized to JSON)
        Map<String, String> payload = new HashMap<String, String>();

        // get the time
        Date curdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MMM hh:mm:ss a");
        format.setTimeZone(TimeZone.getTimeZone("GMT+07"));
        String timetostr = format.format(curdate);


        // Add test message
        payload.put("message", String.format("ola, it is now %s (ICT)", timetostr));

        // Prepare the push request
        PushyPushRequest push = new PushyPushRequest(payload, registrationIDs.toArray( new String[registrationIDs.size()] ));

        try
        {
            // Try sending the push notification
            PushyAPI.sendPush(push);
        }
        catch( Exception exc )
        {
            // Error, output log
            logger.info(exc.toString());
            throw new ServiceUnavailableException("Something bad happened. Check the logs.");
        }

        PushyPushResponse response = new PushyPushResponse();


        response.setResponse(String.format("[%s ICT ] Your push has been sent to device id %s", timetostr, id));

        return response;
    }
}