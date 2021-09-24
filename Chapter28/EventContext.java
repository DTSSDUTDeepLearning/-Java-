package Chapter28;

import java.lang.reflect.Method;

public interface EventContext {
    String getSource();
    Object getSubScriber();
    Method getSubScribe();
    Object getEvent();
}
