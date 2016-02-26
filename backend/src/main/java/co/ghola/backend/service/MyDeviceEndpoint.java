package co.ghola.backend.service;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Named;

import co.ghola.backend.entity.MyDevice;

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

    /**
     * This method gets the <code>MyDevice</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>MyDevice</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "sendPush")
    public MyDevice sendPush(@Named("id") String id) {
        // TODO: Implement this function
        logger.info("Calling sendPush method");

        // Prepare registration IDs array
        List<String> registrationIDs = new ArrayList<String>();

        // Add your registration IDs here
        registrationIDs.add(id);

        // Set payload (any object, it will be serialized to JSON)
        Map<String, String> payload = new HashMap<String, String>();

        // Add test message
        payload.put("message", "bitches!!!!!");

        // Prepare the push request
        PushyPushRequest push = new PushyPushRequest(payload, registrationIDs.toArray( new String[registrationIDs.size()] ));

        try
        {
            // Try sending the push notification
            PushyAPI.sendPush(push);
        }
        catch( Exception exc )
        {
            // Error, print to output
            System.out.println(exc.toString());
        }


        MyDevice response = new MyDevice();
        response.setId("Your push has been sent to device id: " + id
        );
        return response;
    }

    /**
     * This inserts a new <code>MyDevice</code> object.
     *
     * @param myDevice The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertMyDevice")
    public MyDevice insertMyDevice(MyDevice myDevice) {
        // TODO: Implement this function
        logger.info("Calling insertMyDevice method");
        return myDevice;
    }
}