package postman;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ApiTestPostmanGet.class,
        ApiTestPostmanDelete.class,
        ApiTestPostmanPostBody.class,
        ApiTestPostmanPostData.class,
        ApiTestPostmanPatch.class,
        ApiTestPostmanPut.class
})
public class TestRunner {

}
