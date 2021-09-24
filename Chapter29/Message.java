package Chapter29;

public interface Message {

    Class<? extends Message> getType();
}
