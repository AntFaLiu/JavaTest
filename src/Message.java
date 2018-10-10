public class Message implements IMessageApi {

    private int i;

    private Message() {
        i = 50;
    }

    @Override
    public int conn() {
        return i;
    }
}
