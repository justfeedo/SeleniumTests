package tests;

import Base.TestBase;
import categories.ReleaseTest;
import categories.SmokeTest;
import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(ConcurrentTestRunner.class)

public class DummyTest extends TestBase {
    static int numberofFailedTests;

    int failed = 0;




    @Category(SmokeTest.class)
    @Test
    public void testA(){
        System.out.println("setup");
        getDriver().get(BASE_URL + "wairforit.php");
        System.out.println("A");
        System.out.println("Static "+ numberofFailedTests);
        failed++;
        System.out.println(failed);

    }
    @Category({SmokeTest.class, ReleaseTest.class})
    @Test
    public void testB(){
        getDriver().get(BASE_URL + "wairforit.php");
        System.out.println("B");
        numberofFailedTests++;
        System.out.println("Static "+numberofFailedTests);
        failed=10;

    }
    @Category(ReleaseTest.class)
    @Test
    public void testC(){
        getDriver().get(BASE_URL + "wairforit.php");
        System.out.println("C");
        System.out.println("Static "+ numberofFailedTests);
        System.out.println("failed");

    }

}
