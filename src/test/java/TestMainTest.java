import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class TestMainTest {
    @Before
    public void init(){
        TestMain.uri = "";
    }
    //RegistrationTest
    @Test
    public void toDoReg() {
        TestMain main = new TestMain();
        TestMain.uri = "http://localhost:8080/register?user=Jon&company=100";
        Pair<Integer,String> buff = main.toDoReq();
        Assert.assertEquals(buff.getKey(),(Integer)200);
        System.out.println(buff.getValue());
    }

    //If 'name' is not belonging to a coworker
    @Test
    public void toDoReq() {
        TestMain main = new TestMain();
        TestMain.uri = "http://localhost:8080/company/200/users?name=Izergil";
        Pair<Integer, String> buff = main.toDoReq();
        Assert.assertEquals(buff.getKey(),(Integer)412);
        Assert.assertTrue(buff.getValue().contains("User can search only coworkers!"));
    }

    //Successful launch
    @Test
    public void toDoSucReq() {
        TestMain main = new TestMain();
        TestMain.uri = "http://localhost:8080/company/100/users?name=Max";
        Pair<Integer, String> buff = main.toDoReq();
        Assert.assertEquals(buff.getKey(),(Integer)200);
        Assert.assertTrue(buff.getValue().contains("Max"));
    }
}