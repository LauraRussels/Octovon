package net.octovon;

public class Stopwatch extends Thread {
    private long startTime;
    private boolean started;

    public void startThread() {
        this.startTime = System.currentTimeMillis();
        this.started = true;
        this.start();
    }

    public void run() {
        while (started) {}
    }


    public int[] getTime() {
        long milliTime = System.currentTimeMillis() - this.startTime;
        int[] out = new int[]{0, 0, 0, 0};
        out[0] = (int)(milliTime / 3600000);
        out[1] = (int)(milliTime / 60000) % 60;

        return out;
    }

    public void stopThread() {
        this.started = false;
    }
}
