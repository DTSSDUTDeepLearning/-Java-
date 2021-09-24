package Chapter28;

public class SimpleObject {

    @Subscribe(topic = "alex-topic")
    public void test2(Integer x) {}

    @Subscribe(topic = "test-topic")
    public void test3(Integer x) {}
}
