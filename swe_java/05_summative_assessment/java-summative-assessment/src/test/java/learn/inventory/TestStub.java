package learn.inventory;

import org.junit.jupiter.api.*;

public class TestStub {

    @BeforeAll
    static void test_Environment_Setup(){
        System.out.println("Setup Test Suite");
    }

    @BeforeEach
    void test_SetUp(){
        System.out.println("Setup Test State");
    }

    @Test
    void test01_TestTest(){
        System.out.println("Test");
        Assertions.assertEquals(1,1);
    }

    @AfterEach
    void test_State_TearDown(){
        System.out.println("Tear down Test State");
    }

    @AfterAll
    static void test_Environment_TearDown(){
        System.out.println("Tear Down Test Suite");
    }
}
