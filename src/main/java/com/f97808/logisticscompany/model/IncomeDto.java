package com.f97808.logisticscompany.model;

public class IncomeDto {
    private double income;
    private double weightAll;
    private int packetsAll;
    private int packets1;
    private int packets2;
    private int packets3;
    private int packets4;
    private int toOffice;

    public IncomeDto() {
    }

    public IncomeDto(double income, double weightAll, int packetsAll, int packets1, int packets2, int packets3, int packets4, int toOffice) {
        this.income = income;
        this.weightAll = weightAll;
        this.packetsAll = packetsAll;
        this.packets1 = packets1;
        this.packets2 = packets2;
        this.packets3 = packets3;
        this.packets4 = packets4;
        this.toOffice = toOffice;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getWeightAll() {
        return weightAll;
    }

    public void setWeightAll(double weightAll) {
        this.weightAll = weightAll;
    }

    public int getPacketsAll() {
        return packetsAll;
    }

    public void setPacketsAll(int packetsAll) {
        this.packetsAll = packetsAll;
    }

    public int getPackets1() {
        return packets1;
    }

    public void setPackets1(int packets1) {
        this.packets1 = packets1;
    }

    public int getPackets2() {
        return packets2;
    }

    public void setPackets2(int packets2) {
        this.packets2 = packets2;
    }

    public int getPackets3() {
        return packets3;
    }

    public void setPackets3(int packets3) {
        this.packets3 = packets3;
    }

    public int getPackets4() {
        return packets4;
    }

    public void setPackets4(int packets4) {
        this.packets4 = packets4;
    }

    public int getToOffice() {
        return toOffice;
    }

    public void setToOffice(int toOffice) {
        this.toOffice = toOffice;
    }
}
