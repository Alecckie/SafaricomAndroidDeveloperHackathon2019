package ke.co.asertile.breakthrough.safaricomandroiddeveloperhackathon2019.model;

public class DecodeContributions {
    String week, deposit,total;

    public DecodeContributions(String week, String deposit, String total) {
        this.week = week;
        this.deposit = deposit;
        this.total = total;
    }

    public String getWeek() {
        return week;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getTotal() {
        return total;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
