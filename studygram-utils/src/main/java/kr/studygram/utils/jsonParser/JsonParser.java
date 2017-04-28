package kr.studygram.utils.jsonParser;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 * Created by cynos07 on 2017-04-27.
 */
public class JsonParser {

    public void parse() throws IOException {
        InputStream input = new URL("https://graph.facebook.com/me?access_token=EAAbC0LZC4O6oBANkDnNxDHKCxc9leMFXd0tUA3jM6X7dMJGbtz37ozzu2TTI2AJax8fRolR8QKzsUW5IqewK18xA3OUwm29PrSqwtrvc8ckZAHZAV44lWRxgZClTPSv6L4UcQuerdbbJgTqoxG1rGfZCMXmTpFV9vsrTIMI84dRDbKbYtod4CZCBOW1DzyqAgZD").openStream();
        Reader reader = new InputStreamReader(input, "UTF-8");
        System.out.println(new Gson().fromJson(reader, Object.class));
    }
}
