package me.quexer.homeserverv2.homebackend.misc;

import me.quexer.homeserverv2.homebackend.HomeBackend;
import me.quexer.homeserverv2.homebackend.enums.DisplayType;
import me.quexer.homeserverv2.homebackend.enums.SoundType;
import me.quexer.homeserverv2.homebackend.builder.TitleBuilder;
import org.bukkit.Bukkit;

public class Countdown {

    private int high;
    private int sched;
    private Runnable onFinish;
    private boolean silent;
    private DisplayType displayType;
    private String msg;
    private boolean started;


    public Countdown(int high, Runnable finish, boolean silent, DisplayType displayType, String msg){
        setHigh(high);
        setOnFinish(finish);
        setSilent(silent);
        setDisplayType(displayType);
        setMsg(msg);
        setStarted(false);

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(HomeBackend.getInstance(), () -> {
            setStarted(true);
            if(getHigh() == 0) {
                finish.run();
                Bukkit.getScheduler().cancelTask(sched);
            } else {
                if(!silent) {
                    switch (displayType) {
                        case CHAT:
                            Bukkit.getOnlinePlayers().forEach(o -> {
                                o.sendMessage(msg);
                            });
                            break;
                        case TITLE:
                            Bukkit.getOnlinePlayers().forEach(o -> {
                                TitleBuilder.sendTitle(o, 0, 20, 60, "§a", "§e§l"+getHigh());
                            });
                            break;
                        case ACTIONBAR:
                            Bukkit.getOnlinePlayers().forEach(o -> {
                                TitleBuilder.sendActionBar(o, "§7Countdown §8● §e§l"+getHigh());
                            });
                            break;
                    }
                    Bukkit.getOnlinePlayers().forEach(o -> {
                        if(getHigh() <= 5) {
                            HomeBackend.getSoundManager().play(o, SoundType.NORMAL);
                        }
                        o.setLevel(getHigh());
                    });
                }
                setHigh(getHigh()-1);
            }
        }, 1, 20);


    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getSched() {
        return sched;
    }

    public void setSched(int sched) {
        this.sched = sched;
    }

    public Runnable getOnFinish() {
        return onFinish;
    }

    public void setOnFinish(Runnable onFinish) {
        this.onFinish = onFinish;
    }

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }
}
