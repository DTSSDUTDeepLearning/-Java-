package Chapter16;

import java.util.concurrent.TimeUnit;

public class FlightSecurity {

    private int count = 0;
    // 登机牌
    private String boardingPass = "null";
    // 身份证
    private String idCard = "null";

    public synchronized void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException("====Exception===" + toString());
        }
        else {
            System.out.println(boardingPass + " passed");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "The" + count + " passengers, boardingPass [" + boardingPass  +"], idCard [" + idCard + "]";
    }
}
