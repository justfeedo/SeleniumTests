package suites;

import categories.ReleaseTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.DummyTest;
import tests.WaitForItTest;


@RunWith(Categories.class)
@Categories.IncludeCategory(ReleaseTest.class)
@Suite.SuiteClasses({
        DummyTest.class,
        WaitForItTest.class
        })
public class ReleaseTestSuite {

}
