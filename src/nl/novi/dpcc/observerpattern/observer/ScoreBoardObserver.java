package nl.novi.dpcc.observerpattern.observer;

import nl.novi.dpcc.observerpattern.domain.Message;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardObserver implements Observer {

    private int club1Goals;
    private int club2Goals;
    private int club1YellowCard;
    private int club2YellowCard;
    private int club1RedCard;
    private int club2RedCard;

    List<String> clubs = new ArrayList<>();


    @Override
    public void update(Message message) {
        String clubname = message.getClubName();
        if(!isInList(clubname)) {
            clubs.add(clubname);
        }

        System.out.println(message.getMatchEventType());
        if(message.getClubName().equals(this.clubs.get(0))) {
            switch (message.getMatchEventType()) {
                case GOAL:
                    this.club1Goals++;
                    break;
                case RED_CARD:
                    this.club1RedCard++;
                    break;
                case YELLOW_CARD:
                    this.club1YellowCard++;
                    break;
            }
        } else {
            switch(message.getMatchEventType()) {
                case GOAL:
                    this.club2Goals ++;
                    break;
                case RED_CARD:
                    this.club2RedCard ++;
                    break;
                case YELLOW_CARD:
                    this.club2YellowCard ++;
                    break;
            }
        }
    }

    public String getResult() {
        String result = "";

        if(this.club1Goals == this.club2Goals){
            result = " Gelijkspel";
        } else if (this.club1Goals > this.club2Goals) {
            result = this.clubs.get(0) + " heeft gewonnen";
        } else {
            result = this.clubs.get(1) + " heeft gewonnen";
        }
        return result;
    }

    public boolean isInList(String clubName){
        for (String club: this.clubs) {
            if(club.equals(clubName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return

                "ScoreBoardObserver { " +
                this.clubs.get(0) + " heeft " + club1Goals + " gescoord.\r" +
                        this.clubs.get(1) + " heeft " + club2Goals + " gescoord.\r" +
                        this.clubs.get(0) + " heeft " + club1YellowCard + " gele kaarten.\r" +
                        this.clubs.get(1) + " heeft " + club2YellowCard + " gele kaarten.\r" +
                        this.clubs.get(0) + " heeft " + club1RedCard + " rode kaarten.\r" +
                        this.clubs.get(1) + " heeft " + club2RedCard + " rode kaarten.\r" +
                        getResult();
    }
}
