package myapp.rabbit;
import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by sp0ilerz on 14-06-2015.
 */
public class rabbitApplication extends Application {
    public void onCreate(){
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "EIDo0blIcSnhxOGeScfCrH9XsdpNLsxHA28EicYQ", "bY7ORkjtF4QqrG7Vt4l68F8akKdb7WKeiSkEnqU5");
        /*ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/

    }
}
